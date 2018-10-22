package app.services;

import app.db.dao.UserDao;
import app.db.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class UserService {
    private SecurityService securityService;
    private UserDao userDao;

    public UserService() {
        this.securityService = new SecurityService();
        userDao = new app.db.dao.postgres.UserDao();
    }

    public User getCurrentUser(HttpServletRequest req) {
        User currentUser = (User) req.getSession().getAttribute("current_user");
        if (currentUser != null) {
            return currentUser;
        }

        Optional<Cookie> token = Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals("token"))
                .findAny();
        return token.map(cookie -> userDao.getUserByToken(cookie.getValue())).orElse(null);
    }

    public User authenticate(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = securityService.hashPassword(
                req.getParameter("password")
        );

        User user = userDao.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public void authorize(HttpServletRequest req, User user) {
        req.getSession().setAttribute("current_user", user);
    }
}
