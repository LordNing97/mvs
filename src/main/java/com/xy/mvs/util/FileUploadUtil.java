package com.xy.mvs.util;

import com.xy.mvs.config.FileConfig;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

/**
 * @Author 陈璇
 * @Date 2020/4/13 15:29
 * @Version 1.0
 **/
public class FileUploadUtil {

    private static final String[] imgTypes = {"png", "jpeg", "jpg"};

    private static final int IMAGE_MAX_SIZE = 50;

    private static final int FILE_MAX_SIZE = 50;

    private static final String UNIT = "M";

    private static FileConfig fileConfig = SpringBeanUtil.getBean(FileConfig.class);

    /**
     * 保存文件
     */
    public static String saveFile(MultipartFile file, String folder) {
        String fileName = file.getOriginalFilename();
        //文件类型
        int lastIndexOf = fileName.lastIndexOf(".");
        String fileType = fileName.substring(lastIndexOf > 0 ? lastIndexOf : fileName.length());
        //文件路径
        String newFileName = UUIDUtil.randomUUID() + fileType;
        //创建目录
        File newFolder = new File(folder);
        //如果目录不存在，则创建目录
        if (!newFolder.exists()) {
            newFolder.mkdirs();
        }
        //创建目标文件
        File resultFile = new File(newFolder, newFileName);
        try {
            file.transferTo(resultFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return newFileName;
    }

    public static String saveImageFile(MultipartFile file) {
        return saveFile(file, fileConfig.getImgPath());
    }

    /**
     * 检查文件大小
     */
    public static boolean checkImageFileSize(Long len) {
        double fileSize = getFileSize(len);
        if (fileSize > IMAGE_MAX_SIZE) {
            return true;
        }
        return false;
    }

    public static boolean checkFileSize(Long len) {
        double fileSize = getFileSize(len);
        if (fileSize > FILE_MAX_SIZE) {
            return true;
        }
        return false;
    }

    private static double getFileSize(Long len) {
        double fileSize = 0;
        if ("B".equals(UNIT.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(UNIT.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(UNIT.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(UNIT.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        return fileSize;
    }

    /**
     * 检查文件类型
     */
    public static boolean checkImageFileType(String fileName) {
        for (int i = 0; i < imgTypes.length; i++) {
            if (fileName.endsWith(imgTypes[i])) {
                return true;
            }
        }
        return false;
    }

}
