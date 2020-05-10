package km.month9.myshop.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserController {

    private final UserService service;

    @GetMapping("/register")
    public String userRegisterPage(Model model) {
        if (!model.containsAttribute("form")) {
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
        service.register(form);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/profile")
    public String pageCustomerProfile(Model model, Principal principal) {
        var user = service.getByEmail(principal.getName());
        model.addAttribute("dto", user);
        return "profile";
    }
}
