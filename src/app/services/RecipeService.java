package app.services;

import app.db.dao.IngredientDao;
import app.db.dao.RecipeDao;
import app.db.models.Dish;
import app.db.models.Ingredient;
import app.db.models.Recipe;
import app.db.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeService {
    private RecipeDao recipeDao;
    private IngredientDao ingredientDao;

    public RecipeService() {
        recipeDao = new app.db.dao.postgres.RecipeDao();
        ingredientDao = new app.db.dao.postgres.IngredientDao();
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

    public Recipe create(Dish dish, User author, String title, String text, int[] ids) {
        Recipe recipe = new Recipe();
        recipe.setDish(dish);
        recipe.setTitle(title);
        recipe.setAuthor(author);
        recipe.setText(text);
        Recipe saved = recipeDao.save(recipe);
        ingredientDao.bindToRecipe(saved, ids);
        return saved;
    }

    public List<Recipe> getRecipesByIngredients(List<String> ingredientsNames) {
        List<Ingredient> ingredients = ingredientDao.getByNames(ingredientsNames);
        List<Integer> ids = ingredients.stream().map(Ingredient::getId).collect(Collectors.toList());
        return recipeDao.getRecipesByIngredients(ids);
    }
}
