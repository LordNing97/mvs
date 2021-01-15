package com.xy.mvs.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "保存地址参数对象")
public class Address {

    private Integer id;
    private Integer userId;
    private String name;
    private String telephone;
    private String location;
    private String address;
    private Integer isDel;

}
