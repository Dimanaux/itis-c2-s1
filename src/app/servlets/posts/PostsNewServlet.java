package app.servlets.posts;

import app.db.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "PostsNewServlet")
public class PostsNewServlet extends AbstractPostServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().authenticate(req);
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
