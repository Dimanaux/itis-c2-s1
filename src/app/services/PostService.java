package app.services;

import app.db.dao.PostDao;
import app.db.dao.UserDao;
import app.db.models.Post;
import app.db.models.User;

import java.util.List;

public class PostService {
    private UserDao userDao;
    private PostDao postDao;

    public PostService() {
        userDao = new app.db.dao.postgres.UserDao();
        postDao = new app.db.dao.postgres.PostDao();
    }

    public List<Post> getAllPosts() {
        return postDao.getAll();
    }

    public Post getPostById(int id) {
        return postDao.getById(id);
    }

    public Post create(String title, User user, String text) {
        Post post = new Post();
        post.setTitle(title);
        post.setAuthor(user);
        post.setText(text);
        return postDao.save(post);
    }

    public boolean delete(Post post) {
        // TODO: 18/10/20 implement
        return false;
    }
}
