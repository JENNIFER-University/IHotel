package edu.jennifer.ihotel.util;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by khalid on 9/1/16.
 */
public class ExecutionTimeLogger extends HandlerInterceptorAdapter{

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long randomDelay = Common.getRandom(1000, 8500);
        boolean  test = Common.processLong(100, 500, 20);
        if(!test)
            Common.getToken(randomDelay/2);
    }
}
