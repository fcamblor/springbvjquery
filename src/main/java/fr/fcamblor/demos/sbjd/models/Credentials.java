package fr.fcamblor.demos.sbjd.models;

import fr.fcamblor.demos.sbjd.stereotypes.ValidationMode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author fcamblor
 */
public class Credentials {
    @NotNull @Size(min=1,max=25)
    String login;
    @Size(min=3)
    // Password should only be mandatory during create/authent (not in update)
    @NotNull(groups={ValidationMode.Authent.class, ValidationMode.Create.class})
    String password;

    public Credentials setLogin(String _login){
        this.login = _login;
        return this;
    }

    public String getLogin(){
        return this.login;
    }

    public Credentials setPassword(String _password){
        this.password = _password;
        return this;
    }

    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credentials)) return false;

        Credentials that = (Credentials) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }
}
