package app.db.models;

import app.db.annotations.*;

import java.util.Date;

@Table(tableName = "post_comment")
public class PostComment implements Model {
    @Id
    @Column(columnName = "id")
    private Integer id;

    @Column(columnName = "authorId")
    private Integer authorId;
    private User author;

    @Column(columnName = "postId")
    private Integer postId;
    private Post post;

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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
