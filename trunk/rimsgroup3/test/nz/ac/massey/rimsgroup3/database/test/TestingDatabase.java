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
import org.eclipse.jdt.internal.compiler.batch.FileSystem.Classpath;

import junit.framework.*;

import nz.ac.massey.rimsgroup3.database.*;
import nz.ac.massey.rimsgroup3.metadata.bean.*;
import nz.ac.massey.rimsgroup3.runner.ScriptRunner;


public class TestingDatabase extends ServletTestCase{
	DataSource dataSource;
	
	public TestingDatabase(String name)
	{
		super(name);
		System.setProperty("cactus.contextURL", "http://localhost:8080/rimsgroup3");
		
	}
	
	
	public void setUp() throws SQLException, ServletException, IOException{
		String db = "test";
    	DatabaseConnection datasource = new DatabaseConnection();
    	this.dataSource =  datasource.setUp(db);
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
		config.setInitParameter("test", "testDB");
		String doi = "doi1";
		
		HttpSession session = request.getSession(true);  
	    session.setAttribute("publicationDOI", doi);
		
	    
		DatabaseSearchDOI dbSearchDOI = new DatabaseSearchDOI();
		dbSearchDOI.init(config);
		dbSearchDOI.doGet(request, response);
	    
		Boolean checkInDB = (Boolean) session.getAttribute("boolean");
		assertFalse(checkInDB);
		
	    /*DatabaseRead dbread = new DatabaseRead();
		dbread.init(config);
		dbread.doGet(request,response);
	
		Information checkInformation = new Information();
		checkInformation = (Information) session.getAttribute("information");*/


	}
	/*
	public void testInsert() throws SQLException, ServletException, IOException {
		config.setInitParameter("test", "testDB");
		String doi = "doi1";
		List <Author> authorsInserted = new ArrayList<Author>();
		
	    Author author = new Author();
		author.setCollege("College");
		author.setDepartment("Department");
		author.setFirstName("Princess");
		author.setID("1");
		author.setLastName("Leia");
		author.setMiddleName("mid");
		author.setType("type");
		author.setUniversity("Massey");
		 
		Author author2 = new Author();
		author2.setFirstName("Hans");
		author2.setLastName("Solo");
		author2.setMiddleName("middle");
		author2.setUniversity("Dublin");
		
		authorsInserted.add(author);
		authorsInserted.add(author2);
	    
		HttpSession session = request.getSession(true);  
	    session.setAttribute("publicationDOI", doi);
	    session.setAttribute("publicationAuthors", authorsInserted);
	    DatabaseInsert dbInsert = new DatabaseInsert();
	    dbInsert.init(config);
	    dbInsert.doGet(request, response);
		session.removeAttribute("publicationAuthors");
	    
		DatabaseSearchDOI dbSearchDOI = new DatabaseSearchDOI();
		dbSearchDOI.init(config);
		dbSearchDOI.doGet(request, response);
		
		Boolean checkInDB = (Boolean) session.getAttribute("boolean");
		assertTrue(checkInDB);
		
		List <Author> authorsChecked = new ArrayList<Author>();
		Author authorCheck1 = new Author();
		authorCheck1.setFirstName(author.getFirstName());
		authorCheck1.setLastName(author.getLastName());
		authorCheck1.setMiddleName(author.getMiddleName());
		Author authorCheck2 = new Author();
		authorCheck2.setFirstName(author2.getFirstName());
		authorCheck2.setLastName(author2.getLastName());
		authorCheck2.setMiddleName(author.getMiddleName());
		authorsChecked.add(authorCheck1);
		authorsChecked.add(authorCheck2);
		
		DatabaseSearchAuthors dbSearchAuthor = new DatabaseSearchAuthors();
		session.setAttribute("publicationAuthors", authorsChecked);
		dbSearchAuthor.init(config);
		dbSearchAuthor.doGet(request, response);
		//session.removeAttribute("publicationAuthors");
	    
		List <Author> authorsReturned = new ArrayList<Author>();
		authorsReturned = (List<Author>) session.getAttribute("publicationAuthors");
		int i = 0;
		assertEquals(authorsInserted.size(),authorsReturned.size());
		while (authorsReturned.size() != i)
		{
			Author authorRetrieved = authorsReturned.get(i);
			Author authorCompare = authorsInserted.get(i);
			authorComparison(authorRetrieved,authorCompare);
			i++;
		}
		//authorsReturned

	}
	*/
	
	private void authorComparison(Author authorRetrieved, Author authorCompare){
		assertEquals(authorCompare.getFirstName(),authorRetrieved.getFirstName());
		assertEquals(authorCompare.getLastName(),authorRetrieved.getLastName());
	}
	

	
}
