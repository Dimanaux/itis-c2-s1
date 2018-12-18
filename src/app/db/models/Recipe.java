package app.db.models;


// TODO: 18/10/10
// What to do with many-to-many relation?

import app.db.annotations.Column;
import app.db.annotations.Table;
import app.db.annotations.Id;

import java.util.Date;
import java.util.List;

@Table(table = "recipe")
public class Recipe extends Model {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author_id")
    private Integer authorId;
    private User author;

    @Column(name = "dish_id")
    private Integer dishId;
    private Dish dish;

    @Column(name = "text")
    private String text;

    @Column(name = "publish_date")
    private Date date;

    private List<RecipeComment> comments;
    private List<Ingredient> ingredients;

    public Integer getDishId() {
        return dishId;
    }

    public List<RecipeComment> getComments() {
        return comments;
    }

    public void setComments(List<RecipeComment> comments) {
        this.comments = comments;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
        this.authorId = author.getId();
    }

    public Integer getRecipeId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
        this.dishId = dish.getId();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
