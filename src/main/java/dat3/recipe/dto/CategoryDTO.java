package dat3.recipe.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.recipe.entity.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)


public class CategoryDTO {
    private Integer id;

    private String name;

    public CategoryDTO(Recipe r) {
        this.id = r.getCategory().getId();
        this.name = r.getName();
    }
}



