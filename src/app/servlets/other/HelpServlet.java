package app.servlets.other;

import app.db.models.User;
import app.servlets.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "HelpServlet", urlPatterns = {"/help"})
public class HelpServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);
        getHelper().render(
                resp,
                "Help.ftl",
                new HashMap<>() {{
                    put("user", user);
                }}
        );
    }
}
