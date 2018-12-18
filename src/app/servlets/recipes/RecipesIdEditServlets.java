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

@WebServlet(name = "RecipesIdEditServlet", urlPatterns = {"/recipes/:id/edit"})
public class RecipesIdEditServlets extends AbstractRecipesServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);
        int id = getId(req.getRequestURI());
        Recipe recipe = getRecipeService().getRecipeById(id);

        if (!recipe.getAuthor().equals(user)) {
            resp.sendRedirect("/recipes");
            return;
        }

        getHelper().render(
                resp,
                "RecipesIdEdit.ftl",
                new HashMap<>() {{
                    put("user", user);
                    put("recipe", recipe);
                }}
        );
    }

    private int getId(String uri) {
        Matcher matcher = Pattern.compile("/recipes/([1-9][0-9]*)/edit")
                .matcher(uri);
        matcher.find();
        String id = matcher.group(1);
        return Integer.parseInt(id);
    }
}
