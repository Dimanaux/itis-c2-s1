package app.services;

import app.db.dao.IngredientDao;
import app.db.models.Ingredient;

import java.util.List;

public class IngredientService {

    private IngredientDao ingredientDao;

    public IngredientService() {
        this.ingredientDao = new app.db.dao.postgres.IngredientDao();
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientDao.getAll();
    }

    public Ingredient create(String name) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        return ingredientDao.save(ingredient);
    }
}
