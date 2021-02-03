package com.xy.mvs.mapper;

import com.xy.mvs.model.Lottery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/22 15:07
 * @Version 1.0
 */
@Mapper
public interface LotteryMapper {

    /**
     * 保存抽奖
     * @param lottery
     * @return
     */
    int saveLottery(Lottery lottery);

    int count(@Param("productName") String productName, @Param("status") Integer status,
              @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    /**
     * 分页获取抽奖
     * @param productName
     * @param startTime
     * @param endTime
     * @param page
     * @param size
     * @return
     */
    List<Lottery> getLotteryList(@Param("productName") String productName, @Param("status") Integer status,
                                 @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,
                                 @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 修改删除状态
     * @param lotteryProductId
     * @return
     */
    int modifyIsDel(@Param("lotteryProductId") Integer lotteryProductId, @Param("isDel") Integer isDel);

}
