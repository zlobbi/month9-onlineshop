package km.month9.myshop.domain.smartphone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Integer> {
    Page<Smartphone> findAllByBrand_Name(Pageable pageable, String brand);
    Page<Smartphone> findAllByNameContains(Pageable pageable, String name);
    Page<Smartphone> findAllByPriceIsLessThanEqual(Pageable pageable, double price);
    Page<Smartphone> findAllByDescriptionContains(Pageable pageable, String description);

    @Query("select s from Smartphone s inner join CartSmartphone sc on sc.smartphone = s inner join Cart c on c.user.id =:userId")
    List<Smartphone> findAllByUserId(int userId);

}
