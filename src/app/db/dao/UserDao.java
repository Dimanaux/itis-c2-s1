package app.db.dao;

import app.db.models.User;


public interface UserDao extends Dao<User> {
    User getByUsername(String username);
}
