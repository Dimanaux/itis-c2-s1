package app.db.dao;

import app.db.models.Ingredient;

public interface IngredientDao extends Dao<Ingredient> {
    Ingredient getByName(String name);
}
