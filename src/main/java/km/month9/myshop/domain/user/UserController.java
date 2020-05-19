package km.month9.myshop.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserController {

    private final UserService service;
    private final PasswordResetRepository passwordRR;

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

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "/forgot-password";
    }

    @PostMapping("/forgot-password")
    public String submitForgotPassword(@RequestParam("email") String email, RedirectAttributes attributes, Model model) {
        if (!service.checkUserByEmail(email)) {
            attributes.addFlashAttribute("error", "Entered email does not exist!");
            return "redirect:/forgot-password";
        }

        PasswordResetToken pT = PasswordResetToken.builder()
                .user(service.getUserByEmail(email))
                .token(UUID.randomUUID().toString()).build();
        passwordRR.deleteAll();
        passwordRR.save(pT);

        model.addAttribute("reset", pT);

        return "/reset-password";
    }

    @PostMapping("/reset-password")
    public String reset(@RequestParam Map<String, String> map, RedirectAttributes attributes, Model model) {
        if(!passwordRR.existsByToken(map.get("token"))) {
            attributes.addFlashAttribute("error", "Not valid token!");
            return "redirect:/forgot-password";
        }
        var pr = passwordRR.getByToken(map.get("token"));
        var u = pr.getUser();
        u.setPassword(new BCryptPasswordEncoder().encode(map.get("password")));
        service.saveUser(u);
        return "/login";
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
