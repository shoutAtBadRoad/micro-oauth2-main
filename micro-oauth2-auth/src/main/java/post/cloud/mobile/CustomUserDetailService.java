package post.cloud.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Service;
import post.cloud.dao.UserDao;
import post.cloud.domain.SecurityUser;
import post.cloud.domain.UserDTO;
import post.cloud.domain.UserIf;

import javax.annotation.Resource;
import java.util.Collections;

@Service("CustomUserDetailService")
public class CustomUserDetailService{

    @Resource
    private UserDao userDao;

    UserDetails loadByPhone(String phone, String code) {
        UserIf userIf = userDao.getUserByMobile(phone);
        //在验证手机号和验证码
        if (!userIf.getCode().equals(code)) {
            throw new InvalidGrantException("验证码错误或已过期");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userIf.getUsername());
        userDTO.setPassword(userIf.getPassword());
        userDTO.setId(userIf.getId());
        userDTO.setRoles(Collections.singletonList(userIf.getRole()));
        userDTO.setStatus(1);
        return new SecurityUser(userDTO);
    }
}
