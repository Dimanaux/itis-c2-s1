package app.servlets.posts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "PostsIdEditServlets")
public class PostsIdEditServlets extends AbstractPostServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserService().authenticate(req);
        int id = getId(req.getRequestURI());
        Post post = getPostService().getPostById(id);

        if (!post.getAuthor().equals(user)) {
            resp.sendRedirect("/posts");
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
