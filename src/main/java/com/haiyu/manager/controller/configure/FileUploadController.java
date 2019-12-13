package com.haiyu.manager.controller.configure;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Random;

@RestController
@Slf4j
public class FileUploadController {

    @RequestMapping("/uploadFile")
    public JSONObject upload(@RequestParam("file") MultipartFile file){
        OutputStream os = null;
        InputStream inputStream = null;
        String filePath=null;
        String path="D:\\file";
        JSONObject resultJSON=new JSONObject();
        String name = file.getOriginalFilename();//上传文件的真实名称
        String suffixName = name.substring(name.lastIndexOf("."));//获取后缀名
        String hash = Integer.toHexString(new Random().nextInt());//自定义随机数（字母+数字）作为文件名
        String fileName = hash + suffixName;
        try {
            inputStream = file.getInputStream();
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            filePath=tempFile.getPath() + File.separator + fileName;
            os = new FileOutputStream(filePath);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (Exception e) {
            log.error("文件上传异常",e);
            resultJSON.put("code",1);
            return resultJSON;
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                log.error("文件流关闭异常",e);
                resultJSON.put("code",1);
                return resultJSON;
            }
        }
        resultJSON.put("filePath",filePath);
        resultJSON.put("size",file.getSize());
        resultJSON.put("code",0);
        return resultJSON;
    }

}
