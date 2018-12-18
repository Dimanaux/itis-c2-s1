package app.servlets.posts;

import app.db.models.Post;
import app.db.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "PostsIdEditServlet", urlPatterns = {"/posts/:id/edit"})
public class PostsIdEditServlets extends AbstractPostsServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);
        int id = getId(req.getRequestURI());
        Post post = getPostService().getPostById(id);

        if (!post.getAuthor().equals(user)) {
            resp.sendError(403);
            return;
        }

        getHelper().render(
                resp,
                "PostsIdEdit.ftl",
                new HashMap<>() {{
                    put("user", user);
                    put("post", post);
                }}
        );
    }

    private int getId(String uri) {
        Matcher matcher = Pattern.compile("/posts/([1-9][0-9]*)/edit")
                .matcher(uri);
        matcher.find();
        String id = matcher.group(1);
        return Integer.parseInt(id);
    }
}
