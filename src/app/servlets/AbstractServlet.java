package app.servlets;

import app.services.UserService;
import app.util.Helper;
import com.google.gson.Gson;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class AbstractServlet extends HttpServlet {
    private Helper helper;
    private UserService userService;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        helper = new Helper(getServletContext());
        userService = new UserService();
        gson = new Gson();
    }

    public Helper getHelper() {
        return helper;
    }

    public UserService getUserService() {
        return userService;
    }

    public Gson getGson() {
        return gson;
    }
}
