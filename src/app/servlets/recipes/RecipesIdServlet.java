package app.servlets.recipes;

import app.db.models.Recipe;
import app.db.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "RecipesIdServlet", urlPatterns = {"/recipes/:id"})
public class RecipesIdServlet extends AbstractRecipesServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO: 18/10/20 Maybe POST? or PUT/PATCH to edit recipe
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = getId(req.getRequestURI());
        Recipe recipe = getRecipeService().getRecipeById(id);
        User user = getUserService().getCurrentUser(req);

        if (recipe.getAuthor().equals(user)) {
            getRecipeService().delete(recipe);
        } else {
            resp.sendError(403);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);
        int id = getId(req.getRequestURI());
        Recipe recipe = getRecipeService().getRecipeById(id);
        boolean canEdit = recipe.getAuthor().equals(user);

        getHelper().render(
                resp,
                "RecipesId.ftl",
                new HashMap<>() {{
                    put("user", user);
                    put("recipe", recipe);
                    put("can_edit", canEdit);
                }}
        );
    }

    private int getId(String uri) {
        Matcher matcher = Pattern.compile("/recipes/([1-9][0-9]*)")
                .matcher(uri);
        matcher.find();
        String id = matcher.group(1);
        return Integer.parseInt(id);
    }
}
