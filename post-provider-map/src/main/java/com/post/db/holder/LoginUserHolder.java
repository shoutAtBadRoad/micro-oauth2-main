package com.post.db.holder;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.post.db.daomp.StationManagerMapper;
import com.post.db.daomp.UserMapper;
import com.post.db.entity.User;
import com.post.db.entity.UserDTO;
import com.post.db.entityMp.StationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.support.ServletContextApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginUserHolder {

    @Resource
    private StationManagerMapper stationManagerMapper;
    @Resource
    private UserMapper userMapper;

    @Autowired
    private ApplicationContext applicationContext;

    public Map<String,Object> getCurrentUser(){
        StationManagerMapper mapper = applicationContext.getBean("stationManagerMapper",StationManagerMapper.class);
        Map<String,Object> map = new HashMap<>();
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(userStr);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userJsonObject.getStr("user_name"));
        userDTO.setId(Convert.toLong(userJsonObject.get("id")));
        userDTO.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
        map.put("userDTO",userDTO);
        StationManager stationManager = mapper.selectOne(new QueryWrapper<StationManager>().eq("name", userJsonObject.getStr("user_name")));
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", Convert.toLong(userJsonObject.get("id"))));
        if(stationManager!=null){
            map.put("station",stationManager.getStation());
        }else {
            map.put("station",-1);
        }
        if(user!=null){
            map.put("mobile",user.getMobile());
        }else {
            map.put("mobile","NO MOBILE");
        }
        return map;
    }
}
