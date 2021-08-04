package iyp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import iyp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author JYP
 * @date 2021/7/28
 **/
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {
}
