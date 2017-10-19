package interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by root on 5/23/17.
 */

@ControllerAdvice
public class ExceptionInterceptor implements HandlerExceptionResolver {
    @ExceptionHandler({Exception.class})
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Logger log = LoggerFactory.getLogger("error");
        log.error("", ex.getCause());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("root/root");
        return mv;
    }
}
