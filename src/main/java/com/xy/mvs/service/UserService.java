package com.xy.mvs.service;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.mapper.UserMapper;
import com.xy.mvs.model.User;
import com.xy.mvs.request.UserList;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2020/12/29 11:24
 * @Version 1.0
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 保存用户
     * @param user
     * @return
     */
    public Result saveUser(User user){
        if(userMapper.selectByNameAndPwd(user.getName(), null) != null){
            return Result.builder(ResultCode.USER_IS_EXISTENCE).build();
        }
        user.setIsDel(0);
        userMapper.saveUser(user);
        return Result.builder().build();
    }

    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    public User login(String name, String password){
        return userMapper.selectByNameAndPwd(name, password);
    }

    /**
     * 根据ID获取用户
     * @param id
     * @return
     */
    public User getById(Integer id) {
        User user = userMapper.getById(id);
        return user;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    public boolean modifyUser(User user){
        return userMapper.modifyUser(user) > 0;
    }

    /**
     * 修改用户
     * @param id
     * @return
     */
    public boolean deleteUser(Integer id){
        return userMapper.deleteUser(id) > 0;
    }

    /**
     * 分页获取用户
     * @param name
     * @param role
     * @param page
     * @param size
     * @return
     */
    public UserList getUserList(String name, Integer role, Integer page, Integer size){
        List<User> userList = userMapper.getUserList(name, role, (page - 1) * size, size);
        return UserList.builder()
                .userList(userList)
                .total(userMapper.countUser(name, role))
                .page(page)
                .build();
    }

}