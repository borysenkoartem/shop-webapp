package com.epam.borysenko.filter.compression;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(value = "/*",
        dispatcherTypes = {DispatcherType.REQUEST}
)
public class GzipFilter  implements Filter {

    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String GZIP ="gzip";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (acceptsGZipEncoding(req)) {
            GZipServletResponseWrapper gzipResponse =
                    new GZipServletResponseWrapper(resp);
            chain.doFilter(request, gzipResponse);
            gzipResponse.close();
        } else {
            chain.doFilter(req, resp);
        }
    }
    private boolean acceptsGZipEncoding(final HttpServletRequest httpRequest) {
        String acceptEncoding = httpRequest.getHeader(ACCEPT_ENCODING);
        return acceptEncoding != null && acceptEncoding.contains(GZIP);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
