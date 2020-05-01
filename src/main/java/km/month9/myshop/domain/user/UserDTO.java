package km.month9.myshop.domain.user;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class UserDTO {
    private int id;
    private String login;
    private String email;

    static UserDTO from(User user) {
        return builder().id(user.getId())
                .email(user.getEmail())
                .login(user.getLogin())
                .build();

    }

}
