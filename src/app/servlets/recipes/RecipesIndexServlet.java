package app.servlets.recipes;

import app.db.models.Recipe;
import app.db.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "RecipesIndexServlet", urlPatterns = {"/recipes"})
public class RecipesIndexServlet extends AbstractRecipesServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);
        if (user == null) {
            resp.sendError(403);
            return;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Recipe> recipes = getRecipeService().getAllRecipes();
        User user = getUserService().getCurrentUser(req);
        getHelper().render(
                resp,
                "RecipesIndex.ftl",
                new HashMap<>() {{
                    put("user", user);
                    put("recipes", recipes);
                }}
        );
    }
}
