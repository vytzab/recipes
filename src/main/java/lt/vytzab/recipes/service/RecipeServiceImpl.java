package lt.vytzab.recipes.service;

import jakarta.ws.rs.NotFoundException;
import lt.vytzab.recipes.dto.IngredientDTO;
import lt.vytzab.recipes.dto.IngredientDTOWithAmount;
import lt.vytzab.recipes.dto.RecipeDTO;
import lt.vytzab.recipes.entity.Recipe;
import lt.vytzab.recipes.mapper.RecipeMapper;
import lt.vytzab.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService{
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public RecipeDTO addRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = RecipeMapper.INSTANCE.mapRecipeDTOToRecipe(recipeDTO);
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFat = 0;
        int totalKcal = 0;

        List<IngredientDTOWithAmount> ingredients = new ArrayList<>();
        for (IngredientDTOWithAmount ingredientWithAmount : recipeDTO.getIngredients()) {
            IngredientDTO ingredientDTO = restTemplate.getForObject("http://INGREDIENTS-SERVICE/ingredient/getIngredient/" + ingredientWithAmount.getIngredientId(), IngredientDTO.class);
            if (ingredientDTO != null) {
                totalProtein += ingredientDTO.getProtein() * ingredientWithAmount.getAmount()*0.01;
                totalCarbs += ingredientDTO.getCarbs() * ingredientWithAmount.getAmount()*0.01;
                totalFat += ingredientDTO.getFat() * ingredientWithAmount.getAmount()*0.01;
                totalKcal += (int) (ingredientDTO.getKcal() * ingredientWithAmount.getAmount()*0.01);
            }
        }
        recipe.setTotalProtein(totalProtein);
        recipe.setTotalCarbs(totalCarbs);
        recipe.setTotalFat(totalFat);
        recipe.setTotalKcal(totalKcal);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return RecipeMapper.INSTANCE.mapRecipeToRecipeDTO(savedRecipe);
    }

    @Override
    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(RecipeMapper.INSTANCE::mapRecipeToRecipeDTO).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<RecipeDTO> getRecipe(Integer id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        return optionalRecipe.map(recipe -> new ResponseEntity<>(RecipeMapper.INSTANCE.mapRecipeToRecipeDTO(recipe), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Override
    public RecipeDTO updateRecipe(Integer id, RecipeDTO recipeDTO) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isEmpty()) {
            throw new NotFoundException("Recipe not found with id: " + id);
        }
        Recipe recipe = recipeRepository.save(RecipeMapper.INSTANCE.mapRecipeDTOToRecipe(recipeDTO));
        return RecipeMapper.INSTANCE.mapRecipeToRecipeDTO(recipe);
    }

    @Override
    public ResponseEntity<RecipeDTO> deleteRecipe(Integer id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
