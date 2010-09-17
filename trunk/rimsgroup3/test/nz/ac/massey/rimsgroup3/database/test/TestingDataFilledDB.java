package nz.ac.massey.rimsgroup3.database.test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.cactus.*;
import org.apache.catalina.Server;
import org.eclipse.jdt.internal.compiler.batch.FileSystem.Classpath;

import junit.framework.*;

import nz.ac.massey.rimsgroup3.database.*;
import nz.ac.massey.rimsgroup3.metadata.bean.*;
import nz.ac.massey.rimsgroup3.runner.ScriptRunner;

public class TestingDataFilledDB extends ServletTestCase{
DataSource dataSource;
private String dbSelection;
	
	public TestingDataFilledDB(String name)
	{
		super(name);
		System.setProperty("cactus.contextURL", "http://localhost:8080/rimsgroup3");
		
	}
	
	
	public void setUp() throws SQLException, ServletException, IOException{
		this.dbSelection = "datatest";
    	DatabaseConnection datasource = new DatabaseConnection();
    	this.dataSource =  datasource.setUp(this.dbSelection);
    	Connection connection = null;
    	synchronized (dataSource)
		{
			connection = dataSource.getConnection();
		}
    	ScriptRunner runner = new ScriptRunner(connection ,false, true);
    	runner.runScript(new BufferedReader(new FileReader("C:/Users/Peter/Documents/University/Software C/Createtables.sql")));
    	runner.runScript(new BufferedReader(new FileReader("C:/Users/Peter/Documents/University/Software C/testCode.sql")));
	}
	
	public void tearDown(){

	}
	
	public void testNoPublicationFound() throws SQLException, ServletException, IOException {
		config.setInitParameter("test", this.dbSelection);
		String doi = "10.1016/j.tcs.2010.07.0078";
		
		HttpSession session = request.getSession(true);  
	    session.setAttribute("publicationDOI", doi);
		
	    
		DatabaseSearchDOI dbSearchDOI = new DatabaseSearchDOI();
		dbSearchDOI.init(config);
		dbSearchDOI.doGet(request, response);
	    
		Boolean checkInDB = (Boolean) session.getAttribute("boolean");
		assertFalse(checkInDB);


	}
	
	public void testPublicationFound() throws SQLException, ServletException, IOException {
		config.setInitParameter("test", this.dbSelection);
		String doi = "10.1016/j.tcs.2010.07.007";
		
		HttpSession session = request.getSession(true);  
	    session.setAttribute("publicationDOI", doi);
		
	    
		DatabaseSearchDOI dbSearchDOI = new DatabaseSearchDOI();
		dbSearchDOI.init(config);
		dbSearchDOI.doGet(request, response);
	    
		Boolean checkInDB = (Boolean) session.getAttribute("boolean");
		assertTrue(checkInDB);
	}
	
	public void testNoAuthorsFound() throws SQLException, ServletException, IOException{
		config.setInitParameter("test", this.dbSelection);
		
		List <Author> authorsChecked = new ArrayList<Author>();
		Author authorCheck1 = new Author();
		authorCheck1.setFirstName("g");
		authorCheck1.setLastName("Bobbison");
		//authorCheck1.setMiddleName("");
		Author authorCheck2 = new Author();
		authorCheck2.setFirstName("a");
		authorCheck2.setLastName("Mathews");
		authorCheck2.setMiddleName("c");
		Author authorCheck3 = new Author();
		authorCheck3.setFirstName("Squall");
		authorCheck3.setLastName("Anderson");
		authorCheck3.setMiddleName("D");
		Author authorCheck4 = new Author();
		authorCheck4.setFirstName("Cam");
		authorCheck4.setLastName("Mccaw");
		//authorCheck4.setMiddleName("");
		authorsChecked.add(authorCheck1);
		authorsChecked.add(authorCheck2);
		authorsChecked.add(authorCheck3);
		authorsChecked.add(authorCheck4);
		
		DatabaseSearchAuthors dbSearchAuthor = new DatabaseSearchAuthors();
		session.setAttribute("publicationAuthors", authorsChecked);
		dbSearchAuthor.init(config);
		dbSearchAuthor.doGet(request, response);
	    
		List <Author> authorsReturned = new ArrayList<Author>();
		authorsReturned = (List<Author>) session.getAttribute("publicationAuthors");
		int i = 0;
		assertEquals(authorsChecked.size(),authorsReturned.size());
		while (authorsReturned.size() != i)
		{
			Author authorRetrieved = authorsReturned.get(i);
			assertFalse(authorRetrieved.getInDatabase());
			Author authorCompare = authorsChecked.get(i);
			authorComparison(authorRetrieved,authorCompare);
			i++;
		}
		
	}
	
	public void testAuthorsFound() throws SQLException, ServletException, IOException{
		config.setInitParameter("test", this.dbSelection);
		
		List <Author> authorsChecked = new ArrayList<Author>();
		Author authorCheck1 = new Author();
		authorCheck1.setFirstName("Eskimo");
		authorCheck1.setLastName("Bobbison");
		//authorCheck1.setMiddleName("");
		Author authorCheck2 = new Author();
		authorCheck2.setFirstName("a");
		authorCheck2.setLastName("Mathews");
		authorCheck2.setMiddleName("c");
		Author authorCheck3 = new Author();
		authorCheck3.setFirstName("Squall");
		authorCheck3.setLastName("Anderson");
		authorCheck3.setMiddleName("D");
		Author authorCheck4 = new Author();
		authorCheck4.setFirstName("Cam");
		authorCheck4.setLastName("Mccaw");
		//authorCheck4.setMiddleName("");
		authorsChecked.add(authorCheck1);
		authorsChecked.add(authorCheck2);
		authorsChecked.add(authorCheck3);
		authorsChecked.add(authorCheck4);
		
		DatabaseSearchAuthors dbSearchAuthor = new DatabaseSearchAuthors();
		session.setAttribute("publicationAuthors", authorsChecked);
		dbSearchAuthor.init(config);
		dbSearchAuthor.doGet(request, response);
	    
		List <Author> authorsReturned = new ArrayList<Author>();
		authorsReturned = (List<Author>) session.getAttribute("publicationAuthors");
		int i = 0;
		assertEquals(authorsChecked.size(),authorsReturned.size());
		while (authorsReturned.size() != i)
		{
			Author authorRetrieved = authorsReturned.get(i);
			assertFalse(authorRetrieved.getInDatabase());
			Author authorCompare = authorsChecked.get(i);
			authorComparison(authorRetrieved,authorCompare);
			i++;
		}
		
	}
	
	
	private void authorComparison(Author authorRetrieved, Author authorCompare){
		assertEquals(authorCompare.getFirstName(),authorRetrieved.getFirstName());
		assertEquals(authorCompare.getLastName(),authorRetrieved.getLastName());
		assertEquals(authorCompare.getMiddleName(),authorRetrieved.getMiddleName());
		assertEquals(authorCompare.getID(),authorRetrieved.getID());
		assertEquals(authorCompare.getType(),authorRetrieved.getType());
		assertEquals(authorCompare.getCollege(),authorRetrieved.getCollege());
		assertEquals(authorCompare.getUniversity(),authorRetrieved.getUniversity());
		assertEquals(authorCompare.getDepartment(),authorRetrieved.getDepartment());
	}
	

	
}

