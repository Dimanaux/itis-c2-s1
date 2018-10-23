package app.servlets.auth;

import app.db.models.User;
import app.servlets.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        User user = getUserService().createUser(username, password, name);
        getUserService().authorize(req, user);
        resp.sendRedirect("/profile");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User currentUser = getUserService().getCurrentUser(req);
        if (currentUser != null) {
            resp.sendRedirect("/profile");
        } else {
            getHelper().render(
                    resp,
                    "Registration.ftl",
                    new HashMap<>()
            );
        }
    }
}
