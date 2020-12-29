package com.xy.mvs.service;

import com.xy.mvs.mapper.UserMapper;
import com.xy.mvs.model.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2020/12/29 11:24
 * @Version 1.0
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User selectById(Integer id) {
        User user = userMapper.selectById(id);
        return user;
    }

}