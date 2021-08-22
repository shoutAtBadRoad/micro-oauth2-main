package iyp.token;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author JYP
 * @date 2021/8/21
 **/

public class TokenWorker implements TokenBase64{

    private static TokenWorker instance = null;

    private static String salt = "loveloveloveMe";

    private TokenWorker(){

    }

    synchronized public static TokenWorker getInstance(){
        if(instance==null){
            instance = new TokenWorker();
        }
        return instance;
    }

    @Override
    public String encode(String user) {
        Map<String,Object> map = new HashMap<>();
        map.put("time", DateUtil.currentSeconds());
        map.put("user",user);

        String jsonString = JSONObject.toJSONString(map);
        String s = Base64.encode(jsonString);
        int i = new Random().nextInt(s.length());
        return s.substring(0,i) + salt + s.substring(i);
    }

    @Override
    public Token decode(String secret) {
        String s = secret.replaceAll(salt, "");
        return new Token(Base64.decodeStr(s));
    }
}
