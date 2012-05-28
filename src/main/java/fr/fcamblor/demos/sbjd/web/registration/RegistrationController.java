package fr.fcamblor.demos.sbjd.web.registration;

import fr.fcamblor.demos.sbjd.models.User;
import fr.fcamblor.demos.sbjd.stereotypes.ValidationMode;
import fr.fcamblor.demos.sbjd.web.holders.UserHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RegistrationController {

    Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    @RequestMapping("/")
    public String welcomeView(){
        return "welcome"; // Will forward to welcome.jsp file
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public @ResponseBody User registerUser(@RequestBody @Validated(value={ Default.class, ValidationMode.Create.class }) User user){
        UserHolder.store(user); // Storing user in session
        return user;
    }

    @RequestMapping(value="/users", method=RequestMethod.PUT)
    public @ResponseBody User updateRegisteredUser(@RequestBody @Validated({ Default.class, ValidationMode.Update.class }) User user){
        UserHolder.update(user); // Updating stored user infos
        return user;
    }

    @RequestMapping(value="/users/registered", method=RequestMethod.GET)
    public @ResponseBody User registeredUser(){
        return UserHolder.loggedUser(); // Retrieving stored user infos
    }
}
