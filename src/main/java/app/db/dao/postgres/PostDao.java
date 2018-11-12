package main.java.app.db.dao.postgres;

import main.java.app.db.models.Post;
import main.java.app.db.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PostDao extends AbstractDao<Post> implements main.java.app.db.dao.PostDao {
    private UserDao userDao;

    public PostDao() {
        super(ConnectionSingleton.getInstance());
        userDao = new UserDao();
    }

    @Override
    public Post getById(int id) {
        try {
            PreparedStatement statement = super.connection.prepareStatement(
                    "SELECT * FROM post p INNER JOIN \"user\" u on p.author_id = u.id WHERE p.id = ?"
            );
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();

            Post post = instance(rs);
            User author = userDao.instance(rs);
            author.setId(post.getAuthorId());
            return post;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Post> getByAuthor(User author) {
        List<Post> posts = new LinkedList<>();
        try {
            PreparedStatement statement = super.connection.prepareStatement(
                    "SELECT * FROM post WHERE author_id = ?"
            );
            statement.setInt(1, author.getId());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Post post = instance(rs);
                post.setAuthor(author);
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public List<Post> getByAuthorId(int id) {
        User author = userDao.getById(id);
        return getByAuthor(author);
    }

    @Override
    public List<Post> getAll() {
        try {
            List<Post> posts = new LinkedList<>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM post INNER JOIN \"user\" u on post.author_id = u.id"
            );
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Post post = instance(rs);
                User user = userDao.instance(rs);
                user.setId(post.getAuthorId());
                post.setAuthor(user);
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    @Override
    public String getTableName() {
        return "post";
    }

    @Override
    public Post instance(ResultSet rs) {
        Post post = new Post();
        post.setId(getInt(rs, "id"));
        post.setTitle(getString(rs, "title"));
        post.setAuthorId(getInt(rs, "author_id"));
        post.setDate(getDate(rs, "publish_date"));
        post.setText(getString(rs, "text"));
        return post;
    }
}
