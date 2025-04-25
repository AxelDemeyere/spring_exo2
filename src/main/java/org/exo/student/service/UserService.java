package org.exo.student.service;

import jakarta.servlet.http.HttpSession;
import org.exo.student.dao.UserRepository;
import org.exo.student.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;

    public boolean login(String login, String password) {
        User user = userRepository.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return true;
        }
        return false;
    }

    public void logout() {
        session.invalidate();
    }

    public boolean checkLogin() {
        return session.getAttribute("user") != null;
    }

    public void register(User user) {
        userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User getCurrentUser() {
        return (User) session.getAttribute("user");
    }

}