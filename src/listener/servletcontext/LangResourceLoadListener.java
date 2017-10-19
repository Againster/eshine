package servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class LangResourceLoadListener implements ServletContextListener {
    private static HashMap<String, HashMap<String, String>> langmap = new HashMap<String,HashMap<String, String>>();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        String param = ctx.getInitParameter("langResourcePath");
        String path = ctx.getRealPath(param);

        File file = new File(path);
        URL[] urls = new URL[0];
        try {
            urls = new URL[]{file.toURI().toURL()};
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ClassLoader loader = new URLClassLoader(urls);

        ResourceBundle bundle;
        Locale locale;
        HashMap<String, String> hashmap;
        for (String[] x : iso6391_31661.code) {
            locale = new Locale(x[0], x[1]);
            bundle = ResourceBundle.getBundle("lang", locale, loader);
            hashmap = new HashMap<>();
            for(String y: bundle.keySet()) {
                hashmap.put(y, bundle.getString(y));
            }

            langmap.put(locale.toString(), hashmap);
        }
        ctx.setAttribute("lang_bundle_hashmap", langmap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
