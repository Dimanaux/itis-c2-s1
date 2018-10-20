package app.servlets.posts;

import app.db.models.Post;
import app.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "PostsIdServlet")
public class PostsIdServlet extends AbstractPostServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO: 18/10/20 Maybe POST? or PUT/PATCH to edit post
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = getId(req.getRequestURI());
        Post post = getPostService().getPostById(id);
        User user = getUserService().authenticate(req);

        if (post.getAuthor().equals(user)) {
            getPostService().delete(post);
        } else {
            resp.sendError(403);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().authenticate(req);
        int id = getId(req.getRequestURI());
        Post post = getPostService().getPostById(id);
        boolean canEdit = post.getAuthor().equals(user);

        getHelper().render(
                resp,
                "PostsId.ftl",
                new HashMap<>() {{
                    put("user", user);
                    put("post", post);
                    put("can_edit", canEdit);
                }}
        );
    }

    private int getId(String uri) {
        Matcher matcher = Pattern.compile("/posts/([1-9][0-9]*)")
                .matcher(uri);
        matcher.find();
        String id = matcher.group(1);
        return Integer.parseInt(id);
    }
}
