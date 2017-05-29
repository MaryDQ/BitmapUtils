package com.phone.bitmaputils.bitmapUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by MLXPHONE on 2017/5/29.
 */

public class MD5Encoder {
    public static String encode(String url) throws NoSuchAlgorithmException {
        MessageDigest md ;
        md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update(url.getBytes());
        System.out.println("aaaaaaaaaaaaaaa:"+url);
        System.out.println("aaaaaaaaaaaaaaa:"+md.digest());
        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        return new BigInteger(1, md.digest()).toString(16);
    }
}
