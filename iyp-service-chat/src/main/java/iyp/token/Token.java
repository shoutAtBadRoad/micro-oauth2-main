package iyp.token;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JYP
 * @date 2021/8/21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    private Long time;

    private String user;

    Token(String s){
        Token token = JSON.parseObject(s, Token.class);
        this.time = token.getTime();
        this.user = token.getUser();
    }
}
