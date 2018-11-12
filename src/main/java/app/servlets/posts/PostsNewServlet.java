package main.java.app.servlets.posts;

import main.java.app.db.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "PostsNewServlet", urlPatterns = {"/posts/new"})
public class PostsNewServlet extends AbstractPostsServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);
        if (user == null) {
            resp.sendRedirect("/auth");
            return;
        }
        getHelper().render(
                resp,
                "PostsNew.ftl",
                new HashMap<>() {{
                    put("user", user);
                }}
        );
    }
}
