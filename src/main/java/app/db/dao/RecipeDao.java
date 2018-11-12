package main.java.app.db.dao;

import main.java.app.db.models.Recipe;
import main.java.app.db.models.User;

import java.util.List;

public interface RecipeDao extends Dao<Recipe> {
    List<Recipe> getByAuthor(User author);
    List<Recipe> getByAuthorId(int id);
    List<Recipe> getRecipesByIngredients(List<Integer> ids);
}
