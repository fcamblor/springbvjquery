package fr.fcamblor.demos.sbjd.web;

import fr.fcamblor.demos.sbjd.models.Address;
import fr.fcamblor.demos.sbjd.models.Credentials;
import fr.fcamblor.demos.sbjd.models.User;
import fr.fcamblor.demos.sbjd.test.rules.RequiresDefaultRestAssuredConfiguration;
import fr.fcamblor.demos.sbjd.test.rules.RequiresRunningEmbeddedTomcat;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;

/**
 * @author fcamblor
 */
public class RegistrationControllerTest {

    private static final int VALIDATION_ERROR_HTTP_STATUS_CODE = HttpStatus.PRECONDITION_FAILED.value();
    private static final int VALIDATION_OK_HTTP_STATUS_CODE = HttpStatus.OK.value();

    @Rule
    // Ensuring a tomcat server will be up and running during test execution !
    public RequiresRunningEmbeddedTomcat tomcat = new RequiresRunningEmbeddedTomcat();

    @Rule
    public RequiresDefaultRestAssuredConfiguration raDefaultConfig = new RequiresDefaultRestAssuredConfiguration();

    @Test
    public void wellFormedUserShouldBeAcceptedInCreation(){
        expectCreateUserStatus(createWellFormedUserForCreation(), VALIDATION_OK_HTTP_STATUS_CODE);
    }

    @Test
    public void nullFirstNameShouldntBeAcceptedInCreation(){
        expectCreateUserStatus(createWellFormedUserForCreation().setFirstName(null), VALIDATION_ERROR_HTTP_STATUS_CODE);
        expectCreateUserStatus(createWellFormedUserForCreation().setFirstName("blah"), VALIDATION_OK_HTTP_STATUS_CODE);
    }

    @Test
    public void nullIdShouldntBeAcceptedInUpdate(){
        // Creating a user to be able to edit it laterly...
        User existingUser =  createUser(createWellFormedUserForCreation());
        // ... everything should be ok from now..

        // Editing non existing user (null id) should throw a validation error
        expectUpdateUserStatus(createWellFormedUserForCreation(), VALIDATION_ERROR_HTTP_STATUS_CODE);
        // Editing existing user should be ok
        expectUpdateUserStatus(createWellFormedUserForCreation().setId(existingUser.getId()), VALIDATION_OK_HTTP_STATUS_CODE);
    }

    protected User createUser(User userToCreate){
        return
        given().
                contentType("application/json").
                body(userToCreate).
        expect().
                statusCode(HttpStatus.OK.value()).
        when().
                post("/users").
                as(User.class);
    }

    protected void expectCreateUserStatus(User userToCreate, int expectedStatusCode){
        given().
                contentType("application/json").
                body(userToCreate).
        expect().
                statusCode(expectedStatusCode).
        when().
                post("/users");
    }

    protected void expectUpdateUserStatus(User userToCreate, int expectedStatusCode){
        given().
                contentType("application/json").
                body(userToCreate).
        expect().
                statusCode(expectedStatusCode).
        when().
                put("/users");
    }

    protected User expectGetUserStatus(int expectedStatusCode){
        return
        expect().
                statusCode(expectedStatusCode).
        when().
                post("/users/registered").as(User.class);
    }

    protected static Address createWellFormedAddress(){
        return new Address().
                setStreet1("37 rue Jean Moulin").
                setPostalCode("33140").
                setCity("Villenave d'Ornon");
    }

    protected static Credentials createWellFormedCredentials(){
        return new Credentials().setLogin("foo@bar.com").setPassword("bar");
    }

    protected static User createWellFormedUserForCreation(){
        Date birthDate = new Date();
        try { birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("29/06/1983"); }
        catch (ParseException e) {}
        return new User().
                setFirstName("Frédéric").
                setLastName("Camblor").
                setBirthDate(birthDate).
                setPhoneNumbers(Arrays.asList("0123456789")).
                setCredentials(createWellFormedCredentials()).
                setAddresses(Collections.<Address>emptyList());
    }
}
