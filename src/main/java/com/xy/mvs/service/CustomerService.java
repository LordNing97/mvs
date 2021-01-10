package com.xy.mvs.service;

import com.xy.mvs.mapper.CustomerMapper;
import com.xy.mvs.model.Customer;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2021/1/10 18:01
 * @Version 1.0
 **/
@Service
public class CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    /**
     * 保存客户
     * @param customer
     * @return
     */
    public boolean saveCustomer(Customer customer){
        customer.setIsDel(0);
        return customerMapper.saveCustomer(customer) > 0;
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    public Customer getById(Integer id){
        return customerMapper.getById(id);
    }

}
