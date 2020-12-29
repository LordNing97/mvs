package com.xy.mvs.request;

import com.xy.mvs.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2020/12/29 23:03
 * @Version 1.0
 **/
@Getter
@Builder
@ToString
public class UserList {

    List<User> userList;
    int total;
    int page;

}
