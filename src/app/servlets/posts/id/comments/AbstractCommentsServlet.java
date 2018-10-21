package app.servlets.posts.id.comments;

import app.services.PostCommentService;
import app.services.PostService;
import app.services.UserService;
import app.servlets.AbstractServlet;
import app.util.Helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractCommentsServlet extends AbstractServlet {
    private Helper helper;
    private UserService userService;
    private PostService postService;
    private PostCommentService commentService;

    @Override
    public void init() {
        helper = new Helper(getServletContext());
        userService = new UserService();
        postService = new PostService();
        commentService = new PostCommentService();
    }

    Helper getHelper() {
        return helper;
    }

    UserService getUserService() {
        return userService;
    }

    PostService getPostService() {
        return postService;
    }

    PostCommentService getCommentService() {
        return commentService;
    }

    int getPostId(String uri) {
        Matcher matcher = Pattern.compile("/posts/([1-9][0-9]*)/.*")
                .matcher(uri);
        matcher.find();
        String id = matcher.group(1);
        return Integer.parseInt(id);
    }
}
