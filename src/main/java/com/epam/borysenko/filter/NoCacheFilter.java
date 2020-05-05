package com.epam.borysenko.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(value = "/*",
        dispatcherTypes = {DispatcherType.REQUEST}
)
public class NoCacheFilter implements Filter {

    private static final String CACHE_CONTROL = "Cache-Control";
    public static final String NO_STORE =  "no-store";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader(CACHE_CONTROL, NO_STORE);
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
