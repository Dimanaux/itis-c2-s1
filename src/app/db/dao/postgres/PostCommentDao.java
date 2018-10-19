package app.db.dao.postgres;

import app.db.models.PostComment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostCommentDao extends AbstractDao<PostComment> implements app.db.dao.PostCommentDao {
    PostCommentDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return "post_comment";
    }

    @Override
    protected PostComment instance(ResultSet rs) throws SQLException {
        if (rs.next()) {
            PostComment postComment = new PostComment();
            postComment.setId(rs.getInt("id"));
            postComment.setAuthorId(rs.getInt("author_id"));
            postComment.setPostId(rs.getInt("post_id"));
            postComment.setDate(rs.getDate("publish_date"));
            postComment.setText(rs.getBlob("text").toString());
            return postComment;
        } else {
            return null;
        }
    }
}
