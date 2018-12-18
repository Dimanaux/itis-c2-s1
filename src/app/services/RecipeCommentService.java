package app.services;

import app.db.dao.RecipeCommentDao;
import app.db.models.Recipe;
import app.db.models.RecipeComment;
import app.db.models.User;

import java.util.List;

public class RecipeCommentService {
    private RecipeCommentDao recipeCommentDao;

    public RecipeCommentService() {
        recipeCommentDao = new app.db.dao.postgres.RecipeCommentDao();
    }

    public RecipeComment createComment(Recipe recipe, User user, String text) {
        RecipeComment comment = new RecipeComment();
        comment.setRecipe(recipe);
        comment.setRecipeId(recipe.getRecipeId());
        comment.setAuthor(user);
        comment.setAuthorId(user.getId());
        comment.setText(text);
        return recipeCommentDao.save(comment);
    }

    public List<RecipeComment> getCommentsByRecipeId(int recipeId) {
        return recipeCommentDao.getByRecipeId(recipeId);
    }
}
