package app.services;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User authenticate(HttpServletRequest request);
}
