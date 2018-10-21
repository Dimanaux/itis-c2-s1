package app.db.models;

import app.db.annotations.*;

import java.util.Date;

@Table(table = "recipe_comment")
public class RecipeComment extends Model {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "authorId")
    private Integer authorId;
    private User author;

    @Column(name = "recipeId")
    private Integer recipeId;
    private Recipe recipe;

    @Column(name = "text")
    private String text;

    @Column(name = "publish_date")
    private Date date;

    @Override
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
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
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        setRecipeId(recipe.getId());
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
