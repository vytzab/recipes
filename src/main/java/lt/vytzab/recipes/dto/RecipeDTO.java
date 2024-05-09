package lt.vytzab.recipes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
        private int id;
        private String name;
        private String description;
        private List<IngredientDTOWithAmount> ingredients;
        private int preparationTime;
        private int cookingTime;
        private int totalTime;
        private String instructions;
        private int servings;
        private String difficultyLevel;
        private String image;
        private double totalProtein;
        private double totalCarbs;
        private double totalFat;
        private int totalKcal;
}
