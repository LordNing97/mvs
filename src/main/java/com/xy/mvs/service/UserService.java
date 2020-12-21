package com.xy.mvs.service;


import com.xy.mvs.util.ResultMap;

public interface UserService {

    /**
     * 根据用户名和密码获取用户
     */
    ResultMap selectByNameAndPwd(String username, String password);

    /**
     * 根据ID获取用户
     */
    ResultMap selectById(Integer id);

}
