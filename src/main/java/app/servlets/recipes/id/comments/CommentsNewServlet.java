package main.java.app.servlets.recipes.id.comments;

import main.java.app.db.models.Recipe;
import main.java.app.db.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/recipes/:id/comments/new"})
public class CommentsNewServlet extends AbstractCommentsServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);
        int postId = getRecipeId(req.getRequestURI());
        Recipe recipe = getRecipeService().getRecipeById(postId);
        if (user == null) {
            resp.sendRedirect("/auth");
        } else if (recipe == null) {
            resp.sendRedirect("/recipes");
        } else {
            getHelper().render(
                    resp,
                    "RecipesIdCommentsNew.ftl",
                    new HashMap<>() {{
                        put("user", user);
                        put("recipe", recipe);
                    }}
            );
        }
    }
}
