package km.month9.myshop.domain.cart;

import km.month9.myshop.domain.smartphone.Smartphone;
import km.month9.myshop.domain.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository repository;

    public boolean checkUserCart(int uId) {
        return repository.existsByUser_Id(uId);
    }

    public int getUserCart(int uId) {
        return repository.findByUser_Id(uId).getMySmartphones().size();
    }

    public void saveCart(Cart c) {
        repository.save(c);
    }
}
