package app.db.models;

import app.db.annotations.*;

import java.util.Date;

@Table(tableName = "recipe_comment")
public class RecipeComment implements Model {
    @Id
    @Column(columnName = "id")
    private Integer id;

    @Column(columnName = "authorId")
    private Integer authorId;
    private User author;

    @Column(columnName = "recipeId")
    private Integer recipeId;
    private Recipe recipe;

    @Column(columnName = "text")
    private String text;

    @Column(columnName = "publish_date")
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
