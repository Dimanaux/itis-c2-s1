package main.java.app.db.dao;

import main.java.app.db.models.Ingredient;
import main.java.app.db.models.Recipe;

import java.util.List;

public interface IngredientDao extends Dao<Ingredient> {
    Ingredient getByName(String name);
    List<Ingredient> getByRecipe(Recipe recipe);
    List<Ingredient> getByRecipeId(int recipeId);
    void bindToRecipe(Recipe recipe, int[] ids);
    List<Ingredient> getByNames(List<String> names);
}
