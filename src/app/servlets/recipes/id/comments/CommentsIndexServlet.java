package app.servlets.recipes.id.comments;

import app.db.models.Recipe;
import app.db.models.RecipeComment;
import app.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/recipes/:id/comments"})
public class CommentsIndexServlet extends AbstractCommentsServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserService().getCurrentUser(req);
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
        comment.setAuthor(user);

        String json = getGson().toJson(comment);
        resp.setContentType("text/json");
        resp.getWriter().print(json);
        resp.getWriter().close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserService().getCurrentUser(req);

        int recipeId = getRecipeId(req.getRequestURI());
        Recipe recipe = getRecipeService().getRecipeById(recipeId);

        List<RecipeComment> comments = getCommentService().getCommentsByRecipeId(recipeId);

        resp.setContentType("text/json");
        String json = getGson().toJson(comments.toArray());
        resp.getWriter().write(json);
        resp.getWriter().close();
    }
}
