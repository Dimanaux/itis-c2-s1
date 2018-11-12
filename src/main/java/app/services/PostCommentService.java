package main.java.app.services;

import main.java.app.db.dao.postgres.PostCommentDao;
import main.java.app.db.models.Post;
import main.java.app.db.models.PostComment;
import main.java.app.db.models.User;

import java.util.List;

public class PostCommentService {

    private PostCommentDao postCommentDao;

    public PostCommentService() {
        postCommentDao = new PostCommentDao();
    }

    public PostComment create(Post post, User user, String text) {
        PostComment comment = new PostComment();
        comment.setPost(post);
        comment.setAuthor(user);
        comment.setText(text);
        return postCommentDao.save(comment);
    }

    public List<PostComment> getByPost(Post post) {
        return postCommentDao.getByPost(post);
    }
}
