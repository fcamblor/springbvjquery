package fr.fcamblor.demos.sbjd.test.rules;

import com.jayway.restassured.RestAssured;
import fr.fcamblor.demos.sbjd.web.main.EmbeddedTomcat;
import org.junit.rules.ExternalResource;

/**
 * @author fcamblor
 * JUnit rule which will ensure tomcat embed server is started during the test
 * It will configure rest assured to connect to started tomcat port, too.
 */
public class RequiresRunningEmbeddedTomcat extends ExternalResource {

    static private Object writeTomcatLock = new Object();
    static protected EmbeddedTomcat tomcat = null;

    @Override
    protected void before() throws Throwable {
        super.before();

        synchronized (writeTomcatLock){
            // Starting tomcat embed only once per JVM, sharing static instance across rule instances
            if(tomcat == null){
                tomcat = new EmbeddedTomcat();
                tomcat.start();
                RestAssured.port = tomcat.getWebPort();
            }
        }
    }

    @Override
    protected void after() {
        /* We don't want to stop tomcat after test execution :
           Tomcat will shutdown when the JVM stops !
        if(tomcat != null){
            try {
                tomcat.stop();
            } catch (LifecycleException e) {
                throw new RuntimeException(e);
            }
        }
        */
        super.after();
    }
}
