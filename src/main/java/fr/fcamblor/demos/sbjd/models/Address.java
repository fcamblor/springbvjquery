package fr.fcamblor.demos.sbjd.models;

import fr.fcamblor.demos.sbjd.stereotypes.PersistencyMode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author fcamblor
 */
public class Address {
    @NotNull(groups=PersistencyMode.Update.class)
    Long id;
    @NotNull @Size(min=5)
    String street1;
    @Size(min=5)
    String street2;
    @Pattern(regexp="[0-9]{5}")
    String postalCode;
    @NotNull @Size(min=1)
    String city;
}
