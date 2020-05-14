package km.month9.myshop.domain.cart;

import km.month9.myshop.domain.smartphone.Smartphone;
import km.month9.myshop.domain.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@ToString
@NoArgsConstructor
@Entity(name = "CartEntity")
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private User user;

    @Column
    @NotBlank
    private String session;

    @ManyToMany
    @JoinTable(
            name = "cart_smartphone",
            joinColumns = @JoinColumn(referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    List<Smartphone> mySmartphones;

    @Override
    public String toString() {
        return String.format("%s %s %s", this.user, this.session, this.mySmartphones);
    }

}
