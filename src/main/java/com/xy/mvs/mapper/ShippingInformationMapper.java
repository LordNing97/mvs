package com.xy.mvs.mapper;

import com.xy.mvs.model.ShippingInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 陈璇
 * @Date 2021/1/15 10:16
 * @Version 1.0
 */
@Mapper
public interface ShippingInformationMapper {

    /**
     * 保存运送信息
     * @param shippingInformation
     * @return
     */
    int saveShippingInformation(ShippingInformation shippingInformation);

    /**
     * 根据订单ID获取运送信息
     * @param orderId
     * @return
     */
    ShippingInformation getByOrderId(Integer orderId);

}
