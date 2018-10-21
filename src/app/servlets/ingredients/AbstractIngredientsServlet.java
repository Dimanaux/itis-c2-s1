package app.servlets.ingredients;

import app.services.IngredientService;
import app.services.UserService;
import app.util.Helper;

import javax.servlet.http.HttpServlet;

public class AbstractIngredientsServlet extends HttpServlet {
    private Helper helper;
    private UserService userService;
    private IngredientService ingredientService;

    @Override
    public void init() {
        helper = new Helper(getServletContext());
        userService = new UserService();
        ingredientService = new IngredientService();
    }

    public Helper getHelper() {
        return helper;
    }

    public UserService getUserService() {
        return userService;
    }

    public IngredientService getIngredientService() {
        return ingredientService;
    }
}
