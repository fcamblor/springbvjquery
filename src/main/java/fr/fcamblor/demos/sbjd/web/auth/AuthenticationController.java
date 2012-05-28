package fr.fcamblor.demos.sbjd.web.auth;

import fr.fcamblor.demos.sbjd.models.Credentials;
import fr.fcamblor.demos.sbjd.stereotypes.ValidationMode;
import fr.fcamblor.demos.sbjd.web.holders.UserHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.groups.Default;

/**
 * @author fcamblor
 */
@Controller
public class AuthenticationController {

    // Please avoid this authentication url since password will be clearly
    // visible in your url
    // It is only an example to demonstrate that spring validation will throw
    // a BindException when validating query parameters, whereas it will throw
    // a MethodArgumentNotValidException when validating @RequestBody params.
    @RequestMapping(value="auth/authenticateQuery", method= RequestMethod.POST)
    public @ResponseBody Object authenticateInQuery(@Validated({ Default.class, ValidationMode.Authent.class}) Credentials credentials){
        // Do some stuff here to verify given credentials
        // You won't reach this point if either credentials.login or credentials.password field is let empty !

        return authenticate(credentials);
    }

    @RequestMapping(value="auth/authenticate", method= RequestMethod.POST)
    public @ResponseBody Object authenticate(@RequestBody @Validated({ Default.class, ValidationMode.Authent.class}) Credentials credentials){
        // Do some stuff here to verify given credentials
        // You won't reach this point if either credentials.login or credentials.password field is let empty !

        UserHolder.login(credentials);
        // Returning something, otherwise Spring will think we want to forward on
        // /auth/authenticate.jsp view whereas we are in a REST method where we would like to return
        // JSON objects, for example, successfully authenticated User object
        return null;
    }
}
