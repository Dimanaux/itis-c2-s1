package app.db.dao;

import app.db.models.Recipe;
import app.db.models.RecipeComment;

import java.util.List;

public interface RecipeCommentDao extends Dao<RecipeComment> {
    List<RecipeComment> getByRecipe(Recipe recipe);
    List<RecipeComment> getByRecipeId(int id);
}
