package app.services;

import app.db.models.User;

import javax.servlet.http.HttpServletRequest;

public class UserService {
    private SecurityService securityService;

    public UserService() {
        this.securityService = new SecurityService();
    }

    public User authenticate(HttpServletRequest request) {
        // TODO: 18/10/20 implement
        return null;
    }

}
