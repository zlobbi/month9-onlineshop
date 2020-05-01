package km.month9.myshop.domain.product;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(min = 4, max = 16)
    @Column(length = 128)
    private String name;
    @NotNull
    @Column(length = 128)
    private String icon;
    @NotNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    @OrderBy("name ASC")
    List<Smartphone> smartphones;

//    @Override
//    public String toString() {
//        return String.format("%s, %s, %s",
//                this.name, this.icon, this.products);
//    }
}
