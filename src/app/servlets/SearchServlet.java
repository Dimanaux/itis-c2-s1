package app.servlets;

import app.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserService().getCurrentUser(req);

        String query = req.getParameter("query");

        getHelper().render(
                resp,
                "Search.ftl",
                new HashMap<>() {{
                    put("user", user);
                    put("query", query);
                }}
        );
    }
}
