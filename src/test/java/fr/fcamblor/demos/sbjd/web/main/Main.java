package fr.fcamblor.demos.sbjd.web.main;

import org.apache.catalina.startup.Bootstrap;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * @author fcamblor
 */
public class Main {
	public static void main(String[] args) throws Exception {
        new EmbeddedTomcat().start();
	}
}
