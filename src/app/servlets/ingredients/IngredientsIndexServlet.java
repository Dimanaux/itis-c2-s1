package app.servlets.ingredients;

import app.db.models.Ingredient;
import app.db.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "IngredientsIndexServlet", urlPatterns = {"/ingredients"})
public class IngredientsIndexServlet extends AbstractIngredientsServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().authenticate(req);
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
        List<Ingredient> ingredients = getIngredientService().getAllIngredients();
        User user = getUserService().authenticate(req);
        getHelper().render(
                resp,
                "IngredientsIndex.ftl",
                new HashMap<>() {{
                    put("user", user);
                    put("ingredients", ingredients);
                }}
        );
    }
}
