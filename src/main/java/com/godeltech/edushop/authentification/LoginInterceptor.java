package com.godeltech.edushop.authentification;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by d.ihnatovich on 10/16/2017.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Map<Long, String> users = new HashMap<>();

    public void register(Long id, String token){
        users.put(id, token);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        return super.preHandle(request, response, handler);
    }

}
