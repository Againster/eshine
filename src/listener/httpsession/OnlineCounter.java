package httpsession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by root on 5/17/17.
 */
public class OnlineCounter implements HttpSessionListener {
    private static String s = "OnlineCounter";
    private static String p = "sessionTimeOut";
    private static String i = "600";
    private Logger log;

    public OnlineCounter () {
        log = LoggerFactory.getLogger("error");
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext ctx = session.getServletContext();
        Long counter = (Long) ctx.getAttribute(s);
        if (counter == null) {
            counter = new Long(0);
        }
        ctx.setAttribute(s, ++counter);

        String time = ctx.getInitParameter(p);
        session.setMaxInactiveInterval(Integer.parseInt( time == null ? i : time));
        log.info("present online counter " + counter);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext ctx = session.getServletContext();
        Long counter = (Long) ctx.getAttribute(s);
        if (counter == null) {
            return;
        }
        ctx.setAttribute(s, --counter);
        log.info("present online counter " + counter);
    }
}
