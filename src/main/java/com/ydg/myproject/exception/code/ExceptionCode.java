package com.ydg.myproject.exception.code;

import com.ydg.myproject.exception.BaseExceptionCode;

/**
 * @author dongzf
 * @date 2018/8/10
 * @description 统一异常码
 */
public enum ExceptionCode implements BaseExceptionCode {

    //系统
    SYSTEM_BUSY(-1, "系统繁忙，请稍候再试"),
    BAD_REQUEST(-2, "400"),
    UNAUTHORIZED(-3, "401"),
    FORBIDDEN(-4, "403"),
    NOT_FOUND(-5, "404"),
    //菜单管理
    MENU_NULL(1001, "菜单信息不能为空"),
    MENU_PID_NULL(1002, "父节点不能为空"),
    MENU_ID_NULL(1003, "菜单id不能为空"),
    MENU_TYPE_NULL(1004, "菜单类型不能为空"),
    MENU_NAME_EXIST(1005, "菜单名已存在"),
    MENU_UPDATE_NOT_EXIST(1006, "修改菜单不存在"),
    MENU_DELETE_NOT_EXIST(1007, "删除菜单不存在"),
    MENU_EXIST_CHILD(1008, "该菜单存在子菜单，无法删除"),
    MENU_PID_NOT_EXIST(1009, "父节点不存在"),
    //部门管理
    ORG_NULL(2001, "部门信息不能为空"),
    ORG_PID_NULL(2002, "上级部门不能为空"),
    ORG_ID_NULL(2003, "部门id不能为空"),
    ORG_TYPE_NULL(2004, "部门类型不能为空"),
    ORG_NAME_EXIST(2005, "部门名已存在"),
    ORG_NOT_EXIST(2006, "部门不存在"),
    ORG_EXIST_CHILD(2007, "该部门存在下属部门，无法删除"),
    ORG_PID_NOT_EXIST(2008, "父节点不存在"),
    //角色管理
    ROLE_NULL(3001, "角色信息不能为空"),
    ROLE_NAME_NULL(3002, "角色名不能为空"),
    ROLE_ID_NULL(3003, "角色id不能为空"),
    ROLE_ORGID_NULL(3004, "角色所属部门不能为空"),
    ROLE_NAME_EXIST(3006, "角色名已存在"),
    ROLE_UPDATE_NOT_EXIST(3007, "修改角色不存在"),
    ROLE_DELETE_NOT_EXIST(3008, "删除角色不存在"),
    //用户管理
    USER_NULL(4001, "用户信息不能为空"),
    USER_ACCOUNT_NULL(4002, "用户账号不能为空"),
    USER_ID_NULL(4003, "用户id不能为空"),
    USER_ORGID_NULL(4004, "用户所属部门不能为空"),
    USER_POSTID_NULL(4004, "用户岗位不能为空"),
    USER_PASSWORD_NULL(4005, "密码不能为空"),
    USER_ACCOUNT_EXIST(4006, "用户账号已存在"),
    USER_ACCOUNT_NOT_EXIST(4007, "用户账号不存在"),
    USER_PASSWORD_ERROR(4008, "密码错误"),
    PASSWORD_CONFIRM_FAIL(4009,"两次输入密码不一致"),

    //
    INSUFFICIENT_PERMISSIONS(5001,"权限不足"),
    SERVICE_RETURN_DATA_NULL(5002,"服务接口返回null"),
    PAGE_QUERY_PARAMS_INCORRECT(5003,"分页查询参数不正确"),
    ERR_ACCESS_DENIED(5004,"access_denied"),


    TOKEN_EXPIRED(6001, "token超时，请检查 token 的有效期"),
    TOKEN_SIGNATURE(6002, "不合法的token，请认真比对 token 的签名"),
    TOKEN_ILLEGAL_ARGUMENT(6003, "缺少token参数"),
    TOKEN_GEN_FAIL(6004, "生成token失败"),
    TOKEN_PARSER_FAIL(6005, "解析token失败"),
    TOKEN_APPID_SECRET_INVALID(6006, "获取 access_token 时 AppSecret 错误，或者 AppId 无效！"),
    REQUEST_INVALID_NOT_CORRECT(6007, "请求路径不存在或不合法！"),
    PERMISSION_DENIED(6008, "权限不足！"),
    NOT_LOGIN(6009, "未登录！"),

    //查询条件  --日期
    STARTTIME_AFTER_ENDTIME(7001, "开始时间需在结束时间之前！"),
    NOT_TIME(7002, "开始时间或结束时间 非日期格式！"),
    MUST_STARTTIME_AND_ENDTIME(7003, "缺少开始时间或结束时间"),
    PARAMS_INVALID(7004, "参数校验异常"),
    MEDIA_ID_INVALID(7005, "媒体id不能为空"),

    MEDIA_PARAM_INVALID(7006, "添加media参数regionName格式不对"),
    DIC_NAME_EXIT(7007, "数据已存在"),
    RANK_DETAIL_NOT_EXIT(7008, "详情不存在"),
    LABEL_EXIT(7009, "标签已存在"),

    FILE_TYPE_ERROR(7010, "上传文件类型异常"),
    FILE_FORMAT_ERROR(7011, "上传Excel表格格式有误<br>或者<br> 上传Excel数据为空"),
    FILE_SERVER_ERROR(7012, "服务端异常"),
    EXCEL_DATA_VALID_FAIL(7013, "导入excel验证异常的数据"),
    EXCEL_QUOTENULL_AND_DISSCOUNT_LESS2(7014, "没有刊例价格，且折扣小于两位整数 不能录入"),
    MEDIA_NAME_EXIST(7015, "媒体名称已存在");

    private  int code;
    private  String msg;
    ExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
