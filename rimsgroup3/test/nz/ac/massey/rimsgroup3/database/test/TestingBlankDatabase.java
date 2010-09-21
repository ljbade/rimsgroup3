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


public class TestingBlankDatabase extends ServletTestCase{
	DataSource dataSource;
	private String dbSelection;
	public TestingBlankDatabase(String name)
	{
		super(name);
		System.setProperty("cactus.contextURL", "http://localhost:8080/rimsgroup3");
		
	}
	
	
	public void setUp() throws SQLException, ServletException, IOException{
		this.dbSelection = "swctest";
    	DatabaseConnection datasource = new DatabaseConnection();
    	this.dataSource =  datasource.setUp(this.dbSelection);
    	Connection connection = null;
    	
    	synchronized (dataSource)
		{
			connection = dataSource.getConnection();
		}
    	ScriptRunner runner = new ScriptRunner(connection ,false, true);
    	runner.runScript(new BufferedReader(new FileReader("C:/Users/Peter/Documents/University/Software C/Createtables.sql")));
    	
	}
	
	public void tearDown(){

	}
	
	public void testNoPublication() throws SQLException, ServletException, IOException {
		config.setInitParameter("test", this.dbSelection);
		String doi = "doi1";
		
		HttpSession session = request.getSession(true);  
	    session.setAttribute("publicationDOI", doi);
		
	    
		DatabaseSearchDOI dbSearchDOI = new DatabaseSearchDOI();
		dbSearchDOI.init(config);
		dbSearchDOI.doGet(request, response);
	    
		Boolean checkInDB = (Boolean) session.getAttribute("boolean");
		assertFalse(checkInDB);


	}

	public void testInsert() throws SQLException, ServletException, IOException {
		config.setInitParameter("test", this.dbSelection);
		String doi = "doi1";
		List <Author> authorsInserted = new ArrayList<Author>();
		
	    Author authorMass1 = new Author();
		authorMass1.setCollege("College");
		authorMass1.setDepartment("Department");
		authorMass1.setFirstName("Princess");
		authorMass1.setID("01");
		authorMass1.setLastName("Leia");
		authorMass1.setMiddleName("mid");
		authorMass1.setType("type");
		authorMass1.setUniversity("Massey");
		 
		Author authorMisc1 = new Author();
		authorMisc1.setID("2");
		authorMisc1.setFirstName("Hans");
		authorMisc1.setLastName("Solo");
		authorMisc1.setMiddleName("middle");
		authorMisc1.setUniversity("Dublin");
		
		authorsInserted.add(authorMass1);
		authorsInserted.add(authorMisc1);
		
		List <Author> authorsExpected = new ArrayList<Author>();
		authorsExpected = authorExpected();
	    
		HttpSession session = request.getSession(true);  
	    session.setAttribute("publicationDOI", doi);
	    session.setAttribute("publicationAuthors", authorsInserted);
	    DatabaseInsert dbInsert = new DatabaseInsert();
	    dbInsert.init(config);
	    dbInsert.doGet(request, response);
		
	    
		DatabaseSearchDOI dbSearchDOI = new DatabaseSearchDOI();
		dbSearchDOI.init(config);
		dbSearchDOI.doGet(request, response);
		
		Boolean checkInDB = (Boolean) session.getAttribute("boolean");
		assertTrue(checkInDB);
		
		List <Author> authorsChecked = new ArrayList<Author>();
		Author authorCheck1 = new Author();
		authorCheck1.setFirstName(authorMass1.getFirstName());
		authorCheck1.setLastName(authorMass1.getLastName());
		authorCheck1.setMiddleName(authorMass1.getMiddleName());
		Author authorCheck2 = new Author();
		authorCheck2.setFirstName(authorMisc1.getFirstName());
		authorCheck2.setLastName(authorMisc1.getLastName());
		authorCheck2.setMiddleName(authorMisc1.getMiddleName());
		authorsChecked.add(authorCheck1);
		authorsChecked.add(authorCheck2);
		
		DatabaseSearchAuthors dbSearchAuthor = new DatabaseSearchAuthors();
		session.setAttribute("publicationAuthors", authorsChecked);
		dbSearchAuthor.init(config);
		dbSearchAuthor.doGet(request, response);
	    
		List <Author> authorsReturned = new ArrayList<Author>();
		authorsReturned = (List<Author>) session.getAttribute("publicationAuthors");
		int i = 0;
		assertEquals(authorsExpected.size(),authorsReturned.size());
		while (authorsReturned.size() != i)
		{
			Author authorRetrieved = authorsReturned.get(i);
			Author authorCompare = authorsExpected.get(i);
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
	
	private List<Author> authorExpected(){
		List <Author> authorsExpected = new ArrayList<Author>();
		
	    Author massExp1 = new Author();
		massExp1.setCollege("College");
		massExp1.setDepartment("Department");
		massExp1.setFirstName("Princess");
		massExp1.setID("01");
		massExp1.setLastName("Leia");
		massExp1.setMiddleName("mid");
		massExp1.setType("type");
		massExp1.setUniversity("Massey");
		 
		Author miscExp1 = new Author();
		miscExp1.setID("2");
		miscExp1.setFirstName("Hans");
		miscExp1.setLastName("Solo");
		miscExp1.setMiddleName("middle");
		miscExp1.setUniversity("Dublin");
		
		authorsExpected.add(massExp1);
		authorsExpected.add(miscExp1);
		return authorsExpected;
	}
	
}
