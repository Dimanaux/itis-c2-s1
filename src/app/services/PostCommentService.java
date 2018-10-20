package app.services;

public interface PostCommentService {
    Comment createComment(Post post, User user, String text);
}
