package com.xy.mvs.util;

import java.util.UUID;

/**
 * @Author 陈璇
 * @Date 2020/4/2 13:54
 * @Version 1.0
 **/
public class UUIDUtil {

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static String randomID(){
        return UUID.randomUUID().toString().replace("-", "").substring(0,8);
    }

}
