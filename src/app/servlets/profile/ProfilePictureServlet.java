package app.servlets.profile;

import app.db.models.User;
import app.servlets.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "ProfilePictureServlet", urlPatterns = {"/profile/picture"})
public class ProfilePictureServlet extends AbstractServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = getUserService().getCurrentUser(req);
        if (user == null) {
            resp.sendError(403);
            return;
        }
        Part filePart = req.getPart("photo");
        getUserService().updateUserPicture(filePart.getInputStream(), user);
        resp.sendRedirect("/profile");
    }
}
