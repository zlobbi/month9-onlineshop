package km.month9.myshop.domain.smartphone;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SearchForm {
    @NotBlank
    private String text = "";
    @NotBlank
    private String param = "";
}
