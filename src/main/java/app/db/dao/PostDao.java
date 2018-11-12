package main.java.app.db.dao;

import main.java.app.db.models.Post;
import main.java.app.db.models.User;

import java.util.List;

public interface PostDao extends Dao<Post> {
    List<Post> getByAuthor(User author);
    List<Post> getByAuthorId(int id);
}
