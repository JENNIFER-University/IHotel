package edu.jennifer.hotel.config;

import edu.jennifer.hotel.util.Conf;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 12/28/17 11:16 AM.
 */
public class SettingsHelper implements Filter {


    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String currentRequestUrl = request.getRequestURI();

        if (isResource(currentRequestUrl) || currentRequestUrl.equals("/doSetup")) {
            filterChain.doFilter(request, response);
        }else {
            checkIfSetupCompleted(request, response, filterChain);
        }
    }

    private void checkIfSetupCompleted(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final String setupUrl = String.format("%s/setup", request.getContextPath());
        boolean isSetupUrl = request.getRequestURI().equals(setupUrl);
        boolean isSetupComplete = Conf.getInstance().isConfFileExists();

        if (isSetupUrl || isSetupComplete) {
            filterChain.doFilter(request, response);
        }else {
            response.sendRedirect(setupUrl);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isResource(String url) {
        return url.endsWith("js") || url.endsWith("css") || url.endsWith("png") || url.endsWith("jpg");
    }
}
