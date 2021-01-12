package com.xy.mvs.mapper;

import com.xy.mvs.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 陈璇
 * @Date 2021/1/12 16:03
 * @Version 1.0
 */
@Mapper
public interface OrderItemMapper {

    /**
     * 保存订单详情
     * @param orderItem
     * @return
     */
    int saveOrderItem(OrderItem orderItem);

}
