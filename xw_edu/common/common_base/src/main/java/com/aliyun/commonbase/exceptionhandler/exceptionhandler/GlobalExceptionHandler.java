package com.aliyun.commonbase.exceptionhandler.exceptionhandler;

import com.aliyun.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2020年05月17日
 *
 * @author 徐威
 * @version : 1.0
 */

// 统一异常处理
@ControllerAdvice
@Slf4j // 将异常信息输出到日志中
public class GlobalExceptionHandler {

    // 1.指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理..");
    }

    // 2.特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了算术异常处理..");
    }

    // 3.自定义异常处理
    @ExceptionHandler(SelfException.class)
    @ResponseBody //为了返回数据
    public R error(SelfException e) {
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
