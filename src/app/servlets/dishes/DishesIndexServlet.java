package app.servlets.dishes;

import app.db.models.Dish;
import app.services.DishService;
import app.servlets.AbstractServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DishesIndexServlet", urlPatterns = {"/dishes"})
public class DishesIndexServlet extends AbstractServlet {
    public DishService getDishService() {
        return dishService;
    }

    private DishService dishService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dishService = new DishService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dish[] dishes = getDishService().getAll().toArray(new Dish[0]);

        resp.setContentType("text/json");
        String json = getGson().toJson(dishes);
        resp.getWriter().write(json);
        resp.getWriter().close();
    }
}
