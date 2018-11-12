package main.java.app.services;

import main.java.app.db.dao.RecipeCommentDao;
import main.java.app.db.models.Recipe;
import main.java.app.db.models.RecipeComment;
import main.java.app.db.models.User;

import java.util.List;

public class RecipeCommentService {
    private RecipeCommentDao recipeCommentDao;

    public RecipeCommentService() {
        recipeCommentDao = new main.java.app.db.dao.postgres.RecipeCommentDao();
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
