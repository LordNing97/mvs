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
        customer.setIsMail(1);
        customer.setIsLottery(0);
        customer.setIsAuto(0);
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
        for(int i = 0;i < customerList.size();i++){
            if(customerList.get(i).getIsMail() == 1 && customerList.get(i).getIsLottery() == 0){
                customerList.get(i).setType(0);
            }else if(customerList.get(i).getIsLottery() == 1 && customerList.get(i).getIsMail() == 0){
                customerList.get(i).setType(1);
            }else if(customerList.get(i).getIsLottery() == 1 && customerList.get(i).getIsMail() == 1){
                customerList.get(i).setType(-1);
            }
        }
        return CustomerList.builder()
                .customerList(customerList)
                .total(customerMapper.countCustomer(name, type))
                .page(page)
                .build();
    }

}
