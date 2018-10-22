package app.servlets.profile;

import app.db.models.User;
import app.servlets.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "ProfileIndexServlet", urlPatterns = {"/profile"})
public class ProfileIndexServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().authenticate(req);

        getHelper().render(
                resp,
                "Profile.ftl",
                new HashMap<>() {{
                    put("user", user);
                }}
        );
    }
}
