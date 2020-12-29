package com.xy.mvs.mapper;

import com.xy.mvs.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    /**
     * 保存用户
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 根据用户名和密码获取用户
     * @param name
     * @param password
     * @return
     */
    User selectByNameAndPwd(@Param("name") String name, @Param("password") String password);

    /**
     * 根据ID获取用户
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 编辑用户
     * @param user
     * @return
     */
    int modifyUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(Integer id);

}
