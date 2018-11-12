package main.java.app.db.dao.postgres;

import main.java.app.db.models.Dish;
import main.java.app.db.models.Ingredient;
import main.java.app.db.models.Recipe;
import main.java.app.db.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RecipeDao extends AbstractDao<Recipe> implements main.java.app.db.dao.RecipeDao {
    private UserDao userDao;
    private IngredientDao ingredientDao;

    public RecipeDao() {
        super(ConnectionSingleton.getInstance());
        userDao = new UserDao();
        ingredientDao = new IngredientDao();
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

    private Recipe load(int id) {
        try {
            PreparedStatement statement = super.connection.prepareStatement(
                    "SELECT r.id, r.title, r.author_id, r.dish_id, r.publish_date, r.text, d.name AS d_name, u.name, u.username FROM recipe r " +
                            " INNER JOIN \"user\" u on r.author_id = u.id" +
                            " INNER JOIN dish d on r.dish_id = d.id " +
                            " WHERE r.id = ?"
            );
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            rs.next();
            Recipe recipe = instance(rs);

            User author = userDao.instance(rs);
            author.setId(recipe.getAuthorId());

            recipe.setAuthor(author);

            Dish dish = new Dish();
            dish.setId(recipe.getDishId());
            dish.setName(getString(rs, "d_name"));
            recipe.setDish(dish);

            return recipe;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Recipe getById(int id) {
        Recipe recipe = load(id);
        List<Ingredient> ingredients = ingredientDao.getByRecipe(recipe);
        recipe.setIngredients(ingredients);
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

    @Override
    public List<Recipe> getRecipesByIngredients(List<Integer> ids) {
        StringBuilder query = new StringBuilder(
                "SELECT DISTINCT r.id, r.title, r.author_id, r.publish_date, r.dish_id" +
                        " FROM recipe r" +
                        " INNER JOIN ingredient_recipe_relation irr ON r.id = irr.recipe_id" +
                        " INNER JOIN ingredient i on irr.ingredient_id = i.id" +
                        " WHERE NOT EXISTS(SELECT 1" +
                        " FROM ingredient_recipe_relation ir" +
                        " WHERE ir.recipe_id = r.id" +
                        " AND ir.ingredient_id NOT IN (?"
        );
        for (int i = 1; i < ids.size(); i++) {
            query.append(", ?");
        }
        query.append("));");

        try {
            PreparedStatement statement = connection.prepareStatement(query.toString());
            int count = 1;
            for (var ingredientId : ids) {
                statement.setInt(count, ingredientId);
                count++;
            }

            LinkedList<Recipe> recipes = new LinkedList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Recipe recipe = instance(rs);
                recipes.add(recipe);
            }
            return recipes;
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }
}
