package fr.fcamblor.demos.sbjd.models;

import fr.fcamblor.demos.sbjd.stereotypes.ValidationMode;

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
    @NotNull(groups=ValidationMode.Update.class)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (credentials != null ? !credentials.equals(user.credentials) : user.credentials != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return credentials != null ? credentials.hashCode() : 0;
    }
}
