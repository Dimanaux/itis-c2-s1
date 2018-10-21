package app.servlets.posts;

import app.services.PostService;
import app.services.UserService;
import app.servlets.AbstractServlet;
import app.util.Helper;

public abstract class AbstractPostsServlet extends AbstractServlet {
    private Helper helper;
    private UserService userService;
    private PostService postService;

    @Override
    public void init() {
        helper = new Helper(getServletContext());
        userService = new UserService();
        postService = new PostService();
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
}
