package app.db.dao.postgres;

import app.db.models.Dish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DishDao extends AbstractDao<Dish> implements app.db.dao.DishDao {
    DishDao() {
        super(ConnectionSingleton.getInstance());
    }

    @Override
    public Dish getByName(String name) {
        try {
            PreparedStatement statement = super.connection.prepareStatement(
                    "SELECT * FROM dish WHERE name = ? LIMIT 1"
            );
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            rs.next();
            return instance(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getTableName() {
        return "dish";
    }


    @Override
    public Dish instance(ResultSet rs) {
        Dish dish = new Dish();
        dish.setId(getInt(rs, "id"));
        dish.setName(getString(rs, "name"));
        dish.setDescription(getBlob(rs, "description"));
        dish.setVegan(getBoolean(rs, "is_vegan"));
        return dish;
    }
}
