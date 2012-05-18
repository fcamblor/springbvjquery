package fr.fcamblor.demos.sbjd.web.registration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fcamblor
 */
@Controller
public class RegistrationController {

    @RequestMapping("/")
    public String welcomeView(){
        return "welcome";
    }
}
