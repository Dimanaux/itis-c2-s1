package app.servlets.recipes;

import app.services.RecipeService;
import app.services.UserService;
import app.servlets.AbstractServlet;
import app.util.Helper;

public abstract class AbstractRecipesServlet extends AbstractServlet {
    private Helper helper;
    private UserService userService;
    private RecipeService recipeService;

    @Override
    public void init() {
        helper = new Helper(getServletContext());
        userService = new UserService();
        recipeService = new RecipeService();
    }

    Helper getHelper() {
        return helper;
    }

    UserService getUserService() {
        return userService;
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }
}
