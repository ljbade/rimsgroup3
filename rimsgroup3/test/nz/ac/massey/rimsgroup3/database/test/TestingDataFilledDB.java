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
Connection connection;	

	public TestingDataFilledDB(String name)
	{
		super(name);
		System.setProperty("cactus.contextURL", "http://localhost:8080/rimsgroup3");
		
	}
	
	
	public void setUp() throws SQLException, ServletException, IOException{
		this.dbSelection = "datatest";
    	DatabaseConnectI datasource = new DatabaseConnection();
    	this.dataSource =  datasource.setUp(this.dbSelection);
    	synchronized (dataSource)
		{
			this.connection = dataSource.getConnection();
		}
    	ScriptRunner runner = new ScriptRunner(this.connection ,false, true);
    	//runner.runScript(new BufferedReader(new FileReader("/scripts/create-tables.sql")));
    	//runner.runScript(new BufferedReader(new FileReader("/scripts/testCode.sql")));
	}
	
	public void tearDown(){

	}
	
	public void testNoPublicationFound() throws SQLException, ServletException, IOException {
		String doi = "10.1016/j.tcs.2010.07.0078";
		Boolean checkInDB = ReadStatements.publicationReadStatment(connection, doi);
		assertFalse(checkInDB);


	}
	
	public void testPublicationFound() throws SQLException, ServletException, IOException {
		String doi = "10.1016/j.tcs.2010.07.007";
		Boolean checkInDB = ReadStatements.publicationReadStatment(connection, doi);
		assertTrue(checkInDB);
	}
	
	public void testNoAuthorsFound() throws SQLException, ServletException, IOException{
		
		
		List <Author> authorsChecked = new ArrayList<Author>();
		Publication publication = new Publication();
		
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
		publication.setAuthors(authorsChecked);
		
		List <Author> authorsExpected = new ArrayList<Author>();
		authorsExpected = authorsNotFoundExp();
		
		SearchAuthors.authorsInDatabase(connection, publication);
		int i = 0;
		assertEquals(authorsChecked.size(),authorsExpected.size());
		while (authorsExpected.size() != i)
		{
			Author authorRetrieved = authorsChecked.get(i);
			assertFalse(authorRetrieved.getInDatabase());
			Author authorCompare = authorsExpected.get(i);
			authorComparison(authorRetrieved,authorCompare);
			i++;
		}
		
	}
	
	public void testAuthorsFound() throws SQLException, ServletException, IOException{
		
		Publication publication = new Publication();
		List <Author> authorsChecked = new ArrayList<Author>();
		
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
		publication.setAuthors(authorsChecked);
		

		List <Author> authorsExpected = new ArrayList<Author>();
		authorsExpected = authorsFoundExpected();
		

		SearchAuthors.authorsInDatabase(connection, publication);
		
		
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
	
	
	private List<Author> authorsNotFoundExp(){
		
		List <Author> authorsExpected = new ArrayList<Author>();
		
		Author authorExp1 = new Author();
		authorExp1.setFirstName("g");
		authorExp1.setLastName("Bobbison");
		//authorCheck1.setMiddleName("");
		Author authorExp2 = new Author();
		authorExp2.setFirstName("a");
		authorExp2.setLastName("Mathews");
		authorExp2.setMiddleName("c");
		Author authorExp3 = new Author();
		authorExp3.setFirstName("Squall");
		authorExp3.setLastName("Anderson");
		authorExp3.setMiddleName("D");
		Author authorExp4 = new Author();
		authorExp4.setFirstName("Cam");
		authorExp4.setLastName("Mccaw");
		//authorCheck4.setMiddleName("");
		Author authorExp5 = new Author();
		authorExp5.setFirstName("Alan");
		authorExp5.setLastName("Laur");
		authorExp5.setMiddleName("Sack");
		Author authorExp6 = new Author();
		authorExp6.setFirstName("D");
		authorExp6.setLastName("Dietrach");
		authorExp6.setMiddleName("Huf");
		authorsExpected.add(authorExp1);
		authorsExpected.add(authorExp2);
		authorsExpected.add(authorExp3);
		authorsExpected.add(authorExp4);
		authorsExpected.add(authorExp5);
		authorsExpected.add(authorExp6);
		return authorsExpected;
	}

	
	
	private List<Author> authorsFoundExpected(){
		
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

