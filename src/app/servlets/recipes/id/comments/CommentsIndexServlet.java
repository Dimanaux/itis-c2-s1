package app.servlets.recipes.id.comments;

import app.db.models.Recipe;
import app.db.models.RecipeComment;
import app.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/recipes/:id/comments"})
public class CommentsIndexServlet extends AbstractCommentsServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserService().authenticate(req);
        int recipeId = getRecipeId(req.getRequestURI());
        Recipe recipe = getRecipeService().getRecipeById(recipeId);

        if (user == null || recipe == null) {
            resp.sendError(403);
            return;
        }
        String text = req.getParameter("text");

        RecipeComment comment = getCommentService().createComment(recipe, user, text);
        if (comment == null) {
            resp.sendError(500);
        } else {
            resp.sendRedirect("/recipes/" + recipe.getId() + "/comments/" + comment.getId());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO: 18/10/20 ?
        User user = getUserService().authenticate(req);

        if (user == null) {
            resp.sendRedirect("/auth");
            return;
        }

        int postId = getRecipeId(req.getRequestURI());
        Recipe recipe = getRecipeService().getRecipeById(postId);
        getHelper().render(
                resp,
                "RecipesIdComments.ftl",
                new HashMap<>() {{
                    put("user", user);
                    put("recipe", recipe);
                }}
        );
    }
}
