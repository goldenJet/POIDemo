package com.example.demo.controller;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.util.Iterator;

/**
 * Created by jet.chen on 2017/7/21.
 */
@Controller
@RequestMapping("/uploadAndDownload")
public class UploadAndDownload {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());

        /******************************** 解析Excel *********************************/

        // 读取文件的第一张 sheet
        Iterator<Row> rowIterator = null;
        try {
            InputStream inputStream = file.getInputStream();
            if (file.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$")) {
                // 07版
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
                if (xssfSheet != null){
                    rowIterator = xssfSheet.iterator();
                }
            }
            if (file.getOriginalFilename().matches("^.+\\.(?i)(xls)$")) {
                // 03版
                HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
                if (hssfSheet != null){
                    rowIterator = hssfSheet.iterator();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            // 第一列
            String firstCell = getCellValue(row, 0);
        }

        /****************************************************************************/

        return originalFilename;
    }

    /**
     * 获取列数据
     */
    private String getCellValue(Row row, int rowIndex){
        String value;
        Cell cell = row.getCell(rowIndex);
        if (cell == null) {
            value = null;
        } else {
            int cellType = cell.getCellType();
            switch (cellType) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue() == null ? null : cell.getStringCellValue().trim();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    value = Long.toString((long) cell.getNumericCellValue());
                    break;
                default:
                    value = null;
                    break;
            }
        }
        return value;
    }


    /**
     * 完美下载
     */
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

    /**
     * 有缺陷的下载方式
     */
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
