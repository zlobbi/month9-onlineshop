package km.month9.myshop.domain.cart;

import km.month9.myshop.domain.smartphone.Smartphone;
import km.month9.myshop.domain.smartphone.SmartphoneRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class CartController {
    private final SmartphoneRepository repository;

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
            Smartphone s = repository.findById(Integer.parseInt(value)).get();
            cart.add(s);
        }

        return true;
    }

    // метод для добавления в "корзину" через форму
    // демонстрация добавления через объект HttpSession session
    @PostMapping("/cart/add")
    public String addToList(@RequestParam String value, HttpSession session) {
        int sId = Integer.parseInt(value);
        Smartphone s = repository.findById(sId).get();
        if (session != null) {
            var attr = session.getAttribute(Constants.CART_ID);
            if (attr == null) {
                session.setAttribute(Constants.CART_ID, new ArrayList<Smartphone>());
            }
            try {
                var list = (List<Smartphone>) session.getAttribute(Constants.CART_ID);
                list.add(s);
            } catch (ClassCastException ignored) {

            }
        }

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
