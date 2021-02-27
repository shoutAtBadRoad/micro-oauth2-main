package com.post.db.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;


@Slf4j
public class CharacterUtil {
    private static final String ENCODING_TYPE = "UTF-8";

    /**
     * 将目标字符转成base64编码
     * @param target
     * @return
     */
    public static String enCodeStringToBase64(String target){
        try {
            if (target==null)    return null;
            return new Base64().encodeToString(target.getBytes(ENCODING_TYPE));
        } catch (UnsupportedEncodingException e) {
            log.error("转base64编码出现异常,target:{}",target,e);
            return null;
        }
    }

    /**
     * Base64解码
     * @param target
     * @return
     */
    public static String decodeBase64String(String target){
        try {
            if (target==null)    return null;
            return new String(new Base64().decode(target), ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            log.error("base64解码出现异常,target:{}",target,e);
            return null;
        }
    }

}
