package iyp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import iyp.entity.UserMsg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMsgMapper extends BaseMapper<UserMsg> {
}
