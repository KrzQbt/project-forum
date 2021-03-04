package pl.edu.wszib.project.forum.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    User user;
    @ManyToOne
    @JoinColumn(name = "forumthread_id",nullable = false)
    ForumThread forumThread;

    public Post() {
    }

    public Post(int id, Date dateTime, String content, User user, ForumThread forumThread) {
        this.id = id;
        this.dateTime = dateTime;
        this.content = content;
        this.user = user;
        this.forumThread = forumThread;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public ForumThread getForumThread() {
        return forumThread;
    }

    public void setForumThread(ForumThread forumThread) {
        this.forumThread = forumThread;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
