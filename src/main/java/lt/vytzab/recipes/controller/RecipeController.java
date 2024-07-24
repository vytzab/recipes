package lt.vytzab.recipes.controller;


import lt.vytzab.recipes.dto.RecipeDTO;
import lt.vytzab.recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/recipe")
@CrossOrigin
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @PostMapping("/addRecipe")
    public ResponseEntity<RecipeDTO> addRecipe(@RequestBody RecipeDTO recipeDTO) {
        recipeDTO.setImage(recipeDTO.getName().replaceAll("\\s+","").toLowerCase());
        RecipeDTO savedRecipe = recipeService.addRecipe(recipeDTO);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }

    @GetMapping("/getAllRecipes")
    public ResponseEntity<List<RecipeDTO>> getAllRecipes(){
        List<RecipeDTO> allRecipes = recipeService.getAllRecipes();
        return new ResponseEntity<>(allRecipes, HttpStatus.OK);
    }

    @GetMapping("/getRecipe/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable("id") Integer id) {
        return recipeService.getRecipe(id);
    }

    @PutMapping("/updateRecipe")
    public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody RecipeDTO recipeDTO) {
        RecipeDTO updatedRecipeDTO = recipeService.updateRecipe(recipeDTO.getId(), recipeDTO);
        return new ResponseEntity<>(updatedRecipeDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteRecipe/{id}")
    public ResponseEntity<RecipeDTO> deleteRecipe(@PathVariable("id") Integer id) {
        return recipeService.deleteRecipe(id);
    }

    @GetMapping("/getRecipesByMealType")
    public ResponseEntity<List<RecipeDTO>> getRecipesByMealType(
            @RequestParam(required = false) boolean isBreakfast,
            @RequestParam(required = false) boolean isLunch,
            @RequestParam(required = false) boolean isDinner) {

        // If none of the parameters are provided, return an empty list
        if (!isBreakfast && !isLunch && !isDinner) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<RecipeDTO> recipes = recipeService.getRecipesByMealType(isBreakfast, isLunch, isDinner);
        return ResponseEntity.ok(recipes);
    }
}
