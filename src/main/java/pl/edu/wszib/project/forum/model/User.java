package pl.edu.wszib.project.forum.model;

import javax.persistence.*;

@Entity(name ="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String pass;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String imageLink;

    public User(int id, String login, String pass, Role role) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.role = role;


    }

    public boolean isBanned(){
        if (getRole() == Role.BANNED)
            return true;

        return false;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role{
        ADMIN,
        USER,
        BANNED
    }
}
