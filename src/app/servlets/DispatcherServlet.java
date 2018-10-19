package app.servlets;

import app.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet(name = "DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
    private List<Pair<Pattern, HttpServlet>> map;

    @Override
    public void init() {
        map = new LinkedList<>();
        // TODO: 18/10/19 add url-patterns
    }

    private void addPattern(String regex, HttpServlet instance) {
        map.add(new Pair<>(Pattern.compile(regex), instance));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        for (var pair : map) {
            if (pair.getKey().matcher(uri).matches()) {
                pair.getValue().init();
                pair.getValue().service(req, resp);
                return;
            }
        }
        resp.sendError(404);
    }
}
