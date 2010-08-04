/**
 * 
 */
package nz.ac.massey.rimsgroup3.experimental.test;

import java.io.IOException;

import javax.servlet.ServletException;

import nz.ac.massey.rimsgroup3.experimental.HelloWorldServlet;
import nz.ac.massey.rimsgroup3.experimental.HelloWorldServletWithJSP;

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
	
	public void beginHelloWorldServlet(WebRequest request) {
		request.setURL("localhost:8080", "/rimsgroup3", "/HelloWorldServlet", null, null);
	}
	
	public void testHelloWorldServlet() throws ServletException, IOException {
		HelloWorldServlet servlet = new HelloWorldServlet();
		servlet.init(config);
		
		servlet.doGet(request, response);
	}
	
	public void endHelloWorldServlet(WebResponse response) {
		assertEquals("<html><head><title>Test Page</title><body><h1>Hello World!</h1></body></html>", response.getText());
	}
	
	public void beginHelloWorldServletWithJSP(WebRequest request) {
		request.setURL("localhost:8080", "/rimsgroup3", "/HelloWorldServletWithJSP", null, null);
	}
	
	public void testHelloWorldServletWithJSP() throws ServletException, IOException {
		HelloWorldServletWithJSP servlet = new HelloWorldServletWithJSP();
		servlet.init(config);
		
		servlet.doGet(request, response);
	}
	
	public void endHelloWorldServletWithJSP(WebResponse response) {
		assertEquals("<html><head><title>Test Page</title><body><h1>Hello World! (from JSP)</h1></body></html>", response.getText());
	}
}
