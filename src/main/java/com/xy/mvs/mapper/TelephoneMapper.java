package com.xy.mvs.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/26 11:03
 * @Version 1.0
 */
@Mapper
public interface TelephoneMapper {

    /**
     * 获取所有手机号码
     * @return
     */
    List<String> getNumber();

}
