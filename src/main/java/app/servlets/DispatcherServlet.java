package main.java.app.servlets;

import main.java.app.util.Pair;

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
    private List<Pair<Pattern, HttpServlet>> map;

    @Override
    public void init() {
        map = new LinkedList<>();

        add("/auth", new main.java.app.servlets.auth.AuthServlet());
        add("/registration", new main.java.app.servlets.auth.RegistrationServlet());

        add("/posts/new",                   new main.java.app.servlets.posts.PostsNewServlet());
        add("/posts/([1-9][0-9]*)/edit",    new main.java.app.servlets.posts.PostsIdEditServlets());
        add("/posts/([1-9][0-9]*)",         new main.java.app.servlets.posts.PostsIdServlet());
        add("/posts",                       new main.java.app.servlets.posts.PostsIndexServlet());

        add("/posts/([1-9][0-9]*)/comments/new",                new main.java.app.servlets.posts.id.comments.CommentsNewServlet());
        add("/posts/([1-9][0-9]*)/comments/([1-9][0-9]*)/edit", new main.java.app.servlets.posts.id.comments.CommentsIdEditServlet());
        add("/posts/([1-9][0-9]*)/comments/([1-9][0-9]*)",      new main.java.app.servlets.posts.id.comments.CommentsIdServlet());
        add("/posts/([1-9][0-9]*)/comments",                    new main.java.app.servlets.posts.id.comments.CommentsIndexServlet());

        add("/recipes/new",                 new main.java.app.servlets.recipes.RecipesNewServlet());
        add("/recipes/([1-9][0-9]*)/edit",  new main.java.app.servlets.recipes.RecipesIdEditServlets());
        add("/recipes/([1-9][0-9]*)",       new main.java.app.servlets.recipes.RecipesIdServlet());
        add("/recipes",                     new main.java.app.servlets.recipes.RecipesIndexServlet());

        add("/recipes/([1-9][0-9]*)/comments/new",                  new main.java.app.servlets.recipes.id.comments.CommentsNewServlet());
        add("/recipes/([1-9][0-9]*)/comments/([1-9][0-9]*)/edit",   new main.java.app.servlets.recipes.id.comments.CommentsIdEditServlet());
        add("/recipes/([1-9][0-9]*)/comments/([1-9][0-9]*)",        new main.java.app.servlets.recipes.id.comments.CommentsIdServlet());
        add("/recipes/([1-9][0-9]*)/comments",                      new main.java.app.servlets.recipes.id.comments.CommentsIndexServlet());

        add("/", new main.java.app.servlets.recipes.RecipesIndexServlet());
    }

    private void add(String regex, HttpServlet servlet) {
        map.add(new Pair<>(Pattern.compile(regex), servlet));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        for (var pair : map) {
            if (pair.getKey().matcher(uri).matches()) {
                pair.getValue().init(getServletConfig());
                pair.getValue().service(req, resp);
                return;
            }
        }
        resp.sendError(404);
    }
}
