package com.xy.mvs.request;

import com.xy.mvs.model.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/11 13:47
 * @Version 1.0
 */
@Getter
@Builder
@ToString
public class CustomerList {

    List<Customer> customerList;
    int total;
    int page;

}
