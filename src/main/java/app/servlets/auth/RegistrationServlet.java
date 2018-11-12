package main.java.app.servlets.auth;

import main.java.app.db.models.User;
import main.java.app.servlets.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        Matcher usernameMatcher = Pattern.compile("[a-z][a-z0-9_]{4,15}", Pattern.CASE_INSENSITIVE).matcher(username);
        if (!usernameMatcher.matches()) {
            resp.sendError(400, "username must contain only letters, digits and _. username can start with letter only.");
            return;
        }

        Matcher passwordMatcher = Pattern.compile("[a-z0-9!@#$%^&*-=_+?.,]{6,24}", Pattern.CASE_INSENSITIVE).matcher(username);
        if (!passwordMatcher.matches()) {
            resp.sendError(400, "[a-z0-9!@#$%^&*-=_+?.,]{6,24} plz");
            return;
        }


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
