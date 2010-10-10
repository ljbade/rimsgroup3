package nz.ac.massey.rimsgroup3.database.test;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.cactus.*;


import nz.ac.massey.rimsgroup3.database.*;
import nz.ac.massey.rimsgroup3.metadata.bean.*;
import nz.ac.massey.rimsgroup3.runner.ScriptRunner;


public class BlankTest extends ServletTestCase{
	DataSource dataSource;
	private String dbSelection;
	Connection connection;
	
	public BlankTest(String name)
	{
		super(name);
		System.setProperty("cactus.contextURL", "http://localhost:8080/rimsgroup3");
		
	}
	
	
	public void setUp() throws SQLException, ServletException, IOException{
		this.dbSelection = "swctest";
    	DatabaseConnection datasource = new DatabaseConnection();
    	this.dataSource =  datasource.setUp(this.dbSelection);
    	synchronized (dataSource)
		{
			this.connection = dataSource.getConnection();
		}
    	String path = getClass().getClassLoader().getResource("../../.").getPath();
    	path = path.replace("%20", " ");
    	String fs = System.getProperty("file.separator");
    	ScriptRunner runner = new ScriptRunner(this.connection ,false, true);
    	runner.runScript(new BufferedReader(new FileReader(path + "scripts" + fs +"create-tables.sql")));
    	
	}
	
	public void tearDown(){

	}
	

	public void testInsert() throws SQLException, ServletException, IOException {
		
		String doi = "10.1016/j.tcs.2010.07.0078";
		List <Author> authorsInserted = new ArrayList<Author>();
		Publication publication = new Publication();
		publication.setDoi(doi);
	    
		Author authorMass1 = new Author();
		authorMass1.setCollege("College");
		authorMass1.setDepartment("Department");
		authorMass1.setFirstName("Princess");
		authorMass1.setID("01");
		authorMass1.setLastName("Leia");
		authorMass1.setMiddleName("mid");
		authorMass1.setType("type");
		authorMass1.setAffiliation("Massey");
		 
		Author authorMisc1 = new Author();
		authorMisc1.setID("2");
		authorMisc1.setFirstName("Hans");
		authorMisc1.setLastName("Solo");
		authorMisc1.setMiddleName("middle");
		authorMisc1.setAffiliation("Dublin");
		
		authorsInserted.add(authorMass1);
		authorsInserted.add(authorMisc1);
		publication.setAuthors(authorsInserted);
		
		try {
			InsertDetails.detailsToInsert(this.connection, publication);
			assertTrue(true);
		}
		catch(Exception e){
			assertFalse(false);
		}
	  
		Boolean checkInDB = ReadStatements.publicationReadStatment(this.connection, doi);
		assertTrue(checkInDB);
		
		Publication publicationRetrieved = new Publication();
		List<Author> authorsRetrieved = new ArrayList<Author>();
		Author authorCheck1 = new Author();
		authorCheck1.setFirstName("Princess");
		authorCheck1.setLastName("Leia");
		authorCheck1.setMiddleName("mid");
		Author authorCheck2 = new Author();
		authorCheck2.setFirstName("Hans");
		authorCheck2.setLastName("Solo");
		authorCheck2.setMiddleName("middle");
		authorsRetrieved.add(authorCheck1);
		authorsRetrieved.add(authorCheck2);
		publicationRetrieved.setAuthors(authorsRetrieved);
		
		publicationRetrieved = SearchAuthors.authorsInDatabase(connection, publicationRetrieved);
		
		int i = 0;
		assertEquals(authorsInserted.size(),authorsRetrieved.size());
		while (authorsInserted.size() != i)
		{
			Author authorRetrieved = authorsRetrieved.get(i);
			Author authorCompare = authorsInserted.get(i);
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
	

	
}
