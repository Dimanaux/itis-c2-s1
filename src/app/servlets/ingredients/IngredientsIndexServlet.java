package app.servlets.ingredients;

import app.db.models.Ingredient;
import app.db.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IngredientsIndexServlet", urlPatterns = {"/ingredients"}, asyncSupported = true)
public class IngredientsIndexServlet extends AbstractIngredientsServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);
        if (user == null) {
            resp.sendError(403);
            return;
        }

        String ingredient_name = req.getParameter("ingredient_name");
        getIngredientService().create(ingredient_name);
        resp.sendRedirect("/ingredients");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Ingredient[] ingredients = getIngredientService().getAllIngredients().toArray(new Ingredient[0]);
        String json = getGson().toJson(ingredients);

        resp.setContentType("text/json");
        resp.getWriter().write(json);
        resp.getWriter().close();
    }
}
