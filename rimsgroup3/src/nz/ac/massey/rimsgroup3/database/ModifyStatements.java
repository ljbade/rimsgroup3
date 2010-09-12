package nz.ac.massey.rimsgroup3.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import nz.ac.massey.rimsgroup3.metadata.bean.*;

public class ModifyStatements {
	
	
	public static PreparedStatement modifyPublication(Connection connection, String oldPubID,
			Publication newPublication) {
		try {
			PreparedStatement modifyPublication = connection.prepareStatement
			("UPDATE PUBLICATION SET  PUBLISHER = ?, PUBLICATION_YEAR  = ?, START_PAGE = ?, END_PAGE = ?, " +
					"ISSN_ISBN = ?, URL_LOCATION = ?, QUALITY_ASSURED = ?, ABSTRACT = ?, KEYWORDS = ? " +
						"WHERE PUBLICATION_ID = ?");
							
			//modifyPublication.setString(1,newPublication.getID());
			//modifyPublication.setString(1,newPublication.getPublicationCategory()); 
			modifyPublication.setString(1,newPublication.getPublisher());
			modifyPublication.setString(2,newPublication.getYear());
			modifyPublication.setString(3,newPublication.getStartPage());
			modifyPublication.setString(4,newPublication.getEndPage());
			modifyPublication.setString(5,newPublication.getIssn());
			modifyPublication.setString(6,newPublication.getUrl());
			modifyPublication.setString(7,newPublication.isQualityAssured());
			modifyPublication.setString(8,newPublication.getAbstractText());
			
			List <String> newKeyWords =   new ArrayList<String>();
			newKeyWords = newPublication.getKeyWords();
			int i = 0;
			String newkeywords = "";
			while ( i != newKeyWords.size())
			{
				newkeywords = newkeywords + "," + newKeyWords.get(i);
				i++;
			}	
			modifyPublication.setString(9,newkeywords);
			
			modifyPublication.setString(10,oldPubID);
			return modifyPublication;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
			
	}
	
	public static PreparedStatement modifyAuthor(Connection connection, String oldAuthorID,
			Author newAuthor){
		try {
			PreparedStatement modifyAuthor = connection.prepareStatement
			("UPDATE AUTHOR SET AUTHOR_ID = ?, FIRST_NAME = ?, LAST_NAME = ?, MIDDLE_NAME = ?, TYPE = ?, " +
					"DEPARTMENT = ?, COLLEGE = ?, EMAIL = ? " +
						"WHERE AUTHOR_ID = ?");
			modifyAuthor.setString(1, newAuthor.getID());
			modifyAuthor.setString(2, newAuthor.getFirstName());
			modifyAuthor.setString(3, newAuthor.getLastName());
			modifyAuthor.setString(4, newAuthor.getMiddleName());
			modifyAuthor.setString(5, newAuthor.getType());
			modifyAuthor.setString(6, newAuthor.getDepartment());
			modifyAuthor.setString(7, newAuthor.getCollege());
			modifyAuthor.setString(8, newAuthor.getEmail());
			modifyAuthor.setString(9, oldAuthorID);
			return modifyAuthor;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static PreparedStatement modifyPublished(Connection connection, String oldAuthorID,
			Author newAuthor){
		try {
			PreparedStatement modifyPublished = connection.prepareStatement
			("UPDATE PUBLISED SET AUTHOR_ID = ? " +
						"WHERE AUTHOR_ID = ?");
			modifyPublished.setString(1, newAuthor.getID());
			modifyPublished.setString(2, oldAuthorID);
			return modifyPublished;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static PreparedStatement modifyBook(Connection connection, String oldPubID,
			Book newBook){
		try {
			PreparedStatement modifyBook = connection.prepareStatement
			("UPDATE BOOK SET PLACE_PUBLISHED = ?, CHAPTER_TITLE = ?, BOOK_TITLE " +
					"WHERE PUBLICATION_ID = ?");
			modifyBook.setString(1, newBook.getPlacePublished());
			modifyBook.setString(2, newBook.getChapterTitle());
			modifyBook.setString(3, newBook.getBookTitle());
			modifyBook.setString(4, oldPubID);
			return modifyBook;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static PreparedStatement modifyJournal(Connection connection, String oldPubID,
			Journal newJournal){
		try {
			PreparedStatement modifyJournal = connection.prepareStatement
			("UPDATE BOOK SET ARTICLE_TITLE = ?, JOURNAL_TITLE = ?, VOLUME_NO = ?, ISSUE_NO = ? " +
					"WHERE PUBLICATION_ID = ?");
			modifyJournal.setString(1, newJournal.getArticleTitle());
			modifyJournal.setString(2, newJournal.getJournalTitle());
			modifyJournal.setString(3, newJournal.getVolume());
			modifyJournal.setString(4, newJournal.getIssue());
			modifyJournal.setString(5, oldPubID);
			return modifyJournal;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static PreparedStatement modifyConference(Connection connection, String oldPubID,
			Conference newConference){
		try {
			PreparedStatement modifyConference = connection.prepareStatement
			("UPDATE BOOK SET ABSTRACT_TITLE = ?, CONFERENCE_NAME = ?, START_DATE = ?, END_DATE = ?, " +
					"LOCATION = ? WHERE PUBLICATION_ID = ?");
			modifyConference.setString(1, newConference.getAbstractTitle());
			modifyConference.setString(2, newConference.getConferenceName());
			modifyConference.setString(3, newConference.getStartDate());
			modifyConference.setString(4, newConference.getEndDate());
			modifyConference.setString(5, newConference.getLocation());
			modifyConference.setString(6, oldPubID);
			return modifyConference;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static PreparedStatement modifyEditor(Connection connection, String oldEditorID,
			Editor newEditor){
		try {
			PreparedStatement modifyEditor = connection.prepareStatement
			("UPDATE BOOK SET EDITOR_FIRST_NAME = ?, EDITOR_MIDDLE_NAME = ?, EDITOR_LAST_NAME = ? " +
					"WHERE EDITOR_ID = ?");
			modifyEditor.setString(1, newEditor.getFirstName());
			modifyEditor.setString(2, newEditor.getMiddleName());
			modifyEditor.setString(3, newEditor.getLastName());
			modifyEditor.setString(4, oldEditorID);
			
			return modifyEditor;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

}
