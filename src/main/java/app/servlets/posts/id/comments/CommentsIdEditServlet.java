package main.java.app.servlets.posts.id.comments;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/posts/:id/comments/:id/edit"})
public class CommentsIdEditServlet extends AbstractCommentsServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: 18/10/20 implement
    }
}
