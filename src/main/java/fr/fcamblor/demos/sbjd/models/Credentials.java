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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
