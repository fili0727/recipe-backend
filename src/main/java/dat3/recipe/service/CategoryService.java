package dat3.recipe.service;

import dat3.recipe.dto.CategoryDTO;
import dat3.recipe.entity.Category;
import dat3.recipe.entity.Recipe;
import dat3.recipe.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> getAllCategories() {
        List<Category> categories =  categoryRepository.findAll();
        //Convert from list of Categories to DTO-type, list of Strings
        return categories.stream().map((c)->new String(c.getName())).toList();
    }

//    public Category addCategory(Category category) {
//        Category newCategory = new Category();
//        categoryRepository.save(newCategory);
//        return new Category(category.getName());
//    }
//        if (request.getId() != null) {
//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot provide the id for a new recipe");
//    }
//    Category category = categoryRepository.findByName(request.getCategory()).
//            orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Only existing categories are allowed"));
//    Recipe newRecipe = new Recipe();
//    updateRecipe(newRecipe, request, category);
//        recipeRepository.save(newRecipe);
//        return new RecipeDTO(newRecipe,false);
//



    public CategoryDTO addCategory(CategoryDTO request) {
        // Check if the request contains an ID, which it should not for a new category
        if (request.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot provide the id for a new category");
        }
        // Create a new Category entity and set its properties from the request DTO
        Category newCategory = new Category();
        newCategory.setName(request.getName());
        // Save the new Category entity to the repository
        Category savedCategory = categoryRepository.save(newCategory);
        // Create and return a DTO for the saved entity
        CategoryDTO response = new CategoryDTO();
        response.setName(savedCategory.getName());

        return response;
    }

}
