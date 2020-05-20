package km.month9.myshop.domain.cart;

import km.month9.myshop.domain.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository repository;

    public boolean checkUserCart(int uId) {
        return repository.existsByUser_Id(uId);
    }

    public int getUserCart(int uId) {
        return repository.findAllByUser_Id(uId).size();
    }

    public Cart getUserCart(User user) {
        return repository.findByUser_Id(user.getId());
    }

    public void saveCart(Cart c) {
        repository.save(c);
    }


}
