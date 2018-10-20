package app.servlets.posts.id.comments;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "CommentsNewServlet")
public class CommentsNewServlet extends AbstractCommentServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().authenticate(req);
        int postId = getPostId(req.getRequestURI());
        Post post = getPostService().getPostById(postId);
        if (user == null) {
            resp.sendRedirect("/auth");
        } else if (post == null) {
            resp.sendRedirect("/posts");
        } else {
            getHelper().render(
                    resp,
                    "PostsIdCommentsNew.ftl",
                    new HashMap<>() {{
                        put("user", user);
                        put("post", post);
                    }}
            );
        }
    }
}
