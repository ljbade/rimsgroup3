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
	
	
	public TestingDatabase(String name)
	{
		super(name);
		System.setProperty("cactus.contextURL", "http://localhost:8080/rimsgroup3");
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
		
		/*Conference conference = new Conference();
		conference.setID(publication.getID());
		conference.setAbstractTitle("abstractTitle");
		conference.setConferenceName("conferenceName");
		conference.setStartDate("18/8/1988");
		conference.setEndDate("19/8/1988");
		conference.setLocation("location");
		*/
		
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
		//information.setJournal(journal);
		information.setPublication(publication);
		//information.setConference(conference);
		
		/*request.setAttribute("info", information);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/DatabaseInsert");
		dispatcher.forward(request, response);
		request.removeAttribute("info");*/
		
		
		HttpSession session = request.getSession(true);  
		session.setAttribute("info", information);
	    session.setAttribute("publicationDOI", publication.getUrl());
		
	    DatabaseInsert dbinsert = new DatabaseInsert();
	    dbinsert.init(config);
	    dbinsert.doGet(request, response);
	    
	    DatabaseRead dbread = new DatabaseRead();
		dbread.init(config);
		dbread.doGet(request,response);
	
		
		Information checkInformation = new Information();
		checkInformation = (Information) session.getAttribute("information");

		session.removeAttribute("information");
		
		List<Author> authorRS = checkInformation.getAuthors();
		Publication publicationRS = checkInformation.getPublication();
		Conference conferenceRS = checkInformation.getConference();
		Book bookRS = checkInformation.getBook();
		Journal journalRS = checkInformation.getJournal();
		List<Editor> editorRS = checkInformation.getEditors();
		
		assertEquals(publicationRS.getKeyWords(),publication.getKeyWords());
		
		/*assertEquals(publicationRS, publication);
		assertEquals(authorRS, authors);
		assertEquals(conferenceRS, conference);
		assertEquals(bookRS, book);
		assertEquals(journalRS, journal);
		assertEquals(editorRS, editors);*/
		
	}
	/*
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
		
		
		request.setAttribute("info", information);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/DatabaseInsert");
		dispatcher.forward(request, response);

		
		DatabaseRead dbtest = new DatabaseRead();
		dbtest.init(config);
		dbtest.doGet(request,response);
		//RequestDispatcher dispatcher2 = request.getRequestDispatcher("/DatabaseRead");
		//dispatcher2.forward(request, response);
		
		Information checkInformation = new Information();
		checkInformation = (Information) session.getAttribute("information");
		
		List<Author> authorRS = checkInformation.getAuthors();
		Publication publicationRS = checkInformation.getPublication();
		Conference conferenceRS = checkInformation.getConference();
		Book bookRS = checkInformation.getBook();
		Journal journalRS = checkInformation.getJournal();
		List<Editor> editorRS = checkInformation.getEditors();
		
		assertEquals(publicationRS.getKeyWords(),publication.getKeyWords());
		
		
	}*/
	
	
	
	
	
}
