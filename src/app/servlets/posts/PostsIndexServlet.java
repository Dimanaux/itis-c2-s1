package app.servlets.posts;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "PostsIndexServlet")
public class PostsIndexServlet extends AbstractPostServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().authenticate(req);
        if (user == null) {
            resp.sendError(403);
            return;
        }
        String title = req.getParameter("title");
        String text = req.getParameter("text");

        Post post = getPostService().createPost(title, user, text);
        if (post == null) {
            resp.sendError(500);
        } else {
            resp.sendRedirect("/posts/" + post.getId());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Post> posts = getPostService().getAllPosts();
        User user = getUserService().authenticate(req);
        getHelper().render(
                resp,
                "PostsIndex.ftl",
                new HashMap<>() {{
                    put("user", user);
                    put("posts", posts);
                }}
        );
    }
}
