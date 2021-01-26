package com.xy.mvs.mapper;

import com.xy.mvs.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 批量保存客户
     * @param customerList
     * @return
     */
    int saveBatchCustomer(@Param("customerList") List<Customer> customerList);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Customer getById(Integer id);

    int countCustomer(@Param("name") String name, @Param("type") Integer type);

    /**
     * 分页获取客户
     * @param name
     * @param type
     * @param page
     * @param size
     * @return
     */
    List<Customer> getCustomerList(@Param("name") String name, @Param("type") Integer type,
                                   @Param("page") Integer page, @Param("size") Integer size);

}
