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
        ClassPathResource classPathResource = new ClassPathResource("static/download/Activiti 5.4.pdf");

        InputStream is = this.getClass().getClassLoader().getResourceAsStream("static/download/Activiti 5.4.pdf");
        String fileName = classPathResource.getFilename();
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

    /*@RequestMapping(value = "/getBatchImportTemplete", method = RequestMethod.GET)
    public void getBatchImportTemplete(HttpServletResponse res) {

        ClassPathResource classPathResource = new ClassPathResource("static/download/batchEnteringTemplate2.0.xlsx");

        String fileName = classPathResource.getFilename();
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(classPathResource.getFile()));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
