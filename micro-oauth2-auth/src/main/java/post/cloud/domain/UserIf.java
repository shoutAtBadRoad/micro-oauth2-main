package post.cloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIf {

    private long id;

    private String username;

    private String password;

    private String role;

    private String code;
}
