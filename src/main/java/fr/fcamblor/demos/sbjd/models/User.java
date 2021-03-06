package fr.fcamblor.demos.sbjd.models;

import fr.fcamblor.demos.sbjd.stereotypes.ValidationMode;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    @NotNull @Size(min=1, message="{user.phoneNumbers.notEmpty}")
    List<String> phoneNumbers;
    @Valid
    List<Address> addresses;

    @AssertTrue(message="{user.isAdult}")
    @JsonIgnore // Useful to avoid errors thrown by jackson when it will try to serialize/deserialize instance
    public boolean isUserAnAdult(){
        if(this.birthDate == null){ // birthDate is nullable, we shouldn't fail if it is let empty !
            return true;
        }

        Calendar birthCalendar = new GregorianCalendar();
        birthCalendar.setTime(birthDate);
        birthCalendar.add(Calendar.YEAR, 18);
        return birthCalendar.before(new GregorianCalendar());
    }

    public User setId(Long _id){
        this.id = _id;
        return this;
    }

    public Long getId(){
        return this.id;
    }

    public User setCredentials(Credentials _credentials){
        this.credentials = _credentials;
        return this;
    }

    public Credentials getCredentials(){
        return this.credentials;
    }

    public User setFirstName(String _firstName){
        this.firstName = _firstName;
        return this;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public User setLastName(String _lastName){
        this.lastName = _lastName;
        return this;
    }

    public String getLastName(){
        return this.lastName;
    }

    public User setBirthDate(Date _birthDate){
        this.birthDate = _birthDate;
        return this;
    }

    public Date getBirthDate(){
        return this.birthDate;
    }

    public User setPhoneNumbers(List<String> _phoneNumbers){
        this.phoneNumbers = _phoneNumbers;
        return this;
    }

    public List<String> getPhoneNumbers(){
        return this.phoneNumbers;
    }

    public User setAddresses(List<Address> _addresses){
        this.addresses = _addresses;
        return this;
    }

    public List<Address> getAddresses(){
        return this.addresses;
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
