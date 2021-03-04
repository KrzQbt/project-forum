package pl.edu.wszib.project.forum.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.project.forum.model.User;

@Component
@SessionScope
public class SessionObject {
    private User loggedUser = null;
    private String info = null;


    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
    public boolean isLogged() {
        return this.loggedUser != null;
    }
    public int getUserId() { return this.loggedUser.getId(); }

    public String getInfo() {
        String temp = this.info;
        this.info = null;
        return temp;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
