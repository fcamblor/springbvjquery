package fr.fcamblor.demos.sbjd.models;

import fr.fcamblor.demos.sbjd.stereotypes.PersistencyMode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author fcamblor
 */
public class User {
    @NotNull(groups=PersistencyMode.Update.class)
    Long id;
    @NotNull @Valid
    Credentials credentials;
    @NotNull @Size(min=1,max=20)
    String firstName;
    @NotNull @Size(min=1,max=20)
    String lastName;
    @Past
    Date birthDate;
    @NotNull @Size(min=1)
    List<String> phoneNumbers;
    @Valid
    List<Address> addresses;
}
