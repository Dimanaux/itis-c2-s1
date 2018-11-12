package main.java.app.servlets.dishes;

import main.java.app.db.models.Dish;
import main.java.app.services.DishService;
import main.java.app.servlets.AbstractServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DishesIndexServlet", urlPatterns = {"/dishes"})
public class DishesIndexServlet extends AbstractServlet {
    DishService getDishService() {
        return dishService;
    }

    private DishService dishService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dishService = new DishService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        boolean isVegan = Boolean.parseBoolean(req.getParameter("is_vegan"));
        String description = req.getParameter("description");
        dishService.create(name, isVegan, description);
        resp.sendRedirect("/dishes");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Dish[] dishes = getDishService().getAll().toArray(new Dish[0]);

        resp.setContentType("text/json");
        String json = getGson().toJson(dishes);
        resp.getWriter().write(json);
        resp.getWriter().close();
    }
}
