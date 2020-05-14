package km.month9.myshop.domain.cart;

import km.month9.myshop.domain.smartphone.Smartphone;
import km.month9.myshop.domain.smartphone.SmartphoneRepository;
import km.month9.myshop.domain.user.User;
import km.month9.myshop.domain.user.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class CartController {
    private final SmartphoneRepository repository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @GetMapping("/cart")
    public String cart(Model model, @SessionAttribute(name = Constants.CART_ID, required = false) List<Smartphone> cart) {
        if (cart != null) {
            model.addAttribute("cartItems", cart);
        }
        return "cart";
    }

    // это метод для асинхронных запросов
    // демонстрация добавления в "корзину" через параметр @SessionAttribute cart_model
    @PostMapping("/cart")
    @ResponseBody
    public boolean addToListRest(@RequestParam String value, @SessionAttribute(name = Constants.CART_ID, required = false) List<Smartphone> cart) {
        if (cart != null) {
            cart.add(repository.findById(Integer.parseInt(value)).get());
        }

        return true;
    }

    // метод для добавления в "корзину" через форму
    // демонстрация добавления через объект HttpSession session
    @PostMapping("/cart/add")
    public String addToList(@RequestParam String value, HttpSession session, HttpServletRequest uriBuilder) {
        int sId = Integer.parseInt(value);
        Cart c = new Cart();
        c.setSession(session.getId());

        if(userRepository.findByEmail(uriBuilder.getUserPrincipal().getName()) != null) {
            c.setUser(userRepository.findByEmail(uriBuilder.getUserPrincipal().getName()).get());
            cartRepository.save(c);

        }

        if (session != null) {
            var attr = session.getAttribute(Constants.CART_ID);
            if (attr == null) {
                session.setAttribute(Constants.CART_ID, new ArrayList<Smartphone>());
            }
            try {
                var list = (List<Smartphone>) session.getAttribute(Constants.CART_ID);
                list.add(repository.findById(sId).get());
            } catch (ClassCastException ignored) {

            }
        }

//        cartRepository.save(c);
        return "redirect:/";
    }

    // в идеале это должно быть @DeleteMapping, но web-формы не поддерживают
    // запросы с методами отличными от get и post
    // по этому у нас адрес метода немного "странный" :)
    @PostMapping("/cart/empty")
    public String emptyCart(HttpSession session) {
        session.removeAttribute(Constants.CART_ID);

        return "redirect:/cart";
    }
}
