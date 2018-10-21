package app.db.dao.postgres;

import app.db.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractDao<User> implements app.db.dao.UserDao {
    public UserDao() {
        super(ConnectionSingleton.getInstance());
    }

    @Override
    public User getByUsername(String username) {
        try {
            PreparedStatement statement = super.connection.prepareStatement(
                    "SELECT * FROM \"user\" WHERE username = ?"
            );
            statement.setString(1, username);
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
        return "\"user\"";
    }

    @Override
    public User instance(ResultSet rs) {
        User user = new User();
        user.setId(getInt(rs, "id"));
        user.setUsername(getString(rs, "username"));
        user.setPassword(getString(rs, "password"));
        user.setName(getString(rs, "name"));
        return user;
    }
}
