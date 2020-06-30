package com.aliyun.commonbase.exceptionhandler.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2020年05月17日
 *
 * @author 徐威
 * @version : 1.0
 */

// 自定义异常
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelfException extends RuntimeException{

    private Integer code; // 异常状态码

    private String msg;   // 异常信息

}
