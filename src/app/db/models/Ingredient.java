package app.db.models;

import app.db.annotations.*;

@Table(tableName = "ingredient")
public class Ingredient implements Model {
    @Id
    @Column(columnName = "id")
    private Integer id;

    @Column(columnName = "name")
    private String name;


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
}
