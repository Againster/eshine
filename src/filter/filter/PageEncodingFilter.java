package filter;

import servletcontext.LangResourceLoadListener;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by root on 5/27/17.
 */
public class PageEncodingFilter implements Filter {
    String encode;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
