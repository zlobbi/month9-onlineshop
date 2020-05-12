package km.month9.myshop.domain.cart;

import km.month9.myshop.domain.smartphone.Smartphone;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CartAdvice {

    @ModelAttribute(Constants.CART_ID)
    public List<Smartphone> getCartModel(HttpSession session) {
        var list = session.getAttribute(Constants.CART_ID);
        if (list == null) {
            session.setAttribute(Constants.CART_ID, new ArrayList<>());
        }
        return (List<Smartphone>) session.getAttribute(Constants.CART_ID);
    }

}
