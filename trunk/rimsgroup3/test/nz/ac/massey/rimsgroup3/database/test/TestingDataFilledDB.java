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
    	runner.runScript(new BufferedReader(new FileReader("/scripts/create-tables.sql")));
    	runner.runScript(new BufferedReader(new FileReader("/scripts/testCode.sql")));
	}
	
	public void tearDown(){

	}
	
	public void testNoPublicationFound() throws SQLException, ServletException, IOException {
		config.setInitParameter("test", this.dbSelection);
		String doi = "10.1016/j.tcs.2010.07.0078";
		
		HttpSession session = request.getSession(true);  
	    session.setAttribute("publicationDOI", doi);
		
	    
//		DatabaseSearchDOI dbSearchDOI = new DatabaseSearchDOI();
//		dbSearchDOI.init(config);
//		dbSearchDOI.doGet(request, response);
	    
		Boolean checkInDB = (Boolean) session.getAttribute("boolean");
		assertFalse(checkInDB);


	}
	
	public void testPublicationFound() throws SQLException, ServletException, IOException {
		config.setInitParameter("test", this.dbSelection);
		String doi = "10.1016/j.tcs.2010.07.007";
		
		HttpSession session = request.getSession(true);  
	    session.setAttribute("publicationDOI", doi);
		
	    
//		DatabaseSearchDOI dbSearchDOI = new DatabaseSearchDOI();
//		dbSearchDOI.init(config);
//		dbSearchDOI.doGet(request, response);
	    
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
		Author authorCheck5 = new Author();
		authorCheck5.setFirstName("Alan");
		authorCheck5.setLastName("Laur");
		authorCheck5.setMiddleName("Sack");
		Author authorCheck6 = new Author();
		authorCheck6.setFirstName("D");
		authorCheck6.setLastName("Dietrach");
		authorCheck6.setMiddleName("Huf");
		authorsChecked.add(authorCheck1);
		authorsChecked.add(authorCheck2);
		authorsChecked.add(authorCheck3);
		authorsChecked.add(authorCheck4);
		authorsChecked.add(authorCheck5);
		authorsChecked.add(authorCheck6);
		
//		DatabaseSearchAuthors dbSearchAuthor = new DatabaseSearchAuthors();
		session.setAttribute("publicationAuthors", authorsChecked);
//		dbSearchAuthor.init(config);
//		dbSearchAuthor.doGet(request, response);
	    
		
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
		List <Author> authorsExpected = new ArrayList<Author>();
		
		Author masseyCheck1 = new Author();
		masseyCheck1.setFirstName("E");
		masseyCheck1.setLastName("Bobbison");
		
		Author masseyCheck2 = new Author();
		masseyCheck2.setFirstName("E");
		masseyCheck2.setLastName("Harte");
		masseyCheck2.setMiddleName("c");
		
		Author masseyCheck3 = new Author();
		masseyCheck3.setFirstName("zidane");
		masseyCheck3.setLastName("Anderson");
		masseyCheck3.setMiddleName("D");
		
		Author masseyCheck4 = new Author();
		masseyCheck4.setFirstName("Richard");
		masseyCheck4.setLastName("Mccaw");
		
		Author masseyCheck5 = new Author();
		masseyCheck5.setFirstName("names");
		masseyCheck5.setLastName("noddy");
		masseyCheck5.setMiddleName("damn");
		
		Author masseyCheck6 = new Author();
		masseyCheck6.setFirstName("c");
		masseyCheck6.setLastName("Smithy");
		masseyCheck6.setMiddleName("apache");
		
		Author miscCheck1 = new Author();
		miscCheck1.setFirstName("z");
		miscCheck1.setLastName("Daphine");
		
		Author miscCheck2 = new Author();
		miscCheck2.setFirstName("e");
		miscCheck2.setLastName("Bobbison");
		miscCheck2.setMiddleName("c");
		
		Author miscCheck3 = new Author();
		miscCheck3.setFirstName("boris");
		miscCheck3.setLastName("Harte");
		miscCheck3.setMiddleName("D");
		
		Author miscCheck4 = new Author();
		miscCheck4.setFirstName("tana");
		miscCheck4.setLastName("Umagan");
		
		Author miscCheck5 = new Author();
		miscCheck5.setFirstName("Forest");
		miscCheck5.setLastName("Canning");
		miscCheck5.setMiddleName("Ceaser");
		
		Author miscCheck6 = new Author();
		miscCheck6.setFirstName("q");
		miscCheck6.setLastName("Bosling");
		miscCheck6.setMiddleName("maximus");
		
		authorsChecked.add(masseyCheck1);
		authorsChecked.add(miscCheck1);
		authorsChecked.add(masseyCheck2);
		authorsChecked.add(miscCheck2);
		authorsChecked.add(miscCheck3);
		authorsChecked.add(miscCheck4);
		authorsChecked.add(masseyCheck3);
		authorsChecked.add(masseyCheck4);
		authorsChecked.add(miscCheck5);
		authorsChecked.add(miscCheck6);
		authorsChecked.add(masseyCheck5);
		authorsChecked.add(masseyCheck6);

		authorsExpected = authorsExpected();
		
//		DatabaseSearchAuthors dbSearchAuthor = new DatabaseSearchAuthors();
		session.setAttribute("publicationAuthors", authorsChecked);
//		dbSearchAuthor.init(config);
//		dbSearchAuthor.doGet(request, response);
		
		//masseyCheck1.setDepartment("test");
	    
		session.getAttribute("publicationAuthors");
		int i = 0;
		assertEquals(authorsExpected.size(),authorsChecked.size());
		while (authorsChecked.size() != i)
		{
			Author authorRetrieved = authorsChecked.get(i);
			assertTrue(authorRetrieved.getInDatabase());
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
		assertEquals(authorCompare.getAffiliation(),authorRetrieved.getAffiliation());
		assertEquals(authorCompare.getDepartment(),authorRetrieved.getDepartment());
	}
	
	
	protected List<Author> authorsChecked(){
		
		List <Author> authorsChecked = new ArrayList<Author>();
		
		return authorsChecked;
	}
	
	
	private List<Author> authorsExpected(){
		
		List <Author> authorsExpected = new ArrayList<Author>();
		
		Author masseyExp1 = new Author();
		masseyExp1.setFirstName("E");
		masseyExp1.setLastName("Bobbison");
		masseyExp1.setAffiliation("Massey");
		masseyExp1.setCollege("Science");
		masseyExp1.setType("Student");
		masseyExp1.setDepartment("SEAT");
		masseyExp1.setID("61120");
		
		Author masseyExp2 = new Author();
		masseyExp2.setFirstName("E");
		masseyExp2.setLastName("Harte");
		masseyExp2.setMiddleName("c");
		masseyExp2.setAffiliation("Massey");
		masseyExp2.setCollege("Science");
		masseyExp2.setType("Professor");
		masseyExp2.setDepartment("SEAT");
		masseyExp2.setID("61132");
		
		Author masseyExp3 = new Author();
		masseyExp3.setFirstName("zidane");
		masseyExp3.setLastName("Anderson");
		masseyExp3.setMiddleName("D");
		masseyExp3.setAffiliation("Massey");
		masseyExp3.setCollege("Science");
		masseyExp3.setType("Professor");
		masseyExp3.setDepartment("SEAT");
		masseyExp3.setID("61144");
		
		Author masseyExp4 = new Author();
		masseyExp4.setFirstName("Richard");
		masseyExp4.setLastName("Mccaw");
		masseyExp4.setAffiliation("Massey");
		masseyExp4.setCollege("Science");
		masseyExp4.setType("Student");
		masseyExp4.setDepartment("SEAT");
		masseyExp4.setID("61139");
		
		Author masseyExp5 = new Author();
		masseyExp5.setFirstName("names");
		masseyExp5.setLastName("noddy");
		masseyExp5.setMiddleName("damn");
		masseyExp5.setAffiliation("Massey");
		masseyExp5.setCollege("Science");
		masseyExp5.setType("Student");
		masseyExp5.setDepartment("SEAT");
		masseyExp5.setID("61149");
		
		Author masseyExp6 = new Author();
		masseyExp6.setFirstName("c");
		masseyExp6.setLastName("Smithy");
		masseyExp6.setMiddleName("apache");
		masseyExp6.setAffiliation("Massey");
		masseyExp6.setCollege("Science");
		masseyExp6.setType("Student");
		masseyExp6.setDepartment("SEAT");
		masseyExp6.setID("61142");
		
		Author miscExp1 = new Author();
		miscExp1.setFirstName("z");
		miscExp1.setLastName("Daphine");
		miscExp1.setAffiliation("Dublin University");
		miscExp1.setID("0661138");
		
		Author miscExp2 = new Author();
		miscExp2.setFirstName("e");
		miscExp2.setLastName("Bobbison");
		miscExp2.setMiddleName("c");
		miscExp2.setAffiliation("Dublin University");
		miscExp2.setID("0961120");
		
		Author miscExp3 = new Author();
		miscExp3.setFirstName("boris");
		miscExp3.setLastName("Harte");
		miscExp3.setMiddleName("D");
		miscExp3.setAffiliation("Dublin University");
		miscExp3.setID("0461132");
		
		Author miscExp4 = new Author();
		miscExp4.setFirstName("tana");
		miscExp4.setLastName("Umagan");
		miscExp4.setAffiliation("Kyoto University");
		miscExp4.setID("0061140");
		
		Author miscExp5 = new Author();
		miscExp5.setFirstName("Forest");
		miscExp5.setLastName("Canning");
		miscExp5.setMiddleName("Ceaser");
		miscExp5.setAffiliation("Dublin University");
		miscExp5.setID("0861134");
		
		Author miscExp6 = new Author();
		miscExp6.setFirstName("q");
		miscExp6.setLastName("Bosling");
		miscExp6.setMiddleName("maximus");
		miscExp6.setAffiliation("Cali University");
		miscExp6.setID("0561131");
		
		authorsExpected.add(masseyExp1);
		authorsExpected.add(miscExp1);
		authorsExpected.add(masseyExp2);
		authorsExpected.add(miscExp2);
		authorsExpected.add(miscExp3);
		authorsExpected.add(miscExp4);
		authorsExpected.add(masseyExp3);
		authorsExpected.add(masseyExp4);
		authorsExpected.add(miscExp5);
		authorsExpected.add(miscExp6);
		authorsExpected.add(masseyExp5);
		authorsExpected.add(masseyExp6);
		
		return authorsExpected;

	}
	
	
}

