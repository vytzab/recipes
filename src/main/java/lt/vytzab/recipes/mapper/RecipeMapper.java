package lt.vytzab.recipes.mapper;

import lt.vytzab.recipes.dto.RecipeDTO;
import lt.vytzab.recipes.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);
    Recipe mapRecipeDTOToRecipe(RecipeDTO recipeDTO);
    RecipeDTO mapRecipeToRecipeDTO(Recipe recipe);
}
