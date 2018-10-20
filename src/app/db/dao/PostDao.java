package app.db.dao;

import app.db.models.Post;
import app.db.models.User;

import java.util.List;

public interface PostDao extends Dao<Post> {
    List<Post> getByAuthor(User author);
}
