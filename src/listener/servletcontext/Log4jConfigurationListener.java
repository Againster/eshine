package servletcontext;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.URL;

/**
 * Created by root on 5/15/17.
 */
public class Log4jConfigurationListener implements ServletContextListener {
    public static String ROOT_WEB_LoggerFactory_ATTRIBUTE = Log4jConfigurationListener.class.getName();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("config log4j2 start");
        ServletContext ctx = servletContextEvent.getServletContext();
        String conf = "/WEB-INF/" + ctx.getInitParameter("log4j2.configurationFile");
        URL url = null;
        try {
            url = ctx.getResource(conf);
            System.out.println("uri:" + url.toURI());
            if(url != null) {
                Configurator.initialize(ctx.getServletContextName(), ctx.getClassLoader(), url.toURI(), ctx);
            } else {
                System.out.println("get resource error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger log = LoggerFactory.getLogger("error");
        System.out.println("config log4j2 over");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
