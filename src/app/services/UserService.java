package app.services;

import app.db.dao.UserDao;
import app.db.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        if (req.getCookies() == null || req.getCookies().length == 0) {
            return null;
        }

        Optional<Cookie> token = Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals("token"))
                .findAny();
        return token.map(cookie -> authorize(req, userDao.getUserByToken(cookie.getValue()))).orElse(null);
    }

    /**
     * Gets user by username, returns user if password matches
     * @param req request with parameters username, password
     * @return user instance if found and passwords match, else null
     */
    public User authenticate(HttpServletRequest req) {
        // get user by id
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

    /**
     * Remembers user for 3 days
     * @param resp response to send to user
     * @param user user instance
     */
    public void remember(HttpServletResponse resp, User user) {
        String token = securityService.createToken(user.getUsername());
        Cookie cookie = new Cookie("remember_me", token);
        // expires in 3 days
        cookie.setMaxAge(3 * 24 * 60 * 60);
        resp.addCookie(cookie);
        userDao.saveOrUpdateToken(token, user.getId());
    }

    /**
     * give user to session
     * @param req user's request
     * @param user user instance
     * @return same user
     */
    public User authorize(HttpServletRequest req, User user) {
        req.getSession().setAttribute("current_user", user);
        return user;
    }

    public User createUser(String username, String password, String name) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(
                securityService.hashPassword(password)
        );
        user.setName(name);
        userDao.save(user);
        return user;
    }
}
