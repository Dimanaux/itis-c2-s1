package main.java.app.servlets.posts;

import main.java.app.services.PostService;
import main.java.app.servlets.AbstractServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public abstract class AbstractPostsServlet extends AbstractServlet {
    private PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = new PostService();
    }

    protected PostService getPostService() {
        return postService;
    }
}
