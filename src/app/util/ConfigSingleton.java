package app.util;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;

class ConfigSingleton {
    private static Configuration config;

    static Configuration getConfig(ServletContext context) {
        if (config == null) {
            config = new Configuration(Configuration.VERSION_2_3_28);
            config.setServletContextForTemplateLoading(
                    context,
                    "/WEB-INF/templates"
            );
            config.setTemplateExceptionHandler(
                    TemplateExceptionHandler.HTML_DEBUG_HANDLER
            );
        }
        return config;
    }
}
