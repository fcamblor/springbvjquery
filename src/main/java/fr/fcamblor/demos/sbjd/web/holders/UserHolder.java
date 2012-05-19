package fr.fcamblor.demos.sbjd.web.holders;

import fr.fcamblor.demos.sbjd.models.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author fcamblor
 */
public class UserHolder {

    private static long userIncrement = 0;

    public static User user(){
        return (User) RequestContextHolder.currentRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
    }

    public static void store(User user){
        long userId = userIncrement++;
        RequestContextHolder.currentRequestAttributes().setAttribute("user", user, RequestAttributes.SCOPE_SESSION);
    }
}
