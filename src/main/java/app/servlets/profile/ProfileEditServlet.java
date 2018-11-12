package main.java.app.servlets.profile;

import main.java.app.db.models.User;
import main.java.app.servlets.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "ProfileEditServlet", urlPatterns = {"/profile/edit"})
public class ProfileEditServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);

        if (user == null) {
            resp.sendRedirect("/auth");
            return;
        }

        getHelper().render(
                resp,
                "ProfileEdit.ftl",
                new HashMap<>() {{
                    put("user", user);
                }}
        );
    }
}
