package nz.ac.massey.rimsgroup3.DB.test;



import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import junit.framework.*;

import nz.ac.massey.rimsgroup3.database.*;
import nz.ac.massey.rimsgroup3.metadata.bean.*;


public class TestingDatabase extends ServletTestCase{
	
	public void publicationCheck(Publication publicationRS, Publication publication)
	{
		assertEquals(publicationRS.getID(),publication.getID());
		assertEquals(publicationRS.getPublicationCategory(),publication.getPublicationCategory());
		assertEquals(publicationRS.getYear(),publication.getYear());
		assertEquals(publicationRS.getStartPage(),publication.getStartPage());
		assertEquals(publicationRS.getEndPage(),publication.getEndPage());
		assertEquals(publicationRS.getAbstractText(),publication.getAbstractText());
		assertEquals(publicationRS.getIssn(),publication.getIssn());
		assertEquals(publicationRS.getUrl(),publication.getUrl());
		assertEquals(publicationRS.isQualityAssured(),publication.isQualityAssured());
		assertEquals(publicationRS.getKeyWords(),publication.getKeyWords());
	}
	
	public void authorCheck(List<Author> authorRS, List<Author> authors){
		int i = 0;
		assertEquals(authorRS.size(),authors.size());
		while ( i != authorRS.size())
		{
			Author authorNew = authorRS.get(i);
			Author authorOld = authors.get(i);
			assertEquals(authorNew.getID(),authorOld.getID());
			assertEquals(authorNew.getCollege(),authorOld.getCollege());
			assertEquals(authorNew.getDepartment(),authorOld.getDepartment());
			assertEquals(authorNew.getEmail(),authorOld.getEmail());
			assertEquals(authorNew.getFirstName(),authorOld.getFirstName());
			assertEquals(authorNew.getLastName(),authorOld.getLastName());
			assertEquals(authorNew.getMiddleName(),authorOld.getMiddleName());
			assertEquals(authorNew.getType(),authorOld.getType());
			i++;
		}
	}
	
	public TestingDatabase(String name)
	{
		super(name);
		System.setProperty("cactus.contextURL", "http://localhost:8080/rimsgroup3");
	}
	
	
	public void setUp(){

	}
	
	public void tearDown(){

	}
	
	public void testBook() throws SQLException, ServletException, IOException {

		Publication publication = new Publication();
		publication.setID("08326517");
		publication.setPublicationCategory("book");
		publication.setPublisher("publisher");
		publication.setYear("1988");
		publication.setStartPage("1");
		publication.setEndPage("6");
		publication.setAbstractText("abstractText");
		publication.setIssn("issn");
		publication.setUrl("doi");
		publication.setQualityAssured("yes");
		List <String> keyWords = new ArrayList<String>();
		keyWords.add("try");
		keyWords.add("this");
		keyWords.add("please");
		publication.setKeyWords(keyWords);
		
		Author author = new Author();
		author.setCollege("College");
		author.setDepartment("Department");
		author.setEmail("madeup@hotmail.com");
		author.setFirstName("Firstname");
		author.setID("1");
		author.setLastName("lastName");
		author.setMiddleName("MiddleName");
		author.setType("type");
		List <Author> authors = new ArrayList<Author>();
		authors.add(author);
		
		Editor editor = new Editor();
		editor.setFirstName("firstName");
		editor.setLastName("lastname");
		editor.setMiddleName("middleName");
		
		Editor editor2 = new Editor();
		editor2.setFirstName("firstName2");
		editor2.setLastName("lastname2");
		editor2.setMiddleName("middleName2");
		
		Book book = new Book();
		book.setID(publication.getID());
		book.setPlacePublished("placePublished");
		book.setChapterTitle("chapterTitle");
		book.setBookTitle("bookTitle");
		List <Editor> editors = new ArrayList<Editor>();
		editors.add(editor);
		editors.add(editor2);
		book.setEditors(editors);
		
		Information information = new Information();
		information.setAuthors(authors);
		information.setEditors(editors);
		information.setBook(book);
		information.setPublication(publication);
		
		HttpSession session = request.getSession(true);  
		session.setAttribute("info", information);
	    session.setAttribute("publicationDOI", publication.getUrl());
		config.setInitParameter("test", "testDB");
	    
	    DatabaseInsert dbinsert = new DatabaseInsert();
	    dbinsert.init(config);
	    dbinsert.doGet(request, response);
	    
	    DatabaseRead dbread = new DatabaseRead();
		dbread.init(config);
		dbread.doGet(request,response);
	
		
		Information checkInformation = new Information();
		checkInformation = (Information) session.getAttribute("information");


		List<Author> authorRS = checkInformation.getAuthors();
		Publication publicationRS = checkInformation.getPublication();
		Book bookRS = checkInformation.getBook();
		List<Editor> editorRS = checkInformation.getEditors();
		
		publicationCheck(publicationRS,publication);
		authorCheck(authorRS,authors);
		assertEquals(bookRS.getBookTitle(),book.getBookTitle());
		assertEquals(bookRS.getChapterTitle(),book.getChapterTitle());
		assertEquals(bookRS.getPlacePublished(),book.getPlacePublished());
		int i = 0;
		assertEquals(editorRS.size(),editors.size());
		while (i != editorRS.size())
		{
			Editor editorNew = editorRS.get(i);
			Editor editorOld = editors.get(i);
			assertEquals(editorNew.getFirstName(),editorOld.getFirstName());
			assertEquals(editorNew.getMiddleName(),editorOld.getMiddleName());
			assertEquals(editorNew.getLastName(),editorOld.getLastName());
			i++;
		}
		
	}
	

	
	public void testJournal() throws SQLException, ServletException, IOException
	{
		Publication publication = new Publication();
		publication.setID("05326517");
		publication.setPublicationCategory("journal");
		publication.setPublisher("publisher");
		publication.setYear("1988");
		publication.setStartPage("1");
		publication.setEndPage("6");
		publication.setAbstractText("abstractText");
		publication.setIssn("issn");
		publication.setUrl("doi2");
		publication.setQualityAssured("yes");
		List <String> keyWords = new ArrayList<String>();
		keyWords.add("it");
		keyWords.add("better");
		keyWords.add("be");
		publication.setKeyWords(keyWords);
		
		Author author = new Author();
		author.setCollege("College");
		author.setDepartment("Department");
		author.setEmail("madeup@hotmail.com");
		author.setFirstName("Firstname");
		author.setID("2");
		author.setLastName("lastName");
		author.setMiddleName("MiddleName");
		author.setType("type");
		List <Author> authors = new ArrayList<Author>();
		authors.add(author);
		
		Journal journal = new Journal();
		journal.setID(publication.getID());
		journal.setVolume("4");
		journal.setIssue("2");
		journal.setJournalTitle("journalTitle");
		journal.setArticleTitle("articleTitle");
		
		Information information = new Information();
		information.setAuthors(authors);
		information.setJournal(journal);
		information.setPublication(publication);
		
		
		HttpSession session = request.getSession(true);  
		session.setAttribute("info", information);
	    session.setAttribute("publicationDOI", publication.getUrl());
	    config.setInitParameter("test", "testDB");
	    
	    DatabaseInsert dbinsert = new DatabaseInsert();
	    dbinsert.init(config);
	    dbinsert.doGet(request, response);
	    
	    DatabaseRead dbread = new DatabaseRead();
		dbread.init(config);
		dbread.doGet(request,response);
		
		Information checkInformation = new Information();
		checkInformation = (Information) session.getAttribute("information");
		
		List<Author> authorRS = checkInformation.getAuthors();
		Publication publicationRS = checkInformation.getPublication();
		Journal journalRS = checkInformation.getJournal();
		
		publicationCheck(publicationRS,publication);
		authorCheck(authorRS,authors);
		assertEquals(journalRS.getVolume(),journal.getVolume());
		assertEquals(journalRS.getIssue(),journal.getIssue());
		assertEquals(journalRS.getJournalTitle(),journal.getJournalTitle());
		assertEquals(journalRS.getArticleTitle(),journal.getArticleTitle());
	}
	
