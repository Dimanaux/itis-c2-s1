package app.servlets.posts;

import app.services.PostService;
import app.services.UserService;
import app.servlets.AbstractServlet;
import app.util.Helper;

public abstract class AbstractPostServlet extends AbstractServlet {
    private Helper helper;
    private UserService userService;
    private PostService postService;

    @Override
    public void init() {
        helper = new Helper(getServletContext());
        // TODO: 18/10/20 initialize services
        userService = null;
        postService = null;
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
