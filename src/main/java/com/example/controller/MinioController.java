package com.example.controller;

import com.example.model.Result;
import com.example.util.MinioService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/1/25 16:06
 */
@Slf4j
@RestController
@RequestMapping("/minio")
public class MinioController {

    final
    MinioService minioService;
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.bucketName}")
    private String bucketName;

    public MinioController(MinioService minioService) {
        this.minioService = minioService;
    }

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", minioService.listObjects());
        return "list";
    }

    /**
     * 删除
     *
     * @param filename
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String filename) {
        minioService.deleteObject(filename);
        return Result.success(filename + "删除成功");
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        //得到文件流
        InputStream is = file.getInputStream();
        //源文件名
        String fileName = file.getOriginalFilename();
        //存储到minio的文件名
        String newFileName = System.currentTimeMillis() + "." + StringUtils.substringAfterLast(fileName, ".");
        //类型
        String contentType = file.getContentType();
        minioService.uploadObject(is, newFileName, contentType);
        //返回下载地址
        return Result.success(endpoint + "/" + bucketName + "/" + newFileName);
    }

    /**
     * 下载minio服务的文件
     *
     * @param filename
     * @param response
     */
    @GetMapping("/download")
    public void download(String filename, HttpServletResponse response) {
        try {
            InputStream fileInputStream = minioService.getObject(filename);
            response.setHeader("Content-Disposition", "attachment;filename=" + "test.jpg");
            response.setContentType("application/force-download");
            response.setCharacterEncoding("UTF-8");
            IOUtils.copy(fileInputStream, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载失败");
        }
    }

    /**
     * 获取minio文件的下载地址
     *
     * @param filename
     * @return
     */
    @GetMapping("/getHttpUrl")
    @ResponseBody
    public Result<String> getHttpUrl(String filename) {
        String url = minioService.getObjectUrl(filename);
        return Result.success(url);
    }
}
