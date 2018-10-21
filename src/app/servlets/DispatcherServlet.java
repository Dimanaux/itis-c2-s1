package app.servlets;

import app.util.Pair;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(name = "DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
    private List<Pair<Pattern, RequestDispatcher>> map;

    @Override
    public void init() {
        map = new LinkedList<>();

        addPattern("/posts/new", "/posts/new");
        addPattern("/posts/([1-9][0-9]*)/edit", "/posts/:id/edit");
        addPattern("/posts/([1-9][0-9]*)", "/posts/:id");
        addPattern("/posts", "/posts");

        addPattern("/posts/([1-9][0-9]*)/comments/new", "/posts/:id/comments/new");
        addPattern("/posts/([1-9][0-9]*)/comments/([1-9][0-9]*)/edit", "/posts/:id/comments/:id/edit");
        addPattern("/posts/([1-9][0-9]*)/comments/([1-9][0-9]*)", "/posts/:id/comments/:id");
        addPattern("/posts/([1-9][0-9]*)/comments", "/posts/:id/comments");


        addPattern("/recipes/new", "/recipes/new");
        addPattern("/recipes/([1-9][0-9]*)/edit", "/recipes/:id/edit");
        addPattern("/recipes/([1-9][0-9]*)", "/recipes/:id");
        addPattern("/recipes", "/recipes");

        addPattern("/recipes/([1-9][0-9]*)/comments/new", "/recipes/:id/comments/new");
        addPattern("/recipes/([1-9][0-9]*)/comments/([1-9][0-9]*)/edit", "/recipes/:id/comments/:id/edit");
        addPattern("/recipes/([1-9][0-9]*)/comments/([1-9][0-9]*)", "/recipes/:id/comments/:id");
        addPattern("/recipes/([1-9][0-9]*)/comments", "/recipes/:id/comments");

        // TODO: 18/10/19 add url patterns
        // TODO: 18/10/20 order patterns in the right way


    }

    private void addPattern(String regex, String dumbUrl) {
        map.add(new Pair<>(Pattern.compile(regex), getServletContext().getRequestDispatcher(dumbUrl)));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        for (var pair : map) {
            if (pair.getKey().matcher(uri).matches()) {
                pair.getValue().forward(req, resp);
                return;
            }
        }
        resp.sendError(404);
    }
}
