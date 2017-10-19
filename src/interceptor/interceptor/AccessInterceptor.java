package interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by root on 5/24/17.
 */

@ControllerAdvice
public class AccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Logger log = LoggerFactory.getLogger("error");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        RequestDispatcher dispatcher = null;
        if (user == null) {
            String from = request.getRequestURI();
            int i = from.indexOf("/", 1);
            from = from.substring(i);
            response.sendRedirect( request.getContextPath() + "/access/login?from=" + from);
            log.info("accessinterceptor redirect: {}", from + request.getQueryString());
        } else {
            log.info("login user: {}, query: {}", session.getAttribute("user"), request.getQueryString());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
