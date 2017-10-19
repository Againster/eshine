package servletrequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servletcontext.LangResourceLoadListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by root on 5/18/17.
 */
public class PublicArgument implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        HttpSession session = request.getSession();
        ServletContext ctx = request.getServletContext();
        String lang = ctx.getInitParameter("LangDefault");
        String country = ctx.getInitParameter("CountryDefault");
        String img_theme = ctx.getInitParameter("imgTheme");

        request.setAttribute("baseurl", ctx.getContextPath() + "/");
        request.setAttribute("lang", lang);
        request.setAttribute("img_theme", img_theme);

        Locale locale = request.getLocale();
        if (locale == null) {
            locale = new Locale(lang, country);
        }
        Object object =  session.getAttribute("locale");
        if( object != null) {
            locale = (Locale) object;
        }
        HashMap<String, HashMap<String, String>> langmap = (HashMap<String, HashMap<String, String>>) ctx.getAttribute("lang_bundle_hashmap");
        HashMap<String, String> lang_bundle= langmap.get(locale.toString());
        request.setAttribute("lang_bundle", lang_bundle);
    }
}
