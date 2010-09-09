package nz.ac.massey.rimsgroup3.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import nz.ac.massey.rimsgroup3.metadata.bean.*;


public class ReadStatements {
	
	public static List<Author> authorReadStatement(Connection connection, String query) {
		
		try { 
			PreparedStatement statementAuthor = connection.prepareStatement
			("SELECT AUTHOR.AUTHOR_ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, TYPE, DEPARTMENT, " +
					"COLLEGE, EMAIL FROM AUTHOR, PUBLISHED WHERE AUTHOR.AUTHOR_ID = " +
						"PUBLISHED.AUTHOR_ID and PUBLICATION_ID = " +
							"(SELECT PUBLICATION_ID FROM PUBLICATION " +
								"WHERE URL_LOCATION = '" + query + "')");
			ResultSet authorRS = statementAuthor.executeQuery();
			List <Author> authors = new ArrayList<Author>();
			while (authorRS.next())
			{
				Author authorCheck = new Author();
				authorCheck.setID(authorRS.getString(1));
				authorCheck.setFirstName(authorRS.getString(2));
				authorCheck.setLastName(authorRS.getString(3));
				authorCheck.setMiddleName(authorRS.getString(4));
				authorCheck.setType(authorRS.getString(5));
				authorCheck.setDepartment(authorRS.getString(6));
				authorCheck.setCollege(authorRS.getString(7));
				authorCheck.setEmail(authorRS.getString(8));
				authors.add(authorCheck);
			}
			if  (statementAuthor != null) statementAuthor.close();
			return authors;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static Book bookReadStatment(Connection connection, String query){
		try {
			PreparedStatement statementBook = connection.prepareStatement
			("SELECT PLACE_PUBLISHED,CHAPTER_TITLE,BOOK_TITLE FROM BOOK " +
					"WHERE PUBLICATION_ID = (SELECT PUBLICATION_ID FROM PUBLICATION " +
						"WHERE URL_LOCATION = '" + query + "')");
				ResultSet bookRS = statementBook.executeQuery();
				Book bookCheck = null;
				while (bookRS.next())
				{
					bookCheck = new Book();
					bookCheck.setPlacePublished(bookRS.getString(1));
					bookCheck.setChapterTitle(bookRS.getString(2));
					bookCheck.setBookTitle(bookRS.getString(3));
				}
				if (statementBook != null) statementBook.close();
				return bookCheck;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		
	}
	
	public static Conference conferenceReadStatment(Connection connection, String query){
		try {
			PreparedStatement statementConference = connection.prepareStatement
			("SELECT ABSTRACT_TITLE,CONFERENCE_NAME,START_DATE,END_DATE,LOCATION FROM CONFERENCE " +
					"WHERE PUBLICATION_ID = (SELECT PUBLICATION_ID FROM PUBLICATION " +
					"WHERE URL_LOCATION = '" + query + "')");
				ResultSet conferenceRS = statementConference.executeQuery();
				Conference conferenceCheck = null;
				conferenceCheck = new Conference();
				while (conferenceRS.next())
				{
					conferenceCheck.setAbstractTitle(conferenceRS.getString(1));
					conferenceCheck.setConferenceName(conferenceRS.getString(2));
					conferenceCheck.setStartDate(conferenceRS.getString(3));
					conferenceCheck.setEndDate(conferenceRS.getString(4));
					conferenceCheck.setLocation(conferenceRS.getString(5));
				}
				if(statementConference != null) statementConference.close();
				return conferenceCheck;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		
	}
	
	public static List<Editor> editorReadStatment(Connection connection, String query){
		try {
			PreparedStatement statementEditor = connection.prepareStatement
			("SELECT EDITOR_FIRST_NAME, EDITOR_MIDDLE_NAME, EDITOR_LAST_NAME FROM EDITORS " +
					"WHERE PUBLICATION_ID = (SELECT PUBLICATION_ID FROM PUBLICATION " +
						"WHERE URL_LOCATION = '" + query + "')");
			ResultSet editorRS = statementEditor.executeQuery();
			List <Editor> editors = new ArrayList<Editor>();
			while (editorRS.next())
			{
				Editor editorCheck = new Editor();
				editorCheck.setFirstName(editorRS.getString(1));
				editorCheck.setMiddleName(editorRS.getString(2));
				editorCheck.setLastName(editorRS.getString(3));	
				editors.add(editorCheck);
			}
			if (statementEditor != null) statementEditor.close();
			return editors;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		
	}
	
	public static Journal journalReadStatment(Connection connection, String query){
		try {
			PreparedStatement statementJournal = connection.prepareStatement
			("SELECT ARTICLE_TITLE,JOURNAL_TITLE,VOLUME_NO,ISSUE_NO FROM JOURNAL " + 
					"WHERE PUBLICATION_ID = (SELECT PUBLICATION_ID FROM PUBLICATION " +
						"WHERE URL_LOCATION = '" + query + "')"); 
								
				ResultSet journalRS = statementJournal.executeQuery();
				Journal journalCheck = null;
				while (journalRS.next())
				{
					journalCheck = new Journal();
					journalCheck.setArticleTitle(journalRS.getString(1));
					journalCheck.setJournalTitle(journalRS.getString(2));
					journalCheck.setVolume(journalRS.getString(3));
					journalCheck.setIssue(journalRS.getString(4));
				}
				if (statementJournal != null) statementJournal.close();
				return journalCheck;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		
	}
	
	public static Publication publicationReadStatment(Connection connection, String query){
		try {
			PreparedStatement statementPublication = connection.prepareStatement
			("SELECT * FROM PUBLICATION WHERE URL_LOCATION = '" + query + "'" );
				ResultSet publicationRS = statementPublication.executeQuery();
				Publication publicationCheck = null;
				while (publicationRS.next())
				{
					publicationCheck = new Publication();
					publicationCheck.setID(publicationRS.getString(1));
					publicationCheck.setPublicationCategory(publicationRS.getString(2));
					publicationCheck.setPublisher(publicationRS.getString(3));
					publicationCheck.setYear(publicationRS.getString(4));
					publicationCheck.setStartPage(publicationRS.getString(5));
					publicationCheck.setEndPage(publicationRS.getString(6));
					publicationCheck.setIssn(publicationRS.getString(7));
					publicationCheck.setUrl(publicationRS.getString(8));
					publicationCheck.setQualityAssured(publicationRS.getString(9));
					publicationCheck.setAbstractText(publicationRS.getString(10));
				
					String keywords = publicationRS.getString(11);
					List <String> keyWords = new ArrayList<String>();
					StringTokenizer tokenizer = new StringTokenizer(keywords,",");
					while (tokenizer.hasMoreTokens())
					{
						keyWords.add(tokenizer.nextToken());
					}
					publicationCheck.setKeyWords(keyWords);
				}
				if (statementPublication != null) statementPublication.close();
				return publicationCheck;
			}
		
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
			
	}
	

	

}
