package app.db.models;

import app.db.annotations.Column;
import app.db.annotations.Id;
import app.db.annotations.Table;
import main.java.app.db.annotations.*;

import java.util.Date;

@Table(table = "post_comment")
public class PostComment extends Model {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "author_id")
    private Integer authorId;
    private User author;

    @Column(name = "post_id")
    private Integer postId;
    private Post post;

    @Column(name = "text")
    private String text;

    @Column(name = "publish_date")
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
        this.authorId = author.getId();
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
        this.postId = post.getId();
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
