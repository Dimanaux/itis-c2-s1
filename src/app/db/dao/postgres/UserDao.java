package app.db.dao.postgres;

import app.db.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractDao<User> implements app.db.dao.UserDao {
    UserDao() {
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
    protected User instance(ResultSet rs) throws SQLException {
        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            return user;
        } else {
            return null;
        }
    }
}
