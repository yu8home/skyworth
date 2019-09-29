package com.neusoft.base.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class ImageToBase64 {

    /**
     * 通过图片的url获取图片的base64字符串
     * 
     * @param imgUrl
     *            图片url
     * @return 返回图片base64的字符串
     */
    public static String image2Base64(String imgUrl) {
        URL url = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        HttpURLConnection httpUrl = null;

        try {
            url = new URL(imgUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();

            in = httpUrl.getInputStream();
            out = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            BASE64Encoder decoder = new BASE64Encoder();
            String encodeStr = decoder.encode(out.toByteArray());
            return Pattern.compile("[\n-\r]").matcher(encodeStr).replaceAll("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }
        return imgUrl;
    }

    public static void main(String[] args) {
        String imageUrl = "https://www.baidu.com/img/bd_logo1.png";
        String imageStr = ImageToBase64.image2Base64(imageUrl);
        System.out.println(imageStr);
    }

}