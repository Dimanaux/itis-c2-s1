package app.servlets.auth;

import app.db.models.User;
import app.servlets.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "AuthServlet", urlPatterns = {"/auth"})
public class AuthServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().authenticate(req);
        if (user == null) {
            resp.sendError(400, "Wrong username or password.");
        } else {
            getUserService().authorize(req, user);
            if (req.getParameter("remember_me") != null) {
                getUserService().remember(resp, user);
            }
            resp.sendRedirect("/profile");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User currentUser = getUserService().getCurrentUser(req);
        if (currentUser != null) {
            resp.sendRedirect("/profile");
        } else {
            getHelper().render(
                    resp,
                    "Auth.ftl",
                    new HashMap<>()
            );
        }
    }
}
