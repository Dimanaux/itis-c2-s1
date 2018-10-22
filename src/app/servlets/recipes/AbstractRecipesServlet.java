package app.servlets.recipes;

import app.services.RecipeService;
import app.servlets.AbstractServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public abstract class AbstractRecipesServlet extends AbstractServlet {
    private RecipeService recipeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        recipeService = new RecipeService();
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }
}
