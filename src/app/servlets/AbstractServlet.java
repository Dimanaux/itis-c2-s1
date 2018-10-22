package app.servlets;

import app.services.UserService;
import app.util.Helper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class AbstractServlet extends HttpServlet {
    private Helper helper;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        helper = new Helper(getServletContext());
        userService = new UserService();
    }

    public Helper getHelper() {
        return helper;
    }

    public UserService getUserService() {
        return userService;
    }
}
