package km.month9.myshop.domain.user;

import km.month9.myshop.domain.cart.Cart;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity(name = "UserEntity")
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(min = 4, message = "Login must contain min 4 symbols")
    @Column(length = 128)
    private String login;
    @NotBlank
    @Column(length = 128)
    private @Email String email;
    @NotBlank
    @Column(length = 128)
    @Size(min = 6, message = "Password must contain min 6 symbols")
    private String password;
    @Column
    @Builder.Default
    private boolean enabled = true;
    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    @Builder.Default
    private String role = "USER";
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;
}
