package nz.ac.massey.rimsgroup3.servlet.test;


import java.io.IOException;

import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import org.apache.cactus.*;  


public class AutoCompleteTest extends ServletTestCase  {

	
	public AutoCompleteTest (String name){
		super(name);
		System.setProperty("cactus.contextURL", "http://localhost:8080/rimsgroup3");
	}
	
	public void testSAutoComplete() throws ServletException, IOException{
		String input = "sp";	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AutoComplete?id=" + input); 
		dispatcher.forward(request, response); 
	}
	
	public void endSAutoComplete(WebResponse response) {
		String output = response.getText();
		assertEquals("<publishers><publisher><name>SpringerLink</name></publisher></publishers>",output);
	}
	
	
	public void testIAutoComplete() throws ServletException, IOException{
		String input = "i";	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AutoComplete?id=" + input); 
		dispatcher.forward(request, response); 
	}
	
	public void endIAutoComplete(WebResponse response) {
		String output = response.getText();
		assertEquals("<publishers><publisher><name>IEEE</name></publisher><publisher><name>IWA Publishing</name></publisher></publishers>",output);
	}
	
	public void testNoAutoComplete() throws ServletException, IOException{
		String input = "P";	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AutoComplete?id=" + input); 
		dispatcher.forward(request, response); 
	}
	
	public void endNoAutoComplete(WebResponse response) {
		String output = response.getText();
		assertEquals("",output);
	}
	
	public void testEmptyAutoComplete() throws ServletException, IOException{
		String input = "";	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AutoComplete?id=" + input); 
		dispatcher.forward(request, response); 
	}
	
	public void endEmptyAutoComplete(WebResponse response) {
		String output = response.getText();
		assertEquals("",output);
	}
}
