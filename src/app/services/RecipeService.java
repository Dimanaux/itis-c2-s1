package app.services;

import app.db.dao.RecipeDao;
import app.db.models.Dish;
import app.db.models.Recipe;
import app.db.models.User;

import java.util.List;

public class RecipeService {
    private RecipeDao recipeDao;

    public RecipeService() {
        recipeDao = new app.db.dao.postgres.RecipeDao();
    }

    public Recipe getRecipeById(int recipeId) {
        return recipeDao.getById(recipeId);
    }

    public boolean delete(Recipe recipe) {
        return recipeDao.delete(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeDao.getAll();
    }

    public Recipe create(Dish dish, User author, String text) {
        Recipe recipe = new Recipe();
        recipe.setDish(dish);
        recipe.setAuthor(author);
        recipe.setText(text);
        return recipeDao.save(recipe);
    }
}
