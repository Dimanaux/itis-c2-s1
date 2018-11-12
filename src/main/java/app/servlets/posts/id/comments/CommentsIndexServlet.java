package main.java.app.servlets.posts.id.comments;

import main.java.app.db.models.Post;
import main.java.app.db.models.PostComment;
import main.java.app.db.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/posts/:id/comments"}, asyncSupported = true)
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
        comment.setAuthor(user);
        resp.setContentType("text/json");
        String json = getGson().toJson(comment);
        resp.getWriter().write(json);
        resp.getWriter().close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);
        int postId = getPostId(req.getRequestURI());
        Post post = getPostService().getPostById(postId);

        if (post == null || user == null) {
            resp.sendRedirect("/auth");
            return;
        }

        resp.setContentType("text/json");

        PostComment[] comments = getCommentService().getByPost(post).toArray(new PostComment[0]);

        String json = getGson().toJson(comments);
        resp.getWriter().write(json);
        resp.getWriter().close();
    }
}
