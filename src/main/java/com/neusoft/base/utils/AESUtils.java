package com.neusoft.base.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * AES-对称加密
 *
 * @author：yu8home
 * @date：2017年4月6日 下午2:37:08
 */
public class AESUtils {
    public static SecretKeySpec skey;
    public static Cipher cipher;

    static {
        try {
            String aes_key = PropUtils.getProperty("aes_key");// 密钥
            skey = new SecretKeySpec(aes_key.getBytes("UTF-8"), "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (Exception e) {
            throw new RuntimeException("init.static异常：" + e);
        }
    }

    // 加密
    public static String encode(String msg) {
        byte[] crypted = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(msg.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException("encode.static异常：" + e);
        }
        return new String(Base64.encodeBase64(crypted));
    }

    // 解密
    public static String decode(String msg) {
        byte[] output = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base64.decodeBase64(msg));
        } catch (Exception e) {
            throw new RuntimeException("decode.static异常：" + e);
        }
        return new String(output);
    }

    public static void main(String[] args) {
        String str = "中华人民共和国万岁";
        String encStr = AESUtils.encode(str);
        System.out.println("密文：" + encStr);
        System.out.println("明文：" + AESUtils.decode(encStr));
    }

}