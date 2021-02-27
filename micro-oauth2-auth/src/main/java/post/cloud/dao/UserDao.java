package post.cloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import post.cloud.domain.UserIf;

@Mapper
@Component
public interface UserDao {

    UserIf getUserByName(@Param("username")String username);

    UserIf getUserByMobile(@Param("mobile")String mobile);

}
