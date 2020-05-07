package km.month9.myshop.domain.smartphone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Integer> {
    Page<Smartphone> findAllByBrandContains(Pageable pageable, String brand);
    Page<Smartphone> findAllByNameContains(Pageable pageable, String name);
    Page<Smartphone> findAllByPriceIsLessThanEqual(Pageable pageable, float price);
    Page<Smartphone> findAllByDescriptionContains(Pageable pageabel, String descr);
}
