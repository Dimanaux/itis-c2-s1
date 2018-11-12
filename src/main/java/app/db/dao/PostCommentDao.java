package main.java.app.db.dao;

import main.java.app.db.models.Post;
import main.java.app.db.models.PostComment;

import java.util.List;

public interface PostCommentDao extends Dao<PostComment> {
    List<PostComment> getByPost(Post post);
    List<PostComment> getByPostId(int id);
}
