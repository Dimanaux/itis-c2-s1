package app.db.models;


// TODO: 18/10/10
// What to do with many-to-many relation?

import app.db.annotations.*;

import java.util.Date;

@Table(tableName = "recipe")
public class Recipe implements Model {
    @Id
    @Column(columnName = "id")
    private Integer id;

    @Column(columnName = "authorId")
    private Integer authorId;
    private User author;

    @Column(columnName = "dishId")
    private Integer dishId;
    private Dish dish;

    @Column(columnName = "text")
    private String text;

    @Column(columnName = "publish_date")
    private Date date;


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

    public Integer getDishId() {
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
