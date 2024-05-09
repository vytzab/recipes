package lt.vytzab.recipes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.vytzab.recipes.dto.IngredientDTOWithAmount;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @ElementCollection
    @CollectionTable(name = "recipe_ingredient_mapping", joinColumns = @JoinColumn(name = "recipe_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "ingredientId", column = @Column(name = "ingredient_id")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount"))
    })
    private List<IngredientDTOWithAmount> ingredients;
    private int preparationTime;
    private int cookingTime;
    private int totalTime;
    @Lob
    private String instructions;
    private int servings;
    private String difficultyLevel;
    private String image;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;
    private int totalKcal;
}