package km.month9.myshop.domain.cart;

import km.month9.myshop.domain.smartphone.Smartphone;
import km.month9.myshop.domain.smartphone.SmartphoneRepository;
import km.month9.myshop.domain.smartphone.SmartphoneService;
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
    private final CartSmartphoneRepository repo;
    private final SmartphoneService service;
    private final CartService cService;

    @GetMapping("/cart")
    public String cart(Model model, @SessionAttribute(name = Constants.CART_ID, required = false)
            List<Smartphone> cart, HttpServletRequest request) {
        var user = userRepository.findByEmail(request.getUserPrincipal().getName()).get();
        if (user != null) {
            model.addAttribute("cartItems", service.getUserCart(user.getId()));
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
        var user = userRepository.findByEmail(uriBuilder.getUserPrincipal().getName()).get();
        if(!cService.checkUserCart(user.getId())) {
            session.removeAttribute(Constants.CART_ID);
            Cart c = new Cart();
            c.setUser(userRepository.findByEmail(uriBuilder.getUserPrincipal().getName()).get());
            c.setSession(session.getId());
            cartRepository.save(c);
        }
        var cart = cService.getUserCart(user);
        CartSmartphone sc = new CartSmartphone();
        sc.setCart(cart);
        sc.setSession(session.getId());
        sc.setSmartphone(repository.findById(sId).get());
        repo.save(sc);

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
        return "redirect:/";
    }

    @PostMapping("/cart/buy")
    public String buy(HttpSession session, HttpServletRequest request) {
       session.removeAttribute(Constants.CART_ID);
       var user = userRepository.findByEmail(request.getUserPrincipal().getName()).get();
        if (cService.checkUserCart(user.getId())) {
            cartRepository.delete(cService.getUserCart(user));
            return "redirect:/cart/feedback";
        }
        return "redirect:/";
    }

    @GetMapping("/cart/feedback")
    public String feedback() {
        return "review";
    }

    @PostMapping("cart/feedback")
    public String feedback(HttpSession session) {
        return "redirect:/";
    }


    // в идеале это должно быть @DeleteMapping, но web-формы не поддерживают
    // запросы с методами отличными от get и post
    // по этому у нас адрес метода немного "странный" :)
    @PostMapping("/cart/empty")
    public String emptyCart(HttpSession session, HttpServletRequest request) {
        session.removeAttribute(Constants.CART_ID);
        var user = userRepository.findByEmail(request.getUserPrincipal().getName()).get();
        if (cService.checkUserCart(user.getId())) {
            cartRepository.delete(cService.getUserCart(user));
        }
        return "redirect:/cart";
    }
}
