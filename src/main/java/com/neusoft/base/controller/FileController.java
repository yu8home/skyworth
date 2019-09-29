package com.neusoft.base.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.utils.CommUtils;

/**
 * 文件
 *
 * @author：yu8home
 * @date：2018年7月5日 下午12:31:49
 */
@RestController
@RequestMapping("/file")
public class FileController {

    // excel模板文件下载
    @RequestMapping("/xlsDownload/{fileName}")
    public void xlsDownload(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) {
        InputStream in = null;
        OutputStream out = null;
        try {
            File f = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "public/xls/" + fileName);
            in = new FileInputStream(f);
            out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommUtils.close(in, out);
        }
    }

    // 文件下载
    @RequestMapping("/fileDownload/{fileName}")
    public void fileDownload(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) {
        InputStream in = null;
        OutputStream out = null;
        try {
            fileName = URLDecoder.decode(fileName, "UTF-8");
            response.setContentType("multipart/form-data");
            response.setHeader("content-disposition", "attachment;filename=" + fileName);

            in = new FileInputStream(GlobalConst.UPLOAD_ATTACHMENT + fileName);
            out = response.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommUtils.close(in, out);
        }
    }

}