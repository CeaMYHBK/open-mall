package com.ceam.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author CeaM
 * 2023/03/28 09:38
 **/
public class FileUtil extends cn.hutool.core.io.FileUtil {

    /**
     * 获取文件扩展名，不带 .
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 将文件名解析成文件的上传路径
     */
    public static File upload(MultipartFile file, String filePath) {

        String suffix = getExtensionName(file.getOriginalFilename());
        StringBuffer nowStr = fileRename();
        try {
            String fileName = nowStr + "." + suffix;
            String path = filePath + fileName;
            // getCanonicalFile 可解析正确各种路径
            File dest = new File(path).getCanonicalFile();
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            // 文件写入
            file.transferTo(dest);
            return dest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传文件重命名
     * @return 新的文件名
     */
    public static StringBuffer fileRename() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = sdf.format(new Date());
        StringBuffer buf = new StringBuffer(time);
        Random r = new Random();
        //循环取得三个不大于10的随机整数
        for (int x = 0; x < 3; x++) {
            buf.append(r.nextInt(10));
        }
        return buf;
    }
}
