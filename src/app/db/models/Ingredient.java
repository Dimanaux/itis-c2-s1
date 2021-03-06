package app.db.models;

import app.db.annotations.Column;
import app.db.annotations.Id;
import app.db.annotations.Table;
import main.java.app.db.annotations.*;

@Table(table = "ingredients")
public class Ingredient extends Model {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
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
