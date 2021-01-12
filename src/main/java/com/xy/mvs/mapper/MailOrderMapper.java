package com.xy.mvs.mapper;

import com.xy.mvs.model.MailOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 陈璇
 * @Date 2021/1/12 11:17
 * @Version 1.0
 */
@Mapper
public interface MailOrderMapper {

    /**
     * 保存商城订单
     * @param mailOrder
     * @return
     */
    int saveMailOrder(MailOrder mailOrder);

}
