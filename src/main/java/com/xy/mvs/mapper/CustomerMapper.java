package com.xy.mvs.mapper;

import com.xy.mvs.model.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 陈璇
 * @Date 2021/1/10 18:00
 * @Version 1.0
 **/
@Mapper
public interface CustomerMapper {

    /**
     * 保存客户
     * @param customer
     * @return
     */
    int saveCustomer(Customer customer);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Customer getById(Integer id);

}
