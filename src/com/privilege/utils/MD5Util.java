package com.privilege.utils;

import com.mchange.util.Base64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public  class MD5Util {

    private static String util="!@#$%^&*()_+!@#$%^&*()_+!@#$%^&*()_+!@#$%^&*()_+!@#$%^&*()_+";
    public static String MD5Encoding(String pass) {
        //初始化MD5
        MessageDigest md5=null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //创建base64位的编码格式
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密
        String encode = null;
        try {
            pass=util+pass;
            encode = base64Encoder.encode(md5.digest( pass.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encode;
    }
}
