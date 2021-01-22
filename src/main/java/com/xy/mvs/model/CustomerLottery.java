package com.xy.mvs.model;

import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString
public class CustomerLottery {

    private Integer id;
    private Integer userId;
    private Integer lotteryId;
    private Integer points;
    private Integer isWin;
    private Integer isDesignated;
    private LocalDateTime createTime;

}
