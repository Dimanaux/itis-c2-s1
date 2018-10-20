package app.db.models;

import app.db.annotations.*;

@Table(tableName = "\"user\"")
public class User implements Model {
    @Id
    @Column(columnName = "id")
    private Integer id;
    
    @Column(columnName = "username")
    private String username;
    
    @Column(columnName = "password")
    private String password;
    
    @Column(columnName = "name")
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
