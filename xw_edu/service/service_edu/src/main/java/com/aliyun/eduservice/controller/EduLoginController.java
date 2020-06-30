package com.aliyun.eduservice.controller;

import com.aliyun.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2020年06月27日
 *
 * @author 徐威
 * @version : 1.0
 */

@RestController
@CrossOrigin // 解决跨域问题
@RequestMapping("/eduService/user")
public class EduLoginController {

    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","tony").data("avatar",
                "https://www.google.com.hk/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png");
    }

}
