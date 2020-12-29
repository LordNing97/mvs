package com.xy.mvs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "保存用户参数对象")
public class User {

    @ApiModelProperty(name = "id", value = "id(编辑/删除时使用)", dataType = "int")
    private Integer id;
    @ApiModelProperty(name = "name", value = "用户名", required = true, dataType = "string")
    private String name;
    @ApiModelProperty(name = "password", value = "密码", required = true, dataType = "string")
    private String password;
    @ApiModelProperty(name = "role", value = "权限(0:系统管理员 1:抽奖 2:商城 3:抽奖+抽奖设置 4:抽奖+商城)", required = true, dataType = "int")
    private Integer role;
    @ApiModelProperty(name = "isDel", value = "是否删除(0:未删除 1:已删除)", dataType = "int")
    private Integer isDel;

}
