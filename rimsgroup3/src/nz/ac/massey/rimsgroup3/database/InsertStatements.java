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
		("INSERT INTO AUTHOR " + "VALUES(?,?,?,?,?,?,?)");
			statementMasseyAuthor.setString(1, author.getID());
			statementMasseyAuthor.setString(2, author.getFirstName());
			statementMasseyAuthor.setString(3, author.getLastName());
			statementMasseyAuthor.setString(4, author.getMiddleName());
			statementMasseyAuthor.setString(5, author.getType());
			statementMasseyAuthor.setString(6, author.getDepartment());
			statementMasseyAuthor.setString(7, author.getCollege());
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
		("INSERT INTO MISC_AUTHOR MISC_FIRST_NAME, MISC_LAST_NAME, MISC_MIDDLE_NAME, MISC_AFFILIATION " 
				+ "VALUES(?,?,?,?)");
			statementMiscAuthor.setString(1, author.getFirstName());
			statementMiscAuthor.setString(2, author.getLastName());
			statementMiscAuthor.setString(3, author.getMiddleName());
			statementMiscAuthor.setString(4, author.getUniversity());
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
