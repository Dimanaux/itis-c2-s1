package app.servlets.posts;

import app.services.PostService;
import app.servlets.AbstractServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public abstract class AbstractPostsServlet extends AbstractServlet {
    private PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = new PostService();
    }

    PostService getPostService() {
        return postService;
    }
}
