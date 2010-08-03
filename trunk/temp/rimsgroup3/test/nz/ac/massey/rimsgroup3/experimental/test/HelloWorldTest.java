/**
 * 
 */
package nz.ac.massey.rimsgroup3.experimental.test;

import java.io.IOException;

import javax.servlet.ServletException;

import nz.ac.massey.rimsgroup3.experimental.HelloWorld;

import org.apache.cactus.ServletTestCase;
import org.apache.cactus.WebRequest;
import org.apache.cactus.WebResponse;

/**
 * @author ljbade
 *
 */
public class HelloWorldTest extends ServletTestCase {
	public HelloWorldTest(String name) {
		super(name);		

        System.setProperty("cactus.contextURL", "http://localhost:8080/rimsgroup3"); // TODO move to a properties file?
	}
	
	public void beginHelloWorld(WebRequest request) {
		request.setURL("localhost:8080", "/rimsgroup3", "/", null, null);
	}
	
	public void testHelloWorld() throws ServletException, IOException {
		HelloWorld servlet = new HelloWorld();
		servlet.init(config);
		
		servlet.doGet(request, response);
	}
	
	public void endHelloWorld(WebResponse response) {
		assertEquals("<html><head><title>Test Page</title><body><h1>Hello World!</h1></body></html>", response.getText());
	}

}
