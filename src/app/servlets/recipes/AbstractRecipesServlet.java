package app.servlets.recipes;

import app.services.DishService;
import app.services.IngredientService;
import app.services.RecipeService;
import app.servlets.AbstractServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public abstract class AbstractRecipesServlet extends AbstractServlet {
    private RecipeService recipeService;
    private DishService dishService;
    private IngredientService ingredientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        recipeService = new RecipeService();
        dishService = new DishService();
        ingredientService = new IngredientService();
    }

    public IngredientService getIngredientService() {
        return ingredientService;
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }

    public DishService getDishService() {
        return dishService;
    }
}
