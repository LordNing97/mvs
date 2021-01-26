package com.xy.mvs.util;

import java.util.Random;

/**
 * @Author 陈璇
 * @Date 2020/4/14 18:20
 * @Version 1.0
 **/
public class IntegerUtils {

    /**
     * 生成6位验证码
     * @return
     */
    public static String random() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        int[] c = new int[6];
        for (int i = 0; i < 6; i++) {
            c[i] = r.nextInt(9) + 1;
            sb.append(c[i]);
        }
        return sb.toString();
    }

    /**
     * 生成8位手机尾号
     * @return
     */
    public static String telephoneNum() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        int[] c = new int[8];
        for (int i = 0; i < 8; i++) {
            c[i] = r.nextInt(9) + 1;
            sb.append(c[i]);
        }
        return sb.toString();
    }

}
