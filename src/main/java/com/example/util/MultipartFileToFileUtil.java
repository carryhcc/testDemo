package com.example.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/7/23 15:24
 */
@Slf4j
public class MultipartFileToFileUtil {
    /**
     * MultipartFile转换到File
     *
     * @param multipartFile
     * @return
     */
    public static File multipartFileToFile(MultipartFile multipartFile) {
        //文件上传前的名称
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(multipartFile.getName());
        OutputStream out = null;
        try {
            //获取文件流，以文件流的方式输出到新文件
            //putStream in = multipartFile.getInputStream();
            out = new FileOutputStream(file);
            byte[] ss = multipartFile.getBytes();
            for (int i = 0; i < ss.length; i++) {
                out.write(ss[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 转换完成后删除生成文件
     *
     * @param file
     */
    public static void fIleDelete(File file) {
        File f = new File(file.toURI());
        if (f.delete()) {
            log.info("缓存文件删除成功");
        } else {
            log.info("缓存文件删除失败");
        }
    }

    public static File mTof(MultipartFile multipartFile) {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
