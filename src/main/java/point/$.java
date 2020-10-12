package point;

import com.github.iceant.point.web.core.Util;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class $ {

    public static String prop(String name){
        return Util.prop(name);
    }

    public static String prop(String name, String defaultValue){
        return Util.prop(name, defaultValue);
    }

    public static String msg(String name, Object ... args){
        return Util.getMessage(name, args);
    }

    public static HttpServletRequest request() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    public static HttpServletResponse response() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getResponse();
        }
        return null;
    }

    public static boolean hasRole(String name){
        HttpServletRequest request = request();
        if(request==null) return false;
        return request.isUserInRole(name);
    }

    public static boolean hasAnyRole(String ... roles){
        HttpServletRequest request = request();
        if(request==null) return false;
        for(String role : roles){
            if(request.isUserInRole(role)){
                return true;
            }
        }
        return false;
    }
}
