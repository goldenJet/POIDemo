package com.example.demo.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jet.chen on 2017/7/21.
 */
@Controller
@RequestMapping("/uploadAndDownload")
public class UploadAndDownload {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());
        return file.getOriginalFilename();
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download(){
        String fileName = "Activiti 5.4.pdf";
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("static/download/" + fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + fileName);
        headers.add("content-type", "application/octet-stream");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(is));
    }

    @RequestMapping(value = "/download2", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download2() {
        String fileName = "Activiti 5.4.pdf";
        ClassPathResource classPathResource = new ClassPathResource("static/download/" + fileName);
        InputStream is = null;
        try {
            is = classPathResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + fileName);
        headers.add("content-type", "application/octet-stream");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(is));
    }
}
