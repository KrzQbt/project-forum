package pl.edu.wszib.project.forum.dao;

import pl.edu.wszib.project.forum.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);

    boolean persistUser(User user);

    User getUserById(int id);

    void updateUser(User user);
}
