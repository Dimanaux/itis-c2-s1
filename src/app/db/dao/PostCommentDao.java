package app.db.dao;

import app.db.models.Post;
import app.db.models.PostComment;

import java.util.List;

public interface PostCommentDao extends Dao<PostComment> {
    List<PostComment> getByPost(Post post);
    List<PostComment> getByPostId(int id);
}
