package lt.vytzab.recipes.repository;

import lt.vytzab.recipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByBreakfast(boolean isBreakfast);
    List<Recipe> findByLunch(boolean isLunch);
    List<Recipe> findByDinner(boolean isDinner);
}