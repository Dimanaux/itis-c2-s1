package app.servlets.ingredients;

import app.services.IngredientService;
import app.servlets.AbstractServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class AbstractIngredientsServlet extends AbstractServlet {
    private IngredientService ingredientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ingredientService = new IngredientService();
    }

    public IngredientService getIngredientService() {
        return ingredientService;
    }
}
