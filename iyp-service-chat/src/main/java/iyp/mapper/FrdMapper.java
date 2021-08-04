package iyp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import iyp.entity.iFriend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FrdMapper extends BaseMapper<iFriend> {

    @Select("select idifriend,host,friend,u.nickName friendName,chatId from ifriend,iuser u " +
            "where host=#{id} and friend=u.id ")
    List<iFriend> getFrdList(int id);
}
