package com.aliyun.commonbase.exceptionhandler.exceptionhandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2020年05月17日
 *
 * @author 徐威
 * @version : 1.0
 */

// 打印异常信息的工具类
public class ExceptionUtil {

    public static String getMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);

            // 将错误信息输出到PrintWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if(sw!=null) {
                try {
                    sw.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            if(pw!=null) {
                try {
                    pw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return sw.toString();
    }

}
