package com.xy.mvs.controller;

import com.xy.mvs.service.UserService;
import com.xy.mvs.util.ResultMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/userLogin")
    public ResultMap userLogin(String username, String password){
        return userService.selectByNameAndPwd(username,password);
    }

    @PostMapping("/getUserById")
    public ResultMap getUserById(Integer id){
        return userService.selectById(id);
    }

}
