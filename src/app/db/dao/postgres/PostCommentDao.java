package app.db.dao.postgres;

import app.db.models.Post;
import app.db.models.PostComment;
import app.db.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PostCommentDao extends AbstractDao<PostComment> implements app.db.dao.PostCommentDao {
    private PostDao postDao;
    private UserDao userDao;
    PostCommentDao() {
        super(ConnectionSingleton.getInstance());
        this.postDao = new app.db.dao.postgres.PostDao();
        this.userDao = new app.db.dao.postgres.UserDao();
    }

    @Override
    public String getTableName() {
        return "post_comment";
    }

    @Override
    public PostComment instance(ResultSet rs) {
        PostComment postComment = new PostComment();
        postComment.setId(getInt(rs, "id"));
        postComment.setAuthorId(getInt(rs, "author_id"));
        postComment.setPostId(getInt(rs, "post_id"));
        postComment.setDate(getDate(rs, "publish_date"));
        postComment.setText(getBlob(rs, "text"));
        return postComment;
    }

    @Override
    public List<PostComment> getByPost(Post post) {
        List<PostComment> comments = new LinkedList<>();
        try {
            PreparedStatement statement = super.connection.prepareStatement(
                    "SELECT * FROM post_comment " +
                            "INNER JOIN \"user\" u on post_comment.author_id = u.id WHERE post_comment.id = ?"
            );
            statement.setInt(1, post.getId());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PostComment comment = instance(rs);
                User author = userDao.instance(rs);
                author.setId(comment.getAuthorId());

                comment.setPost(post);
                comment.setAuthor(author);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public List<PostComment> getByPostId(int id) {
        Post post = postDao.getById(id);
        return getByPost(post);
    }
}
