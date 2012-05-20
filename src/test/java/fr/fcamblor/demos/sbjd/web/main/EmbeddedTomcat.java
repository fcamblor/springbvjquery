package fr.fcamblor.demos.sbjd.web.main;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

/**
 * @author fcamblor
 */
public class EmbeddedTomcat {
    int webPort;
    Tomcat tomcat;

    public EmbeddedTomcat(){
        this(resolveDefaultWebPort());
    }

    public EmbeddedTomcat(int webPort){
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(webPort);
        this.webPort = webPort;

        String webappDirLocation = "src/main/webapp/";
        try {
            tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        } catch (ServletException e) {
            throw new RuntimeException("Failed adding webapp to embedded tomcat", e);
        }
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        this.tomcat = tomcat;
    }

    public void start() throws LifecycleException {
        tomcat.start();
    }

    public void startAndAwait() throws LifecycleException {
        this.start();
        tomcat.getServer().await();
    }

    public void stop() throws LifecycleException {
        tomcat.stop();
    }

    public int getWebPort() {
        return webPort;
    }

    private static int resolveDefaultWebPort() {
        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPortStr = System.getenv("web.port");
        if(webPortStr == null || webPortStr.isEmpty()) {
            return 8080;
        } else {
            return Integer.valueOf(webPortStr);
        }
    }
}
