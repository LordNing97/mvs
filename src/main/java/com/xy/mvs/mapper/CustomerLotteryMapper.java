package com.xy.mvs.mapper;

import com.xy.mvs.model.CustomerLottery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/22 16:28
 * @Version 1.0
 */
@Mapper
public interface CustomerLotteryMapper {

    /**
     * 保存
     * @param customerLottery
     * @return
     */
    int saveCustomerLottery(CustomerLottery customerLottery);

    /**
     * 批量保存
     * @param customerLotteryList
     * @return
     */
    int saveBatchCustomerLottery(@Param("customerLotteryList") List<CustomerLottery> customerLotteryList);

    int count(Integer lotteryId);

    /**
     * 分页获取
     * @param lotteryId
     * @param page
     * @param size
     * @return
     */
    List<CustomerLottery> getCustomerLotteryList(@Param("lotteryId") Integer lotteryId,
                                                 @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 根据抽奖Id获取所有积分
     * @param lotteryId
     * @return
     */
    Integer sumPoints(Integer lotteryId);

    /**
     * 修改内定
     * @param id
     * @param isDesignated
     * @return
     */
    Integer modifyIsDesignated(@Param("id") Integer id, @Param("isDesignated") Integer isDesignated);

}
