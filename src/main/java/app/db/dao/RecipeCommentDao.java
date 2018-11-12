package main.java.app.db.dao;

import main.java.app.db.models.Recipe;
import main.java.app.db.models.RecipeComment;

import java.util.List;

public interface RecipeCommentDao extends Dao<RecipeComment> {
    List<RecipeComment> getByRecipe(Recipe recipe);
    List<RecipeComment> getByRecipeId(int id);
}
