package com.ydg.myproject.common;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ydg
 * @date 2018/7/24
 * @description
 */
@Data
@Slf4j
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1327894327489237894L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 是否可用标志 1、true ，0、false
     */
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    public Boolean delFlag;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    public String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    public Date createDate;

    /**
     * 更新人
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    public String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    public Date updateDate;

    public void baseSave() {
        this.createBy = "ydg";
        Date date = new Date();
        log.info("创建时间为:{}", date);
        this.createDate = date;
        this.updateBy = "ydg";
        this.updateDate = date;
        this.delFlag = false;
    }

    public void baseUpdate(boolean isDelete) {
        if (isDelete) {
            this.delFlag = true;
        }
        this.updateDate = new Date();
        log.info("创建时间为:{}", this.updateBy);
        this.updateBy = "ydg";
    }
}
