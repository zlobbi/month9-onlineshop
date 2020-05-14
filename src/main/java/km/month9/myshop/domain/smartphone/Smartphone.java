package km.month9.myshop.domain.smartphone;

import km.month9.myshop.domain.cart.Cart;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@Table(name = "smartphones")
public class Smartphone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(min = 4)
    @Column(length = 128)
    private String name;
    @NotBlank
    @Column(length = 128)
    private String image;
    @NotNull
    @Column(length = 128)
    private String description;
    @PositiveOrZero
    @Column(length = 128)
    private int qty;
    @Positive
    @Column
    private double price;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToMany(mappedBy = "mySmartphones")
    List<Cart> carts;


    @Override
    public String toString() {
        return String.format("id=%d, name=%s, image=%s, description=%s, qty=%d, price=%.2f", this.id,
                 this.name, this.image, this.description, this.qty, this.price);
    }

}
