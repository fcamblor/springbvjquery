package fr.fcamblor.demos.sbjd.web.registration;

import fr.fcamblor.demos.sbjd.models.User;
import fr.fcamblor.demos.sbjd.stereotypes.ValidationMode;
import fr.fcamblor.demos.sbjd.web.holders.UserHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fcamblor
 */
@Controller
public class RegistrationController {

    @RequestMapping("/")
    public String welcomeView(){
        return "welcome"; // Will forward to welcome.jsp file
    }

    @RequestMapping(value="/users/registered", method=RequestMethod.POST)
    public @ResponseBody User registerUser(@RequestBody @Validated(ValidationMode.Create.class) User user){
        UserHolder.store(user); // Storing user in session
        return user;
    }

    @RequestMapping(value="/users/registered", method=RequestMethod.PUT)
    public @ResponseBody User updateRegisteredUser(@RequestBody @Validated(ValidationMode.Update.class) User user){
        if(UserHolder.loggedUser() == null){
            throw new IllegalStateException("No user registered for the moment ...");
        }
        UserHolder.store(user); // Updating stored user infos
        return user;
    }

    @RequestMapping(value="/users/registered", method=RequestMethod.GET)
    public @ResponseBody User registeredUser(){
        return UserHolder.loggedUser(); // Retrieving stored user infos
    }
}
