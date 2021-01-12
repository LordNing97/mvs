package com.xy.mvs.service;

import com.xy.mvs.mapper.OrderItemMapper;
import com.xy.mvs.model.OrderItem;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2021/1/12 16:04
 * @Version 1.0
 */
@Service
public class OrderItemService {

    @Resource
    private OrderItemMapper orderItemMapper;

    /**
     * 保存订单详情
     * @param orderItem
     * @return
     */
    public boolean saveOrderItem(OrderItem orderItem){
        orderItem.setIsDel(0);
        return orderItemMapper.saveOrderItem(orderItem) > 0;
    }

}
