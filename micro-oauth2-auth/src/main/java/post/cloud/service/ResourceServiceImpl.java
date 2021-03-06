package post.cloud.service;

import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import post.cloud.constant.RedisConstant;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
    初始化路径角色权限
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/auth/oauth/*", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/api/*", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/post/*", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/api/hello", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/api/user/currentUser", CollUtil.toList("ADMIN", "USER"));
        resourceRolesMap.put("/post/map/location", CollUtil.toList("ADMIN", "USER"));
        resourceRolesMap.put("/post/map/data", CollUtil.toList("ADMIN", "USER"));
        resourceRolesMap.put("/post/map/*", CollUtil.toList("ADMIN", "USER"));
        resourceRolesMap.put("/spost/*", CollUtil.toList("ADMIN", "USER"));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}

