package km.month9.myshop.domain.user;

import km.month9.myshop.domain.smartphone.SmartphoneService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserController {

    private final UserService service;
    private final SmartphoneService smartphoneService;

    @GetMapping("/index")
    public String root(Model model) {
        model.addAttribute("smartphones", smartphoneService.findAllProducts());
        return "index";
    }

    @GetMapping("/register")
    public String userRegisterPage(Model model) {
        if(!model.containsAttribute("form")) {
            model.addAttribute("form", new UserRegisterForm());
        }
        return "/register";
    }

    @RequestMapping("/register")
    public String register(@Valid UserRegisterForm form,
                            BindingResult validationResult,
                            RedirectAttributes attributes) {
        attributes.addFlashAttribute("form");

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }

        if (service.checkUser(form)) {
            attributes.addFlashAttribute("user", form);
            return "redirect:/register";
        }

        service.saveUser(form);

        return "redirect:/index";
    }
}
