package fr.fcamblor.demos.sbjd.web;

import fr.fcamblor.demos.sbjd.models.Credentials;
import fr.fcamblor.demos.sbjd.test.rules.RequiresRunningEmbeddedTomcat;
import org.junit.Rule;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * @author fcamblor
 */
public class AuthenticationControllerTest {

    private static final int VALIDATION_ERROR_HTTP_STATUS_CODE = 500;
    private static final int VALIDATION_OK_HTTP_STATUS_CODE = 200;

    @Rule
    // Ensuring a tomcat server will be up and running during test execution !
    public RequiresRunningEmbeddedTomcat tomcat = new RequiresRunningEmbeddedTomcat();

    @Test
    public void nullCredentialsShouldntBeAccepted(){
        given().
                /* Not setting any query parameters should result in an empty Credential object
                param("login", "").
                param("password", "").
                */
        expect().
                statusCode(VALIDATION_ERROR_HTTP_STATUS_CODE).
        when().
                post("/auth/authenticate");
    }

    @Test
    public void filledCredentialsShouldReturnOk(){
        given().
                param("login", "foo").
                param("password", "bar").
        expect().
                statusCode(VALIDATION_OK_HTTP_STATUS_CODE).
        when().
                post("/auth/authenticate");
    }
}
