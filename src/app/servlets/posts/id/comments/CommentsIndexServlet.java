package app.servlets.posts.id.comments;

import app.db.models.Post;
import app.db.models.PostComment;
import app.db.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/posts/:id/comments"})
public class CommentsIndexServlet extends AbstractCommentsServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);
        int postId = getPostId(req.getRequestURI());
        Post post = getPostService().getPostById(postId);

        if (user == null || post == null) {
            resp.sendError(403);
            return;
        }
        String text = req.getParameter("text");

        PostComment comment = getCommentService().create(post, user, text);
        if (comment == null) {
            resp.sendError(500);
        } else {
            resp.sendRedirect("/posts/" + post.getId() + "/comments/" + comment.getId());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);

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
