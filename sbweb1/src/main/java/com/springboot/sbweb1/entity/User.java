package com.springboot.sbweb1.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;


/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2020年05月12日
 *
 * @author 徐威
 * @version : 1.0
 */

@Data
public class User {
    //@TableId(type = IdType.ID_WORKER) //mp自带策略，生成19位值，数字类型使用这种策略，比如long
    //@TableId(type = IdType.ID_WORKER_STR) //mp自带策略，生成19位值，字符串类型使用这种策略
    private Long id;
    private String name;
    private Integer age;
    private String email;

    // 1.在需要自动填充的属性上加上注解
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


    // 2.乐观锁
    @Version
    @TableField(fill = FieldFill.INSERT)
    private int version;

    // 3.逻辑删除
    @TableLogic
    private byte deleted;


    // 链式编程
    public User id(Long id){
        this.setId(id);
        return this;
    }

    public User name(String name){
        this.setName(name);
        return this;
    }

    public User age(Integer age){
        this.setAge(age);
        return this;
    }

    public User email(String email){
        this.setEmail(email);
        return this;
    }
}
