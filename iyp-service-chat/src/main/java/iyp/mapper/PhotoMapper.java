package iyp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import iyp.entity.Photo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PhotoMapper extends BaseMapper<Photo> {
}
