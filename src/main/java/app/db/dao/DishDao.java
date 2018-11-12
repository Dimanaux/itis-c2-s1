package main.java.app.db.dao;

import main.java.app.db.models.Dish;

public interface DishDao extends Dao<Dish> {
    Dish getByName(String name);
}
