package lt.vytzab.recipes.service;

import lt.vytzab.recipes.dto.RecipeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RecipeService {
    RecipeDTO addRecipe(RecipeDTO recipeDTO);
    List<RecipeDTO> getAllRecipes();
    ResponseEntity<RecipeDTO> getRecipe(Integer id);
    RecipeDTO updateRecipe(Integer id, RecipeDTO recipeDTO);
    ResponseEntity<RecipeDTO> deleteRecipe(Integer id);
}