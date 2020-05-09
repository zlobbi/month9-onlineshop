package km.month9.myshop.frontend;

import km.month9.myshop.domain.exception.ResourceNotFoundException;
import km.month9.myshop.domain.smartphone.SearchForm;
import km.month9.myshop.domain.smartphone.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class FrontendController {

    private String text = "";
    private String param = "";

    @Autowired
    private SmartphoneService service;
    @Autowired
    private PropertiesService propertiesService;

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
    public String index(Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        var smartphones = service.getSmartphones(pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(smartphones, propertiesService.getDefaultPageSize(), model, uri);
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model, Pageable pageable, HttpServletRequest uriBuilder, SearchForm form) {
        if(!model.containsAttribute("form")) {
            model.addAttribute("from", new SearchForm());
        }
        if(!form.getText().equals("")) {
            text = form.getText();
            param = uriBuilder.getParameter("param");
        }
        var uri = uriBuilder.getRequestURI();
        var result = service.searchSmartphones(pageable, param, text);
        constructPageable(result, propertiesService.getDefaultPageSize(), model, uri);
        return "search";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(ResourceNotFoundException ex, Model model) {
        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }

}
