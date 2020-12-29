package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.model.User;
import com.xy.mvs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2020/12/1 14:50
 * @Version 1.0
 */
@RestController
@RequestMapping("user")
@Validated
@Api(tags = "用户接口", produces = "application/json")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "保存用户", httpMethod = "POST")
    @PostMapping("saveUser")
    public Result saveUser(@ModelAttribute User user){
        return userService.saveUser(user);
    }

    @ApiOperation(value = "登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名",dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码",dataType = "string", paramType = "query", required = true)
    })
    @PostMapping("login")
    public Result login(String name, String password){
        User user = userService.login(name, password);
        if(user != null){
            return Result.builder().data(user).build();
        }
        return Result.builder(ResultCode.UN_USER).build();
    }

    @ApiOperation(value = "通过id获取用户", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID",dataType = "int", paramType = "query", required = true)
    })
    @GetMapping("getById")
    public Result getById(Integer id){
        return Result.builder()
                .data(userService.getById(id))
                .build();
    }

    @ApiOperation(value = "修改用户", httpMethod = "POST")
    @PostMapping("modifyUser")
    public Result modifyUser(@ModelAttribute User user){
        if(userService.modifyUser(user)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "删除用户", httpMethod = "POST")
    @PostMapping("deleteUser")
    public Result deleteUser(Integer id){
        if(userService.deleteUser(id)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "分页获取用户", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名",dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "role", value = "权限(-1:全部 0:系统管理员 1:抽奖 2:商城 3:抽奖+抽奖设置 4:抽奖+商城)",dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "page", value = "page",dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "size",dataType = "int", paramType = "query", required = true)
    })
    @GetMapping("getUserList")
    public Result getUserList(String name, Integer role, Integer page, Integer size){
        return Result.builder()
                .data(userService.getUserList(name, role, page, size))
                .build();
    }

}
