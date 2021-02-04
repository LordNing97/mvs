package com.xy.mvs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString
@ApiModel(value = "保存客户抽奖参数对象")
public class CustomerLottery {

    @ApiModelProperty(name = "id", value = "id(编辑/删除时使用)", dataType = "int")
    private Integer id;
    @ApiModelProperty(name = "userId", value = "用户id", required = true, dataType = "int")
    private Integer userId;
    @ApiModelProperty(name = "lotteryId", value = "抽奖id", required = true, dataType = "int")
    private Integer lotteryId;
    @ApiModelProperty(name = "points", value = "积分", required = true,  dataType = "int")
    private Integer points;
    @ApiModelProperty(name = "isWin", value = "是否中奖(0:未中奖 1:已中奖)", dataType = "int")
    private Integer isWin;
    @ApiModelProperty(name = "isDesignated", value = "是否被内定(0:未内定 1:已内定)",  dataType = "int")
    private Integer isDesignated;
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "date")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "rechargePoints", value = "总积分", dataType = "int")
    private Integer rechargePoints;
    @ApiModelProperty(name = "numberWinners", value = "中奖次数", dataType = "int")
    private Integer numberWinners;
    @ApiModelProperty(name = "userTelephone", value = "客户手机号", dataType = "string")
    private String userTelephone;

}
