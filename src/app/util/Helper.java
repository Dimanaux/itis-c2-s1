package app.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class Helper {
    private final Configuration configuration;

    public Helper(ServletContext context) {
        configuration = ConfigSingleton.getConfig(context);
    }

    public void render(HttpServletResponse resp, String path, Map<String, Object> root) throws IOException {
        resp.setContentType("text/html");
        Template template = configuration.getTemplate(path);

        try {
            template.process(root, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
            resp.sendError(500);
        }
        resp.getWriter().close();
    }
}
