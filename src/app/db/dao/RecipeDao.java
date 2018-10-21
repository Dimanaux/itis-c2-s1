package app.db.dao;

import app.db.models.Recipe;
import app.db.models.User;

import java.util.List;

public interface RecipeDao extends Dao<Recipe> {
    List<Recipe> getByAuthor(User author);
    List<Recipe> getByAuthorId(int id);
}
