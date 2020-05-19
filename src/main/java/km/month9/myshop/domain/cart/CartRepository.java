package km.month9.myshop.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    boolean existsByUser_Id(int userId);

    List<Cart> findAllByUser_Id(int userId);

    void deleteByUser_id(int id);
}
