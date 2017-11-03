package com.godeltech.edushop.authentification;

import com.godeltech.edushop.annotation.Permissions;
import com.godeltech.edushop.dto.UserDTO;
import com.godeltech.edushop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

/**
 * Created by d.ihnatovich on 10/16/2017.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    private Map<String, Long> users = new HashMap<>();

    public void register(Long id, String token){
        users.put(token, id);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }

        Permissions permissionAnnotation = getPermissionAnnotation((HandlerMethod) handler);
        if (isNull(permissionAnnotation)) {
            return true;
        }

        String token = request.getHeader("token");
        if (!users.containsKey(token)) {
            throw new RuntimeException("token is empty");
        }

        Long userId = users.get(token);
        UserDTO userById = userService.getUserById(userId);
        if (isNull(userById)) {
            throw new RuntimeException("User is not found");
        }

        boolean roleMatch = Stream.of(permissionAnnotation.roles())
                .anyMatch(role -> role.equals(userById.getRole().getName()));
        if (!roleMatch) {
            throw new RuntimeException("Permission denied");
        }

        return super.preHandle(request, response, handler);
    }

    private Permissions getPermissionAnnotation(HandlerMethod handler) {
        Permissions annotation = handler.getMethod().getAnnotation(Permissions.class);
        if (isNull(annotation)) {
            annotation = handler.getBean().getClass().getAnnotation(Permissions.class);
        }

        return annotation;
    }

}
