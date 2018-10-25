package app.db.dao;

import app.db.models.Ingredient;
import app.db.models.Recipe;

import java.util.List;

public interface IngredientDao extends Dao<Ingredient> {
    Ingredient getByName(String name);
    List<Ingredient> getByRecipe(Recipe recipe);
    List<Ingredient> getByRecipeId(int recipeId);
    void bindToRecipe(Recipe recipe, int[] ids);
}
