package app.db.dao.postgres;

import app.db.models.Post;
import app.db.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PostDao extends AbstractDao<Post> implements app.db.dao.PostDao {

    public PostDao() {
        super(ConnectionSingleton.getInstance());
    }

    @Override
    public List<Post> getByAuthor(User author) {
        LinkedList<Post> posts = new LinkedList<>();

        // TODO: 18/10/18 JOIN
        try {
            PreparedStatement statement = super.connection.prepareStatement(
                    "SELECT * FROM post WHERE author_id = ?"
            );
            statement.setInt(1, author.getId());

            ResultSet rs = statement.executeQuery();

            Post post = instance(rs);
            while (post != null) {
                posts.add(post);
                post = instance(rs);
                // TODO: 18/10/18 or JOIN?
                post.setAuthor(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return posts;

    }

    @Override
    public String getTableName() {
        return "post";
    }

    @Override
    protected Post instance(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Post post = new Post();
            post.setId(rs.getInt("id"));
            post.setAuthorId(rs.getInt("author_id"));
            post.setDate(rs.getDate("publish_date"));
            post.setText(rs.getBlob("text").toString());
            return post;
        } else {
            return null;
        }
    }
}
