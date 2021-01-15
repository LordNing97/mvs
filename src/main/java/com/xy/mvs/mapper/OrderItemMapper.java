package com.xy.mvs.mapper;

import com.xy.mvs.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

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

    /**
     * 根据订单ID获取订单详情
     * @param orderId
     * @return
     */
    List<OrderItem> getByOrderId(Integer orderId);

    /**
     * 根据订单ID取消订单详情
     * @param orderId
     * @return
     */
    int cancelOrderItem(Integer orderId);

}
