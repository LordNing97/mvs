package com.xy.mvs.model;

import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString
public class Lottery {

    private Integer id;
    private Integer productTypeId;
    private Integer lotteryProductId;
    private Integer status;
    private LocalDateTime createTime;
    private Integer isDel;

}
