package com.xy.mvs.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author 陈璇
 * @Date 2020/4/13 15:25
 * @Version 1.0
 **/
@Data
@ToString
@Component
public class FileConfig {

    @Value("${file.imgUrlPrefix}")
    private String imgUrlPrefix;
    @Value("${file.imgPath}")
    private String imgPath;

}
