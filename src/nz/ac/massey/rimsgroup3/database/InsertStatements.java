package nz.ac.massey.rimsgroup3.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;



import nz.ac.massey.rimsgroup3.metadata.bean.*;



public class InsertStatements {


	public static PreparedStatement masseyAuthorStatement(Connection connection,
			Author author) {
		try {
		PreparedStatement statementMasseyAuthor = connection.prepareStatement
		("INSERT INTO MASSEY_AUTHOR VALUES(?,?,?,?,?,?,?)ON DUPLICATE KEY UPDATE MASSEY_FIRST_NAME = IF(CHAR_LENGTH" +
				"(MASSEY_FIRST_NAME) > CHAR_LENGTH(?),MASSEY_FIRST_NAME,?), MASSEY_LAST_NAME = ?, MASSEY_MIDDLE_NAME = IF " +
					"(CHAR_LENGTH(MASSEY_MIDDLE_NAME) > CHAR_LENGTH(?), MASSEY_MIDDLE_NAME,?), TYPE = ?");
						
			statementMasseyAuthor.setString(1, author.getID());
			statementMasseyAuthor.setString(2, author.getFirstName());
			statementMasseyAuthor.setString(3, author.getLastName());
			statementMasseyAuthor.setString(4, author.getMiddleName());
			statementMasseyAuthor.setString(5, author.getType());
			statementMasseyAuthor.setString(6, author.getDepartment());
			statementMasseyAuthor.setString(7, author.getCollege());
			statementMasseyAuthor.setString(8, author.getFirstName());
			statementMasseyAuthor.setString(9, author.getFirstName());
			statementMasseyAuthor.setString(10, author.getLastName());
			statementMasseyAuthor.setString(11, author.getMiddleName());
			statementMasseyAuthor.setString(12, author.getMiddleName());
			statementMasseyAuthor.setString(13, author.getType());
			return statementMasseyAuthor;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static PreparedStatement miscAuthorStatement(Connection connection,
			Author author) {
		try {
		PreparedStatement statementMiscAuthor = connection.prepareStatement
		("INSERT INTO MISC_AUTHOR VALUES(?,?,?,?,?) ON DUPLICATE KEY UPDATE MISC_FIRST_NAME = IF(CHAR_LENGTH" +
				"(MISC_FIRST_NAME) > CHAR_LENGTH(?),MISC_FIRST_NAME,?), MISC_LAST_NAME = ?, MISC_MIDDLE_NAME = IF " +
					"(CHAR_LENGTH(MISC_MIDDLE_NAME) > CHAR_LENGTH(?), MISC_MIDDLE_NAME,?), AFFILIATION  = ?"); 
			
			statementMiscAuthor.setString(1, author.getID());
			statementMiscAuthor.setString(2, author.getFirstName());
			statementMiscAuthor.setString(3, author.getLastName());
			statementMiscAuthor.setString(4, author.getMiddleName());
			statementMiscAuthor.setString(5, author.getAffiliation());
			statementMiscAuthor.setString(6, author.getFirstName());
			statementMiscAuthor.setString(7, author.getFirstName());
			statementMiscAuthor.setString(8, author.getLastName());
			statementMiscAuthor.setString(9, author.getMiddleName());
			statementMiscAuthor.setString(10, author.getMiddleName());
			statementMiscAuthor.setString(11, author.getAffiliation());
			return statementMiscAuthor;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static PreparedStatement publicationStatment(Connection connection, 
			String doi){
		try {
			PreparedStatement statementPublication = connection.prepareStatement
			("INSERT INTO PUBLICATION " + "VALUES(?)");
				statementPublication.setString(1, doi);
				return statementPublication;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
			
	}
	

	
}
