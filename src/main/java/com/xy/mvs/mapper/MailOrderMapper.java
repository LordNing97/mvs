package com.xy.mvs.mapper;

import com.xy.mvs.model.MailOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;

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

    /**
     * 根据id获取
     * @param id
     * @return
     */
    MailOrder getById(Integer id);

    /**
     * 付款
     * @param id
     * @param paymentTime
     * @return
     */
    int payment(@Param("id") String id, @Param("paymentTime") LocalDateTime paymentTime,
                @Param("payment") Double payment, @Param("freight") Double freight, @Param("cutMoney") Double cutMoney,
                @Param("status") Integer status);

    /**
     * 发货
     * @param id
     * @param consignTime
     * @return
     */
    int consign(@Param("id") String id, @Param("consignTime") LocalDateTime consignTime);

    /**
     * 交易完成
     * @param id
     * @param endTime
     * @return
     */
    int end(@Param("id") String id, @Param("endTime") LocalDateTime endTime);

    /**
     * 退款
     * @param id
     * @return
     */
    int refund(@Param("id") String id, @Param("refundTime") LocalDateTime refundTime);

    /**
     * 取消订单
     * @param id
     * @return
     */
    int cancelOrder(String id);

    /**
     * 评价订单
     * @param id
     * @return
     */
    int evaluateOrder(String id);

    /**
     * 根据用户ID和状态获取订单数量
     * @param userId
     * @return
     */
    int countByUserIdAndStatus(@Param("userId") String userId, @Param("status") Integer status);

    /**
     * 根据用户ID和状态获取订单
     * @param userId
     * @return
     */
    List<OrderAndItemInfoList> getByUserIdAndStatus(@Param("userId") String userId, @Param("status") Integer status);

    /**
     * 根据ID修改订单编号
     * @param id
     * @param orderNumber
     * @return
     */
    int modifyOrderNumber(@Param("id") String id, @Param("orderNumber") String orderNumber);

    int countOrderByStatus(Integer status);

    /**
     * 分页获取订单
     * @param status
     * @param page
     * @param size
     * @return
     */
    List<OrderAndItemInfoList> getOrderByStatus(@Param("status") Integer status, @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 导出待发货订单
     * @return
     */
    List<OrderExcel> listOrderExcel();

    /**
     * 自动完成订单
     * @param endTime
     * @return
     */
    Integer autoEndOrder(LocalDateTime endTime);

}
