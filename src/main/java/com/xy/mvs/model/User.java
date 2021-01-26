package com.xy.mvs.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "保存用户参数对象")
public class User {

    private Integer id;
    private String name;
    private String password;
    private Integer role;
    private Integer isDel;

}
