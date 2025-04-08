package com.komalbandi.kb_blog.middleware;

import com.komalbandi.kb_blog.utils.JsonWebTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JsonWebTokenUtils jsonWebTokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            if (!jsonWebTokenUtils.validateJwtToken(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                return false;
            }else{
                return true;
            }
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No Token");
        return false;
    }
}
