package app.servlets.other;

import app.db.models.User;
import app.servlets.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "AboutServlet", urlPatterns = {"/about"})
public class AboutServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);
        getHelper().render(
                resp,
                "About.ftl",
                new HashMap<>() {{
                    put("user", user);
                }}
        );
    }
}
