package app.db.dao.postgres;

import app.db.models.Ingredient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientDao extends AbstractDao<Ingredient> implements app.db.dao.IngredientDao {
    IngredientDao(Connection connection) {
        super(connection);
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
    protected Ingredient instance(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(rs.getInt("id"));
            ingredient.setName(rs.getString("name"));
            return ingredient;
        } else {
            return null;
        }
    }
}
