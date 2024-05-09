package lt.vytzab.recipes.controller;


import lt.vytzab.recipes.dto.RecipeDTO;
import lt.vytzab.recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@CrossOrigin
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @PostMapping("/addRecipe")
    public ResponseEntity<RecipeDTO> addRecipe(@RequestBody RecipeDTO recipeDTO) {
        RecipeDTO savedRecipe = recipeService.addRecipe(recipeDTO);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }

    @GetMapping("/getAllRecipes")
    public ResponseEntity<List<RecipeDTO>> getAllRecipes(){
        List<RecipeDTO> allIngredients = recipeService.getAllRecipes();
        return new ResponseEntity<>(allIngredients, HttpStatus.OK);
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
}
