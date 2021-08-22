package iyp.token;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @author JYP
 * @date 2021/8/21
 **/

public class TokenBuilder {

    private static final TokenWorker worker = TokenWorker.getInstance();

    public static String encode(String user){
        return worker.encode(user);
    }

    public static Token decode(String secret){
        return worker.decode(secret);
    }
}
