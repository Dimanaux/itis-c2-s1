package app.db.dao;

import app.db.models.Dish;

public interface DishDao extends Dao<Dish> {
    Dish getByName(String name);
}
