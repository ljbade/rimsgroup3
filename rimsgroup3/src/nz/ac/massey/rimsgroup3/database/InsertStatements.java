package nz.ac.massey.rimsgroup3.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;



import nz.ac.massey.rimsgroup3.metadata.bean.*;



public class InsertStatements {


	public static PreparedStatement authorStatement(Connection connection,
			Author author) {
		try {
		PreparedStatement statementAuthor = connection.prepareStatement
		("INSERT INTO AUTHOR " + "VALUES(?,?,?,?,?,?,?,?)");
			statementAuthor.setString(1, author.getID());
			statementAuthor.setString(2, author.getFirstName());
			statementAuthor.setString(3, author.getLastName());
			statementAuthor.setString(4, author.getMiddleName());
			statementAuthor.setString(5, author.getType());
			statementAuthor.setString(6, author.getDepartment());
			statementAuthor.setString(7, author.getCollege());
			statementAuthor.setString(8, author.getEmail()); 
			return statementAuthor;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static PreparedStatement bookStatment(Connection connection, 
			Book book){
		try {
			PreparedStatement statementBook = connection.prepareStatement
			("INSERT INTO BOOK " + "VALUES(?,?,?,?)");
				statementBook.setString(1, book.getID());
				statementBook.setString(2, book.getPlacePublished());
				statementBook.setString(3, book.getChapterTitle());
				statementBook.setString(4, book.getBookTitle());
				return statementBook;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		
	}
	
	public static PreparedStatement conferenceStatment(Connection connection, 
			Conference conference){
		try {
			PreparedStatement statementConference = connection.prepareStatement
			("INSERT INTO CONFERENCE " + "VALUES(?,?,?,?,?,?)");
				statementConference.setString(1, conference.getID());
				statementConference.setString(2, conference.getAbstractTitle());
				statementConference.setString(3, conference.getConferenceName());
				statementConference.setString(4, conference.getStartDate());
				statementConference.setString(5, conference.getEndDate());
				statementConference.setString(6, conference.getLocation());
				
				return statementConference;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		
	}
	
	public static PreparedStatement editorStatment(Connection connection, 
			Editor editor, Book book){
		try {
			PreparedStatement statementEditor = connection.prepareStatement
			("INSERT INTO EDITORS " + "(EDITOR_FIRST_NAME,EDITOR_MIDDLE_NAME,EDITOR_LAST_NAME,PUBLICATION_ID) "
					+ "VALUES(?,?,?,?)");
				statementEditor.setString(1, editor.getFirstName());
				statementEditor.setString(2, editor.getMiddleName());
				statementEditor.setString(3, editor.getLastName());
				statementEditor.setString(4, book.getID());
				return statementEditor;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		
	}
	
	public static PreparedStatement journalStatment(Connection connection, 
			Journal journal){
		try {
			PreparedStatement statementJournal = connection.prepareStatement
			("INSERT INTO JOURNAL " + "VALUES(?,?,?,?,?)");
				statementJournal.setString(1, journal.getID());
				statementJournal.setString(2, journal.getArticleTitle());
				statementJournal.setString(3, journal.getJournalTitle());
				statementJournal.setString(4, journal.getVolume());
				statementJournal.setString(5, journal.getIssue());
				return statementJournal;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		
	}
	
	public static PreparedStatement publicationStatment(Connection connection, 
			Publication publication){
		try {
			PreparedStatement statementPublication = connection.prepareStatement
			("INSERT INTO PUBLICATION " + "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
				statementPublication.setString(1, publication.getID());
				statementPublication.setString(2, publication.getPublicationCategory());
				statementPublication.setString(3, publication.getPublisher());
				statementPublication.setString(4, publication.getYear());
				statementPublication.setString(5, publication.getStartPage());
				statementPublication.setString(6, publication.getEndPage());
				statementPublication.setString(7, publication.getIssn());
				statementPublication.setString(8, publication.getUrl()); 
				statementPublication.setString(9, publication.isQualityAssured());
				statementPublication.setString(10, publication.getAbstractText());
				List <String> keyWords =   new ArrayList<String>();
				keyWords = publication.getKeyWords();
				int i = 0;
				String keywords = "";
				while ( i != keyWords.size())
				{
					keywords = keywords + "," + keyWords.get(i);
					i++;
				}
				statementPublication.setString(11, keywords);
				return statementPublication;
			}
		
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
			
	}
	
	public static PreparedStatement publishedStatment(Connection connection, 
			Publication publication, Author author){
		try {
			PreparedStatement statementPublished = connection.prepareStatement
			("INSERT INTO PUBLISHED " + "VALUES(?,?)");

				statementPublished.setString(1, publication.getID());
				statementPublished.setString(2, author.getID());
				return statementPublished;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		
	}
	
	
}
