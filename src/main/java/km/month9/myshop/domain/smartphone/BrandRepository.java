package km.month9.myshop.domain.smartphone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query("select s from Smartphone s inner join Brand b on s.brand = b and b.id =:s")
    List<Smartphone> select(int s);

}
