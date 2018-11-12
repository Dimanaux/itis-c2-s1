package main.java.app.services;

import main.java.app.db.dao.IngredientDao;
import main.java.app.db.models.Ingredient;
import main.java.app.db.models.Recipe;

import java.util.List;

public class IngredientService {

    private IngredientDao ingredientDao;

    public IngredientService() {
        this.ingredientDao = new main.java.app.db.dao.postgres.IngredientDao();
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientDao.getAll();
    }

    public Ingredient create(String name) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        return ingredientDao.save(ingredient);
    }

    public List<Ingredient> getIngredientsByRecipe(Recipe recipe) {
        return ingredientDao.getByRecipe(recipe);
    }
}
