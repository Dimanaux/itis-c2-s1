package main.java.app.db.dao;

import main.java.app.db.models.User;

public interface UserDao extends Dao<User> {
    User getByUsername(String username);
    User getUserByToken(String token);
    boolean saveOrUpdateToken(String token, int userId);
}
