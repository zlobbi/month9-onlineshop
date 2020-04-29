package km.month9.myshop.controller;

import km.month9.myshop.repository.ProductRepository;
import km.month9.myshop.repository.ProductTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private final ProductRepository repo;
    private final ProductTypeRepository ptRepo;

    public MainController(ProductRepository repo, ProductTypeRepository ptRepo) {
        this.repo = repo;
        this.ptRepo = ptRepo;
    }

    @RequestMapping("/")
    public String roo(Model model) {
        model.addAttribute("products", repo.findAll());
        repo.findAll().forEach(product -> System.out.println(product));
        return "index";
    }

    @RequestMapping("/types")
    public String getTypes(Model model) {
        model.addAttribute("types", ptRepo.findAll());
        return "types";
    }
}
