package com.xy.mvs.service.impl;

import com.xy.mvs.mapper.UserMapper;
import com.xy.mvs.model.User;
import com.xy.mvs.service.UserService;
import com.xy.mvs.util.ResultMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户名和密码获取用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResultMap selectByNameAndPwd(String username, String password){
        ResultMap resultMap = null;
        User user = userMapper.selectByNameAndPwd(username,password);
        if(user != null){
            resultMap = ResultMap.ok().put("user",user);
        }else{
            resultMap = ResultMap.error(100,"登录失败");
        }
        return resultMap;
    }

    @Override
    public ResultMap selectById(Integer id) {
        ResultMap resultMap = null;
        User user = userMapper.selectById(id);
        if(user != null){
            resultMap = ResultMap.ok().put("user",user);
        }else {
            resultMap = ResultMap.error(100,"error");
        }
        return resultMap;
    }

}
































