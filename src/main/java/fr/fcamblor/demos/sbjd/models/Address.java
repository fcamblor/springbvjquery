package fr.fcamblor.demos.sbjd.models;

import fr.fcamblor.demos.sbjd.stereotypes.ValidationMode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author fcamblor
 */
public class Address {
    @NotNull(groups=ValidationMode.Update.class)
    Long id;
    @NotNull @Size(min=5)
    String street1;
    @Size(min=5)
    String street2;
    @Pattern(regexp="[0-9]{5}")
    String postalCode;
    @NotNull @Size(min=1)
    String city;

    public Address setId(Long _id){
        this.id = _id;
        return this;
    }

    public Long getId(){
        return this.id;
    }

    public Address setStreet1(String _street1){
        this.street1 = _street1;
        return this;
    }

    public String getStreet1(){
        return this.street1;
    }

    public Address setStreet2(String _street2){
        this.street2 = _street2;
        return this;
    }

    public String getStreet2(){
        return this.street2;
    }

    public Address setPostalCode(String _postalCode){
        this.postalCode = _postalCode;
        return this;
    }

    public String getPostalCode(){
        return this.postalCode;
    }

    public Address setCity(String _city){
        this.city = _city;
        return this;
    }

    public String getCity(){
        return this.city;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (postalCode != null ? !postalCode.equals(address.postalCode) : address.postalCode != null) return false;
        if (street1 != null ? !street1.equals(address.street1) : address.street1 != null) return false;
        if (street2 != null ? !street2.equals(address.street2) : address.street2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = street1 != null ? street1.hashCode() : 0;
        result = 31 * result + (street2 != null ? street2.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
