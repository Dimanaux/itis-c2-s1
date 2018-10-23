package app.db.dao.postgres;

import app.db.models.Recipe;
import app.db.models.RecipeComment;
import app.db.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RecipeCommentDao extends AbstractDao<RecipeComment> implements app.db.dao.RecipeCommentDao {
    private final RecipeDao recipeDao;
    private final UserDao userDao;

    public RecipeCommentDao() {
        super(ConnectionSingleton.getInstance());
        this.userDao = new app.db.dao.postgres.UserDao();
        this.recipeDao = new app.db.dao.postgres.RecipeDao();
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public RecipeComment instance(ResultSet rs) {
        RecipeComment recipeComment = new RecipeComment();
        recipeComment.setId(getInt(rs, "id"));
        recipeComment.setAuthorId(getInt(rs, "author_id"));
        recipeComment.setRecipeId(getInt(rs, "recipe_id"));
        recipeComment.setDate(getDate(rs, "publish_date"));
        recipeComment.setText(getString(rs, "text"));
        return recipeComment;
    }

    @Override
    public List<RecipeComment> getByRecipe(Recipe recipe) {
        List<RecipeComment> comments = new LinkedList<>();
        try {
            PreparedStatement statement = super.connection.prepareStatement(
                    "SELECT * FROM recipe_comment c " +
                            "INNER JOIN \"user\" u on c.author_id = u.id WHERE c.id = ?"
            );
            statement.setInt(1, recipe.getId());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                RecipeComment comment = instance(rs);
                User author = userDao.instance(rs);
                author.setId(comment.getAuthorId());

                comment.setAuthor(author);
                comment.setRecipe(recipe);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public List<RecipeComment> getByRecipeId(int id) {
        Recipe recipe = recipeDao.getById(id);
        return getByRecipe(recipe);
    }
}
