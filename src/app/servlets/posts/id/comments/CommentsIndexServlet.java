package app.servlets.posts.id.comments;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "CommentsIndexServlet")
public class CommentsIndexServlet extends AbstractCommentServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserService().authenticate(req);
        int postId = getPostId(req.getRequestURI());
        Post post = getPostService().getPostById(postId);

        if (user == null || post == null) {
            resp.sendError(403);
            return;
        }
        String text = req.getParameter("text");

        Comment comment = getCommentService().createComment(post, user, text);
        if (comment == null) {
            resp.sendError(500);
        } else {
            resp.sendRedirect("/posts/" + post.getId() + "/comments/" + comment.getId());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: 18/10/20 ?
        User user = getUserService().authenticate(req);

        if (user == null) {
            resp.sendRedirect("/auth");
            return;
        }

        int postId = getPostId(req.getRequestURI());
        Post post = getPostService().getPostById(postId);
        getHelper().render(
                resp,
                "PostsIdComments.ftl",
                new HashMap<>() {{
                    put("user", user);
                    put("post", post);
                }}
        );
    }
}
