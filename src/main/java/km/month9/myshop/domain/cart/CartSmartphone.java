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
@Entity
@Table(name = "Cart_Smartphone")
public class CartSmartphone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "smartphone_id")
    private Smartphone smartphone;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "qty")
    private int qty;

    @Column(name = "sum")
    private double sum;

    @Column(name = "session")
    private String session;

}
