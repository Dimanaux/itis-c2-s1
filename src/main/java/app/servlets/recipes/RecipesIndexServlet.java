package main.java.app.servlets.recipes;

import main.java.app.db.models.Dish;
import main.java.app.db.models.Recipe;
import main.java.app.db.models.User;

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

        String title = req.getParameter("title");
        String text = req.getParameter("text");
        String dishId = req.getParameter("dish");
        String[] ingredients = req.getParameterValues("ingredients[]");

        int[] ids = new int[ingredients.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = Integer.parseInt(ingredients[i]);
        }

        Dish dish = getDishService().getById(Integer.parseInt(dishId));
        getRecipeService().create(dish, user, title, text, ids);
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