	public void testConference() throws SQLException, ServletException, IOException {

		Publication publication = new Publication();
		publication.setID("02326517");
		publication.setPublicationCategory("conference");
		publication.setPublisher("publisher");
		publication.setYear("1988");
		publication.setStartPage("1");
		publication.setEndPage("6");
		publication.setAbstractText("abstractText");
		publication.setIssn("issn");
		publication.setUrl("doi3");
		publication.setQualityAssured("yes");
		List <String> keyWords = new ArrayList<String>();
		keyWords.add("try");
		keyWords.add("this");
		keyWords.add("out");
		publication.setKeyWords(keyWords);
		
		Author author = new Author();
		author.setCollege("College");
		author.setDepartment("Department");
		author.setEmail("madeup@hotmail.com");
		author.setFirstName("Firstname");
		author.setID("3");
		author.setLastName("LastName");
		author.setMiddleName("MiddleName");
		author.setType("type");
		
		Author author2 = new Author();
		author2.setCollege("College");
		author2.setDepartment("Department");
		author2.setEmail("madeup@hotmail.com");
		author2.setFirstName("Firstname");
		author2.setID("4");
		author2.setLastName("LastName");
		author2.setMiddleName("MiddleName");
		author2.setType("type"); 
		List <Author> authors = new ArrayList<Author>();
		authors.add(author);
		authors.add(author2);
		
		Conference conference = new Conference();
		conference.setID(publication.getID());
		conference.setAbstractTitle("abstractTitle");
		conference.setConferenceName("conferenceName");
		conference.setStartDate("18/8/1988");
		conference.setEndDate("19/8/1988");
		conference.setLocation("location");

		Information information = new Information();
		information.setAuthors(authors);
		information.setPublication(publication);
		information.setConference(conference);
		
		HttpSession session = request.getSession(true);  
		session.setAttribute("info", information);
	    session.setAttribute("publicationDOI", publication.getUrl());
	    config.setInitParameter("test", "testDB");
		
	    DatabaseInsert dbinsert = new DatabaseInsert();
	    dbinsert.init(config);
	    dbinsert.doGet(request, response);
	    
	    DatabaseRead dbread = new DatabaseRead();
		dbread.init(config);
		dbread.doGet(request,response);
	
		Information checkInformation = new Information();
		checkInformation = (Information) session.getAttribute("information");

		
		List<Author> authorRS = checkInformation.getAuthors();
		Publication publicationRS = checkInformation.getPublication();
		Conference conferenceRS = checkInformation.getConference();

		
		publicationCheck(publicationRS,publication);
		authorCheck(authorRS,authors);
		assertEquals(conferenceRS.getAbstractTitle(),conference.getAbstractTitle());
		assertEquals(conferenceRS.getConferenceName(),conference.getConferenceName());
		assertEquals(conferenceRS.getStartDate(),conference.getStartDate());
		assertEquals(conferenceRS.getEndDate(),conference.getEndDate());
		assertEquals(conferenceRS.getLocation(),conference.getLocation());
	}
	
	

	
}
