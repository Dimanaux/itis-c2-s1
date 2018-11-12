package main.java.app.servlets;

import main.java.app.db.models.Recipe;
import main.java.app.servlets.recipes.AbstractRecipesServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "SearchJsonServlet", urlPatterns = {"/search.json"}, asyncSupported = true)
public class SearchJsonServlet extends AbstractRecipesServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String query = req.getParameter("query");

        List<String> ingredientsNames = Arrays.asList(query.split(" "));

        List<Recipe> recipesByIngredients = getRecipeService().getRecipesByIngredients(ingredientsNames);

        resp.setContentType("text/json");
        String json = getGson().toJson(recipesByIngredients.toArray());
        resp.getWriter().write(json);
        resp.getWriter().close();
    }
}
