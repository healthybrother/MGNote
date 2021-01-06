package com.mgnote.mgnote.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class HttpUtil {

    public static Optional<HttpServletRequest> getRequest() {

        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if(Optional.ofNullable(servletRequestAttributes).isPresent()) {
            request = servletRequestAttributes.getRequest();
        }
        return Optional.ofNullable(request);
    }

    public static Optional<HttpServletResponse> getResponse(){
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = null;
        if(Optional.ofNullable(servletRequestAttributes).isPresent()) {
            response = servletRequestAttributes.getResponse();
        }
        return Optional.ofNullable(response);
    }
}
