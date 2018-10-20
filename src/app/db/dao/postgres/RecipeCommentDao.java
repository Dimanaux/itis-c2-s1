package app.db.dao.postgres;

import app.db.models.RecipeComment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeCommentDao extends AbstractDao<RecipeComment> implements app.db.dao.RecipeCommentDao {
    RecipeCommentDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    protected RecipeComment instance(ResultSet rs) throws SQLException {
        if (rs.next()) {
            RecipeComment recipeComment = new RecipeComment();
            recipeComment.setId(rs.getInt("id"));
            recipeComment.setAuthorId(rs.getInt("author_id"));
            recipeComment.setRecipeId(rs.getInt("recipe_id"));
            recipeComment.setDate(rs.getDate("publish_date"));
            recipeComment.setText(rs.getBlob("text").toString());
            return recipeComment;
        } else {
            return null;
        }
    }
}
