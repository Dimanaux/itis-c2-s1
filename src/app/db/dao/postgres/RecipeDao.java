package app.db.dao.postgres;

import app.db.models.Recipe;
import app.db.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RecipeDao extends AbstractDao<Recipe> implements app.db.dao.RecipeDao {
    private UserDao userDao;

    public RecipeDao() {
        super(ConnectionSingleton.getInstance());
        userDao = new UserDao();
    }

    @Override
    public String getTableName() {
        return "recipe";
    }

    @Override
    public Recipe instance(ResultSet rs) {
        Recipe recipe = new Recipe();
        recipe.setId(getInt(rs, "id"));
        recipe.setTitle(getString(rs, "title"));
        recipe.setAuthorId(getInt(rs, "author_id"));
        recipe.setDishId(getInt(rs, "dish_id"));
        recipe.setDate(getDate(rs, "publish_date"));
        recipe.setText(getString(rs, "text"));
        return recipe;
    }

    @Override
    public List<Recipe> getByAuthor(User author) {
        List<Recipe> recipes = new LinkedList<>();
        try {
            PreparedStatement statement = super.connection.prepareStatement(
                    "SELECT * FROM recipe WHERE author_id = ?"
            );
            statement.setInt(1, author.getId());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Recipe recipe = instance(rs);
                recipe.setAuthor(author);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    @Override
    public List<Recipe> getByAuthorId(int id) {
        User author = userDao.getById(id);
        return getByAuthor(author);
    }
}
