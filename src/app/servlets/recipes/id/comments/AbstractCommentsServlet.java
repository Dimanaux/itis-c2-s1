package app.servlets.recipes.id.comments;


import app.services.RecipeCommentService;
import app.servlets.recipes.AbstractRecipesServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractCommentsServlet extends AbstractRecipesServlet {
    private RecipeCommentService commentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        commentService = new RecipeCommentService();
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
