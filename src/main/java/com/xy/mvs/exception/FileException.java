package com.xy.mvs.exception;


import com.xy.mvs.api.ResultCode;

/**
 * @Author 陈璇
 * @Date 2020/4/13 15:28
 * @Version 1.0
 **/
public class FileException extends ServiceException{

    public FileException(String message) {
        super(message);
    }

    public FileException(ResultCode resultCode) {
        super(resultCode);
    }

}
