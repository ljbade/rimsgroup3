package nz.ac.massey.rimsgroup3.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import nz.ac.massey.rimsgroup3.metadata.bean.*;


public class ReadStatements {
	
	public static Author miscReadStatement(Connection connection, Author author) {
		Author authoredIt = author;
		authoredIt.setInDatabase(false);
		String authorFirstName = authoredIt.getFirstName();
		String authorMiddleName = authoredIt.getMiddleName();
		
		PreparedStatement statementAuthor = null;
		try { 
			if(authorMiddleName == null && authorFirstName.length() == 1)
			{
				statementAuthor = connection.prepareStatement
				("SELECT MISC_ID, AFFILIATION FROM MISC_AUTHOR WHERE MISC_LAST_NAME = ? AND LOCATE(?,MISC_FIRST_NAME) = 1"); 
					
			}else if(authorMiddleName == null)
			{
				statementAuthor = connection.prepareStatement
				("SELECT MISC_ID, AFFILIATION FROM MISC_AUTHOR WHERE MISC_LAST_NAME = ? AND LOCATE(MISC_FIRST_NAME,?) = 1"); 
			}else if (authorFirstName.length() == 1 && authorMiddleName.length() == 1)
			{	
				statementAuthor = connection.prepareStatement
				("SELECT MISC_ID, AFFILIATION FROM MISC_AUTHOR WHERE MISC_LAST_NAME = ? AND LOCATE(?,MISC_FIRST_NAME) = 1 " +
						"AND (LOCATE(?,MISC_MIDDLE_NAME) = 1 OR MISC_MIDDLE_NAME IS NULL)");
			}
			else if (authorFirstName.length() == 1)
			{
				statementAuthor = connection.prepareStatement
				("SELECT MISC_ID, AFFILIATION FROM MISC_AUTHOR WHERE MISC_LAST_NAME = ? AND LOCATE(?,MISC_FIRST_NAME) = 1 " +
						"AND (LOCATE(MISC_MIDDLE_NAME,?) = 1 OR MISC_MIDDLE_NAME IS NULL)");
			}
			else if (authorMiddleName.length() == 1)
			{
				statementAuthor = connection.prepareStatement
				("SELECT MISC_ID, AFFILIATION FROM MISC_AUTHOR WHERE MISC_LAST_NAME = ? AND LOCATE(MISC_FIRST_NAME,?) = 1 " +
						"AND (LOCATE(?,MISC_MIDDLE_NAME) = 1 OR MISC_MIDDLE_NAME IS NULL)");
			}
			else 
			{
				statementAuthor = connection.prepareStatement
				("SELECT MISC_ID, AFFILIATION FROM MISC_AUTHOR WHERE MISC_LAST_NAME = ? AND LOCATE(MISC_FIRST_NAME,?) = 1 " +
						"AND (LOCATE(MISC_MIDDLE_NAME,?) = 1 OR MISC_MIDDLE_NAME IS NULL)");
			}
			
			statementAuthor.setString(1, author.getLastName());
			statementAuthor.setString(2, author.getFirstName());
			if (authorMiddleName != null)
			{
				statementAuthor.setString(3, author.getMiddleName());
			}
			ResultSet authorRS = statementAuthor.executeQuery();
			while (authorRS.next())
			{
				authoredIt.setID(authorRS.getString(1));
				authoredIt.setAffiliation(authorRS.getString(2));
				authoredIt.setInDatabase(true);
			}
			if  (statementAuthor != null) statementAuthor.close();
			return authoredIt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static Author masseyReadStatement(Connection connection, Author author) {
		Author authoredIt = author;
		authoredIt.setInDatabase(false);
		String authorFirstName = authoredIt.getFirstName();
		String authorMiddleName = authoredIt.getMiddleName();
		
		PreparedStatement statementAuthor = null;
		try { 
			if(authorMiddleName == null && authorFirstName.length() == 1)
			{
				statementAuthor = connection.prepareStatement
				("SELECT MASSEY_ID, TYPE, DEPARTMENT, COLLEGE FROM MASSEY_AUTHOR WHERE MASSEY_LAST_NAME = ? AND (LOCATE(?,MASSEY_FIRST_NAME) = 1)"); 
					
			}else if(authorMiddleName == null)
			{
				statementAuthor = connection.prepareStatement
				("SELECT MASSEY_ID, TYPE, DEPARTMENT, COLLEGE FROM MASSEY_AUTHOR WHERE MASSEY_LAST_NAME = ? AND LOCATE(MASSEY_FIRST_NAME,?) = 1"); 
			}else if (authorFirstName.length() == 1 && authorMiddleName.length() == 1)
			{	
				statementAuthor = connection.prepareStatement
				("SELECT MASSEY_ID, TYPE, DEPARTMENT, COLLEGE FROM MASSEY_AUTHOR WHERE MASSEY_LAST_NAME = ? AND LOCATE(?,MASSEY_FIRST_NAME) = 1 " +
						"AND (LOCATE(?,MASSEY_MIDDLE_NAME) = 1 OR MASSEY_MIDDLE_NAME IS NULL)");
			}
			else if (authorFirstName.length() == 1)
			{
				statementAuthor = connection.prepareStatement
				("SELECT MASSEY_ID, TYPE, DEPARTMENT, COLLEGE FROM MASSEY_AUTHOR WHERE MASSEY_LAST_NAME = ? AND LOCATE(?,MASSEY_FIRST_NAME) = 1 " +
						"AND (LOCATE(MASSEY_MIDDLE_NAME,?) = 1 OR MASSEY_MIDDLE_NAME IS NULL)");
			}
			else if (authorMiddleName.length() == 1)
			{
				statementAuthor = connection.prepareStatement
				("SELECT MASSEY_ID, TYPE, DEPARTMENT, COLLEGE FROM MASSEY_AUTHOR WHERE MASSEY_LAST_NAME = ? AND LOCATE(MASSEY_FIRST_NAME,?) = 1 " +
						"AND (LOCATE(?,MASSEY_MIDDLE_NAME) = 1 OR MASSEY_MIDDLE_NAME IS NULL)");
			}
			else 
			{
				statementAuthor = connection.prepareStatement
				("SELECT MASSEY_ID, TYPE, DEPARTMENT, COLLEGE FROM MASSEY_AUTHOR WHERE MASSEY_LAST_NAME = ? AND LOCATE(MASSEY_FIRST_NAME,?) = 1 " +
						"AND (LOCATE(MASSEY_MIDDLE_NAME,?) = 1 OR MASSEY_MIDDLE_NAME IS NULL)");
			}
			
			statementAuthor.setString(1, author.getLastName());
			statementAuthor.setString(2, author.getFirstName());
			if (authorMiddleName != null)
			{
				statementAuthor.setString(3, author.getMiddleName());
			}
			ResultSet authorRS = statementAuthor.executeQuery();
			while (authorRS.next())
			{
				authoredIt.setID(authorRS.getString(1));
				authoredIt.setType(authorRS.getString(2));
				authoredIt.setDepartment(authorRS.getString(3));
				authoredIt.setCollege(authorRS.getString(4));
				authoredIt.setAffiliation("Massey");
				authoredIt.setInDatabase(true);
			}
			if  (statementAuthor != null) statementAuthor.close();
			return authoredIt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static Boolean publicationReadStatment(Connection connection, String query){
		try {
			PreparedStatement statementPublication = connection.prepareStatement
			("SELECT PUBlICATION_DOI FROM PUBLICATION WHERE PUBLICATION_DOI = ?");
				statementPublication.setString(1, query);
				ResultSet publicationRS = statementPublication.executeQuery();
				Boolean publicationCheck = null;
				if (publicationRS.next())
					publicationCheck = true;
				else 
					publicationCheck = false;
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
