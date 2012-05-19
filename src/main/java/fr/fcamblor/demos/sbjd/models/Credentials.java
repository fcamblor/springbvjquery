package fr.fcamblor.demos.sbjd.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author fcamblor
 */
public class Credentials {
    @NotNull @Size(min=1,max=25)
    String login;
    @Size(min=3)
    String password;
}
