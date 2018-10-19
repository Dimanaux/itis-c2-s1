package app.db.dao.postgres;

import app.db.models.Dish;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DishDao extends AbstractDao<Dish> implements app.db.dao.DishDao {
    DishDao(Connection connection) {
        super(connection);
    }

    @Override
    public Dish getByName(String name) {
        return null;
    }

    @Override
    public String getTableName() {
        return "dish";
    }

    @Override
    protected Dish instance(ResultSet rs) throws SQLException {
        Dish dish = null;
        if (rs.next()) {
            dish = new Dish();
            dish.setId(rs.getInt("id"));
            dish.setName(rs.getString("name"));
            dish.setDescription(rs.getBlob("description").toString());
            dish.setVegan(rs.getBoolean("is_vegan"));
        }
        return dish;
    }
}
