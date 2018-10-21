package app.servlets.recipes.id.comments;


import app.services.RecipeCommentService;
import app.services.RecipeService;
import app.services.UserService;
import app.servlets.AbstractServlet;
import app.util.Helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractCommentsServlet extends AbstractServlet {
    private Helper helper;
    private UserService userService;
    private RecipeService recipeService;
    private RecipeCommentService commentService;

    @Override
    public void init() {
        helper = new Helper(getServletContext());
        userService = new UserService();
        recipeService = new RecipeService();
        commentService = new RecipeCommentService();
    }

    Helper getHelper() {
        return helper;
    }

    UserService getUserService() {
        return userService;
    }

    RecipeService getRecipeService() {
        return recipeService;
    }

    RecipeCommentService getCommentService() {
        return commentService;
    }

    int getRecipeId(String uri) {
        Matcher matcher = Pattern.compile("/recipes/([1-9][0-9]*)/.*")
                .matcher(uri);
        matcher.find();
        String id = matcher.group(1);
        return Integer.parseInt(id);
    }
}
