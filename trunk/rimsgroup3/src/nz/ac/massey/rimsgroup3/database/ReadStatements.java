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
	
	
	
	public static Boolean publicationReadStatment(Connection connection, String query){
		try {
			PreparedStatement statementPublication = connection.prepareStatement
			("SELECT PUBlICATION_DOI FROM PUBLICATION WHERE PUBLICATION_DOI = '" + query + "'" );
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
