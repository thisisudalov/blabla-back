package org.example.blabla.util;

import lombok.experimental.UtilityClass;
import org.example.blabla.domain.pojo.UserPojo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@UtilityClass
public class AuthUtil {

    private static final String USER_ATTR = "auth";

    public void saveUserToContext(UserPojo user) {
        getContextAttributes().setAttribute(USER_ATTR, user);
    }

    public UserPojo getUserFromContext() {
        return (UserPojo) getContextAttributes().getAttribute(USER_ATTR);
    }

    private jakarta.servlet.http.HttpServletRequest getContextAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
    }

}
