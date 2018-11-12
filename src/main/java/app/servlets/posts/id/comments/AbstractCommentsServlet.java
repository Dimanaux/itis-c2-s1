package main.java.app.servlets.posts.id.comments;

import main.java.app.services.PostCommentService;
import main.java.app.servlets.posts.AbstractPostsServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractCommentsServlet extends AbstractPostsServlet {
    private PostCommentService commentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        commentService = new PostCommentService();
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
