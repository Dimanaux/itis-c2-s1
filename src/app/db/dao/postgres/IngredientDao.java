package app.db.dao.postgres;

import app.db.models.Ingredient;

import java.sql.ResultSet;

public class IngredientDao extends AbstractDao<Ingredient> implements app.db.dao.IngredientDao {
    public IngredientDao() {
        super(ConnectionSingleton.getInstance());
    }

    @Override
    public Ingredient getByName(String name) {
        return null;
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public Ingredient instance(ResultSet rs) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(getInt(rs, "id"));
        ingredient.setName(getString(rs, "name"));
        return ingredient;
    }
}
