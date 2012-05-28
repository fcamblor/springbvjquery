package fr.fcamblor.demos.sbjd.web.auth;

import fr.fcamblor.demos.sbjd.models.Credentials;
import fr.fcamblor.demos.sbjd.stereotypes.ValidationMode;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.groups.Default;

/**
 * @author fcamblor
 */
@Controller
public class AuthenticationController {

    @RequestMapping(value="auth/authenticate", method= RequestMethod.POST)
    public @ResponseBody Object authenticate(@Validated({ Default.class, ValidationMode.Authent.class}) Credentials credentials){
        // Do some stuff here to verify given credentials
        // You won't reach this point if either credentials.login or credentials.password field is let empty !

        // Returning something, otherwise Spring will think we want to forward on
        // /auth/authenticate.jsp view whereas we are in a REST method where we would like to return
        // JSON objects, for example, successfully authenticated User object
        return null;
    }
}
