package pl.edu.wszib.project.forum.services;

import pl.edu.wszib.project.forum.model.User;
import pl.edu.wszib.project.forum.model.view.RegistrationModel;

public interface IUserService {
    void authenticate(User user);
    void logout();
    boolean register(RegistrationModel registrationModel);
    User getUserById(int id);
    void banUser(User user);
    void unbanUser(User user);
}
