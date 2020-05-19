package km.month9.myshop.frontend;

import km.month9.myshop.domain.cart.KeyValueRequestDto;
import km.month9.myshop.domain.exception.ResourceNotFoundException;
import km.month9.myshop.domain.smartphone.BrandRepository;
import km.month9.myshop.domain.smartphone.SearchForm;
import km.month9.myshop.domain.smartphone.Smartphone;
import km.month9.myshop.domain.smartphone.SmartphoneService;
import km.month9.myshop.domain.user.PasswordResetRepository;
import km.month9.myshop.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping
public class FrontendController {

    private String text = "";
    private String param = "";

    @Autowired
    private SmartphoneService service;
    @Autowired
    private PropertiesService propertiesService;
    @Autowired
    private UserService userService;
    @Autowired
    private BrandRepository prT;

    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("smartphones", list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }

    @GetMapping
    public String index(Model model, Pageable pageable, HttpServletRequest uriBuilder, HttpSession session) {
        var map = new HashMap<String, Object>();
        
        map.put("Идентификатор сессии", session.getId());

        session.getAttributeNames()
                .asIterator()
                .forEachRemaining(key -> map.put(key, session.getAttribute(key).toString()));

        model.addAttribute("sessionAttributes", map);

        List<Smartphone> list = (List<Smartphone>) session.getAttribute("_cart_");
        if(list.size() != 0) {
            model.addAttribute("cart", list.size());
        }


        var smartphones = service.getSmartphones(pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(smartphones, propertiesService.getDefaultPageSize(), model, uri);
        if(uriBuilder.getUserPrincipal() != null) {
            var user = userService.getByEmail(uriBuilder.getUserPrincipal().getName());
            model.addAttribute("dto", user);
//            if(cartService.checkUserCart(user.getId())) {
//                model.addAttribute("cart", cartService.getUserCart(user.getId()));
//            }
        }
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model, Pageable pageable, HttpServletRequest uriBuilder,
                         SearchForm form) {
        if(!model.containsAttribute("form")) {
            model.addAttribute("from", new SearchForm());
        }
        if(!form.getText().equals("")) {
            text = form.getText();
            param = form.getParam();
        }
        var uri = uriBuilder.getRequestURI();
        var result = service.searchSmartphones(pageable, param, text);
        constructPageable(result, propertiesService.getDefaultPageSize(), model, uri);
        if(uriBuilder.getUserPrincipal() != null) {
            var user = userService.getByEmail(uriBuilder.getUserPrincipal().getName());
            model.addAttribute("dto", user);
        }
        return "search";
    }

    @GetMapping("/invalidate")
    public String invalidate(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @PostMapping("/store")
    public String store(KeyValueRequestDto data, HttpSession session) {
        if (session != null) {
            var attr = session.getAttribute(data.getKey());
            if (attr == null) {
                session.setAttribute(data.getKey(), data.getValue());
            }
        }

        return "redirect:/";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(ResourceNotFoundException ex, Model model) {
        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }

}
