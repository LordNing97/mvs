package com.xy.mvs.mapper;

import com.xy.mvs.model.LotteryOrder;
import com.xy.mvs.request.OrderAndItemInfoList;
import com.xy.mvs.request.OrderExcel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/18 15:16
 * @Version 1.0
 */
@Mapper
public interface LotteryOrderMapper {

    /**
     * 保存抽奖订单
     * @param lotteryOrder
     * @return
     */
    int saveLotteryOrder(LotteryOrder lotteryOrder);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    LotteryOrder getById(Integer id);

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
    int end(@Param("id") Integer id, @Param("endTime") LocalDateTime endTime);

    /**
     * 根据用户ID和状态获取订单数量
     * @param customerId
     * @return
     */
    int countByUserIdAndStatus(@Param("customerId") Integer customerId, @Param("status") Integer status);

    /**
     * 根据用户ID和状态获取订单
     * @param customerId
     * @return
     */
    List<OrderAndItemInfoList> getByUserIdAndStatus(@Param("customerId") Integer customerId, @Param("status") Integer status);

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
