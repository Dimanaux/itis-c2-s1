package app.db.models;

import app.db.annotations.*;

@Table(tableName = "dish")
public class Dish implements Model {
    @Id
    @Column(columnName = "id")
    private Integer id;

    @Column(columnName = "name")
    private String name;

    @Column(columnName = "description")
    private String description;

    @Column(columnName = "is_vegan")
    private Boolean isVegan;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVegan() {
        return isVegan;
    }

    public void setVegan(Boolean vegan) {
        isVegan = vegan;
    }
}
