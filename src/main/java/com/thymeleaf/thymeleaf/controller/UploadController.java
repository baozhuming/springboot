package com.thymeleaf.thymeleaf.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 文件上传页面
 */
@Controller
public class UploadController {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    String formatStr =formatter.format(new Date());
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody JSONObject upload(MultipartFile file, ServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String aa = request.getParameter("aa");
        System.out.println("获取表单提交的值"+aa);
        try{
            if(file.isEmpty()){
                jsonObject.put("result",-1);
            }
            //后期可以加用户在时间前面,文件名称需要用GUID来生成
            File filePath = new File("../testFile/"+formatStr+"/");

            filePath = new File("../testFile/"+formatStr+"/"+file.getOriginalFilename());
            FileUtils.writeByteArrayToFile(filePath,file.getBytes());
            jsonObject.put("result",1);
            jsonObject.put("message","ok");

        }catch (Exception e){
            jsonObject.put("result",0);
            jsonObject.put("message",e.getCause().getMessage());
        }
        return jsonObject;

    }


    // 判断文件夹是否存在
    public void judeDirExists(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }
    }
}
