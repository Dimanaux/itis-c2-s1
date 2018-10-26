package app.services;

import app.db.dao.DishDao;
import app.db.models.Dish;

import java.util.List;

public class DishService {
    private DishDao dishDao;


    public DishService() {
        dishDao = new app.db.dao.postgres.DishDao();

    }

    public Dish getByName(String name) {
        return dishDao.getByName(name);
    }

    public Dish getById(int id) {
        return dishDao.getById(id);
    }

    public List<Dish> getAll() {
        return dishDao.getAll();
    }

    public Dish create(String name, Boolean isVegan, String description) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setVegan(isVegan);
        dish.setDescription(description);
        return dishDao.save(dish);
    }

}
