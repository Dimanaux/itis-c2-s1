package app.db.dao.postgres;

import app.db.models.Ingredient;
import app.db.models.Recipe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class IngredientDao extends AbstractDao<Ingredient> implements app.db.dao.IngredientDao {
    public IngredientDao() {
        super(ConnectionSingleton.getInstance());
    }

    @Override
    public Ingredient getByName(String name) {
        return null;
    }

    @Override
    public List<Ingredient> getByRecipe(Recipe recipe) {
        return getByRecipeId(recipe.getId());
    }

    @Override
    public List<Ingredient> getByRecipeId(int recipeId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT i.id, i.name FROM ingredient i JOIN ingredient_recipe_relation irr on i.id = irr.ingredient_id WHERE irr.recipe_id = ?"
            );
            statement.setInt(1, recipeId);
            ResultSet rs = statement.executeQuery();
            List<Ingredient> ingredients = new LinkedList<>();
            while (rs.next()) {
                Ingredient instance = instance(rs);
                ingredients.add(instance);
            }
            return ingredients;
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    @Override
    public String getTableName() {
        return "ingredient";
    }

    @Override
    public Ingredient instance(ResultSet rs) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(getInt(rs, "id"));
        ingredient.setName(getString(rs, "name"));
        return ingredient;
    }
}
