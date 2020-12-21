package com.xy.mvs;

import com.xy.mvs.mapper.UserMapper;
import com.xy.mvs.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MvsApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        User user = userMapper.selectById(2);
        System.out.print(user.toString());
    }

}
