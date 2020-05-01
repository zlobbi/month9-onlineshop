package km.month9.myshop.domain.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SmartphoneController {

    private final SmartphoneService service;

    @RequestMapping("/")
    public String root(Model model) {
        model.addAttribute("smartphones", service.findAllProducts());
        return "index";
    }

    @RequestMapping("/brands")
    public String getTypes(Model model) {
        model.addAttribute("brands", service.findAllProductTypes());
        return "brands";
    }
}
