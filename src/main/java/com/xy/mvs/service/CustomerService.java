package com.xy.mvs.service;

import com.xy.mvs.mapper.CustomerMapper;
import com.xy.mvs.model.Customer;
import com.xy.mvs.request.CustomerList;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 分页获取客户
     * @param name
     * @param type
     * @param page
     * @param size
     * @return
     */
    public CustomerList getCustomerList(String name, Integer type, Integer page, Integer size){
        List<Customer> customerList = customerMapper.getCustomerList(name, type, (page - 1) * size, size);
        return CustomerList.builder()
                .customerList(customerList)
                .total(customerMapper.countCustomer(name, type))
                .page(page)
                .build();
    }

}
