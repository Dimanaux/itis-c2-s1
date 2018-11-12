package main.java.app.servlets.profile;

import main.java.app.db.dao.postgres.RecipeDao;
import main.java.app.db.models.Recipe;
import main.java.app.db.models.User;
import main.java.app.servlets.AbstractServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "ProfileIndexServlet", urlPatterns = {"/profile"})
public class ProfileIndexServlet extends AbstractServlet {
    private RecipeDao recipeDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        recipeDao = new RecipeDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);

        String name = req.getParameter("name");
        getUserService().updateProfile(user, name);

        getUserService().authorize(req, user);
        resp.sendRedirect("/profile");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);

        if (user == null) {
            resp.sendRedirect("/auth");
            return;
        }

        List<Recipe> recipes = recipeDao.getByAuthor(user);

        getHelper().render(
                resp,
                "Profile.ftl",
                new HashMap<>() {{
                    put("user", user);
                    put("recipes", recipes);
                }}
        );
    }
}
