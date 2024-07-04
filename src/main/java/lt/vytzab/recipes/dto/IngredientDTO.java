package lt.vytzab.recipes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private int id;
    private String name;
    private double protein;
    private double carbs;
    private double fat;
    private int kcal;
    private String image;
}
