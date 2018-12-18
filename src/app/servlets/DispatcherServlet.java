package app.servlets;

import app.servlets.auth.AuthServlet;
import app.servlets.auth.RegistrationServlet;
import app.servlets.posts.PostsIdEditServlets;
import app.servlets.posts.PostsIdServlet;
import app.servlets.posts.PostsIndexServlet;
import app.servlets.posts.PostsNewServlet;
import app.servlets.posts.id.comments.CommentsIdEditServlet;
import app.servlets.posts.id.comments.CommentsIdServlet;
import app.servlets.posts.id.comments.CommentsIndexServlet;
import app.servlets.posts.id.comments.CommentsNewServlet;
import app.servlets.recipes.RecipesIdEditServlets;
import app.servlets.recipes.RecipesIdServlet;
import app.servlets.recipes.RecipesIndexServlet;
import app.servlets.recipes.RecipesNewServlet;
import app.util.Pair;

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

        add("/auth", new AuthServlet());
        add("/registration", new RegistrationServlet());

        add("/posts/new",                   new PostsNewServlet());
        add("/posts/([1-9][0-9]*)/edit",    new PostsIdEditServlets());
        add("/posts/([1-9][0-9]*)",         new PostsIdServlet());
        add("/posts",                       new PostsIndexServlet());

        add("/posts/([1-9][0-9]*)/comments/new",                new CommentsNewServlet());
        add("/posts/([1-9][0-9]*)/comments/([1-9][0-9]*)/edit", new CommentsIdEditServlet());
        add("/posts/([1-9][0-9]*)/comments/([1-9][0-9]*)",      new CommentsIdServlet());
        add("/posts/([1-9][0-9]*)/comments",                    new CommentsIndexServlet());

        add("/recipes/new",                 new RecipesNewServlet());
        add("/recipes/([1-9][0-9]*)/edit",  new RecipesIdEditServlets());
        add("/recipes/([1-9][0-9]*)",       new RecipesIdServlet());
        add("/recipes",                     new RecipesIndexServlet());

        add("/recipes/([1-9][0-9]*)/comments/new",                  new app.servlets.recipes.id.comments.CommentsNewServlet());
        add("/recipes/([1-9][0-9]*)/comments/([1-9][0-9]*)/edit",   new app.servlets.recipes.id.comments.CommentsIdEditServlet());
        add("/recipes/([1-9][0-9]*)/comments/([1-9][0-9]*)",        new app.servlets.recipes.id.comments.CommentsIdServlet());
        add("/recipes/([1-9][0-9]*)/comments",                      new app.servlets.recipes.id.comments.CommentsIndexServlet());

        add("/", new RecipesIndexServlet());
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
