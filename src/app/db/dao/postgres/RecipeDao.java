package app.db.dao.postgres;

import app.db.models.Recipe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeDao extends AbstractDao<Recipe> implements app.db.dao.RecipeDao {
    RecipeDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return "recipe";
    }

    @Override
    protected Recipe instance(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Recipe recipe = new Recipe();
            recipe.setId(rs.getInt("id"));
            recipe.setAuthorId(rs.getInt("author_id"));
            recipe.setDishId(rs.getInt("dish_id"));
            recipe.setDate(rs.getDate("publish_date"));
            recipe.setText(rs.getBlob("text").toString());
            return recipe;
        } else {
            return null;
        }
    }
}
