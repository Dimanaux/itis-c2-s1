package app.services;

import app.db.dao.UserDao;
import app.db.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

        Cookie rememberMe = findRememberMe(req.getCookies());

        if (rememberMe == null) {
            return null;
        }

        return userDao.getUserByToken(rememberMe.getValue());
    }

    private Cookie findRememberMe(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("remember_me")) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * Gets user by username, returns user if password matches
     * @param req request with parameters username, password
     * @return user instance if found and passwords match, else null
     */
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

    public void logout(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("current_user");
        Cookie delete = new Cookie("remember_me", "pop");
        delete.setMaxAge(1);
        resp.addCookie(delete);
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
        return userDao.save(user);
    }

    public void updateUserPicture(InputStream input, User user) throws IOException {
        File file = new File("C:\\Users\\cosmos\\IdeaProjects\\itis-c2-s1\\out\\artifacts\\itis_c2_s1_war_exploded\\static\\res\\" + user.getUsername() + ".jpg");
        FileOutputStream output = new FileOutputStream(file, false);

        byte[] bytes = new byte[512];

        int count = input.read(bytes);
        while (count != -1) {
            output.write(bytes);
            count = input.read(bytes);
        }
        input.close();
        output.close();
    }

    public void updateProfile(User user, String name) {
        user.setName(name);
        userDao.update(user);
    }
}
