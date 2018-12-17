package com.ydg.myproject.token;

import com.ydg.myproject.common.RedisConstant;
import com.ydg.myproject.exception.BizException;
import com.ydg.myproject.exception.code.ExceptionCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author dongzf
 * @date 2018/7/20
 * @description
 */
@Component
@Slf4j
public class TokenServer {
    @Value("${cloud.security.expiration}")
    private long expiration;
    @Value("${cloud.security.signKey}")
    private String signKey;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 生成token
     *
     * @param account
     * @return
     */
    public String generateToken(String account) {
        try {
            Map<String, Object> claims = new HashMap<>(2);
            claims.put("account", account);
            String jwt = Jwts.builder().setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(SignatureAlgorithm.HS512, signKey).compact();

            String token = RedisConstant.TOKEN_PREFIX + jwt;
            // TODO: 2018-12-17 下面的这个还需要保存到redis中，然后到时候前端、app之类的需要给出token(token是否是保存在cookie中)给服务端验证
            log.info("获取到的token是：{}", token);
            return token;
        } catch (Exception e) {
            throw new BizException(ExceptionCode.TOKEN_GEN_FAIL.getCode(), ExceptionCode.TOKEN_GEN_FAIL.getMsg());
        }

    }

    /**
     * 验证token
     *
     * @param request
     * @return
     */
    public Map<String, Object> validateToken(HttpServletRequest request) {

        String token = request.getHeader(RedisConstant.HEADER_STRING);
        try {
            if (token == null) {
                throw new BizException(ExceptionCode.TOKEN_ILLEGAL_ARGUMENT.getCode(),
                        ExceptionCode.TOKEN_ILLEGAL_ARGUMENT.getMsg());
            }
            Map<String, Object> parseData = Jwts.parser().setSigningKey(signKey)
                    .parseClaimsJws(token.replace(RedisConstant.TOKEN_PREFIX, "")).getBody();
            return parseData;
        } catch (ExpiredJwtException ex) {
            //过期
            throw new BizException(ExceptionCode.TOKEN_EXPIRED.getCode(), ExceptionCode.TOKEN_EXPIRED.getMsg());
        } catch (IllegalArgumentException ex) {
            //token 为空
            throw new BizException(ExceptionCode.TOKEN_ILLEGAL_ARGUMENT.getCode(),
                    ExceptionCode.TOKEN_ILLEGAL_ARGUMENT.getMsg());
        } catch (Exception e) {
            throw new BizException(ExceptionCode.TOKEN_PARSER_FAIL.getCode(), ExceptionCode.TOKEN_PARSER_FAIL.getMsg());
        }
    }
}
