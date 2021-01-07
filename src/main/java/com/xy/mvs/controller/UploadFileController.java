package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.config.FileConfig;
import com.xy.mvs.exception.FileException;
import com.xy.mvs.util.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2020/4/13 15:33
 * @Version 1.0
 **/
@RestController
@RequestMapping("app/file")
@Validated
@Api(tags = "上传文件接口", produces = "application/json")
public class UploadFileController {

    @Resource
    private FileConfig fileConfig;

    @ApiOperation(value = "上传图片", httpMethod = "POST")
    @PostMapping(value = "uploadPhoto")
    public Result uploadPhoto(MultipartFile files) {
        //判断文件大小
        if (FileUploadUtil.checkImageFileSize(files.getSize())) {
            throw new FileException(ResultCode.FILE_SIZE_OUT);
        }
        String url = FileUploadUtil.saveImageFile(files);
        return Result.builder()
                .data(fileConfig.getImgUrlPrefix() + url)
                .build();
    }

    @ApiOperation(value = "上传图片数组", httpMethod = "POST")
    @PostMapping(value = "uploadPhotos", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Result uploadPhotos(@RequestParam(value = "files", required = true) MultipartFile[] files) {
        List<String> url = new ArrayList<>();
        for(int i = 0;i < files.length;i++){
            //判断文件大小
            if (FileUploadUtil.checkImageFileSize(files[i].getSize())) {
                throw new FileException(ResultCode.FILE_SIZE_OUT);
            }
            url.add(fileConfig.getImgUrlPrefix() + FileUploadUtil.saveImageFile(files[i]));
        }
        return Result.builder()
                .data(url)
                .build();
    }

}
