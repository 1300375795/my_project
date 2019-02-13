package com.ydg.myproject.common;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;

/**
 * @author ydg
 * @date 2018/7/24
 * @description
 */
@Data
@Slf4j
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1327894327489237894L;

    @Comment("主键id")
    private String id;

    @Column("del_flag")
    @Comment("是否可用标志 1、true ，0、false")
    public Boolean delFlag;

    @Column("create_by")
    @Comment("创建人")
    public String createBy;

    @Column("create_date")
    @Comment("创建时间")
    public Date createDate;

    @Column("update_by")
    @Comment("更新人")
    public String updateBy;

    @Column("update_date")
    @Comment("更新时间")
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
