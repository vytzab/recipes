package lt.vytzab.recipes.repository;

import lt.vytzab.recipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByBreakfastOrLunchOrDinner(boolean isBreakfast, boolean isLunch, boolean isDinner);
}