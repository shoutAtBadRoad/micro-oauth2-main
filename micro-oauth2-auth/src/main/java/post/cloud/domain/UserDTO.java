package post.cloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private List<String> roles;

    public UserDTO(UserIf userIf){
        this.username = userIf.getUsername();
        this.id = userIf.getId();
        this.roles = Collections.singletonList(userIf.getRole());
    }
}
