package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2020/12/1 14:50
 * @Version 1.0
 */
@RestController
@RequestMapping("app/user")
@Validated
@Api(tags = "用户接口", produces = "application/json")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "通过id获取用户", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID",dataType = "string", paramType = "query", required = true)
    })
    @GetMapping("getUserById")
    public Result getUserById(Integer id){
        return Result.builder()
                .data(userService.selectById(id))
                .build();
    }

}
