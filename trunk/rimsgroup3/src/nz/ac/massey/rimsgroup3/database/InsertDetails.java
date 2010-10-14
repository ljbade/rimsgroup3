package nz.ac.massey.rimsgroup3.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import nz.ac.massey.rimsgroup3.metadata.bean.*;

public class InsertDetails {
		
		/**
		 * Inserts the publication details into the database.
		 * Specifically the DOI and the the Authors details that have been placed 
		 * @param connect
		 * @param publication
		 * @throws SQLException
		 */
		public static void detailsToInsert(Connection connect, Publication publication) throws SQLException {
			
			Connection connection = connect;
			//disables autoCommit, to reduce errors
			connection.setAutoCommit(false);
			
			List <Author> authorList = publication.getAuthors();
			String doi = publication.getDoi();
			// insert publication DOI to Publication table
			PreparedStatement statementPublication = InsertStatements.publicationStatment(connection, doi);
			if (statementPublication != null){
				statementPublication.executeUpdate();
				statementPublication.close();
			}
		
			int i = 0;
			while (i != authorList.size())
			{
				Author author = new Author();
				author = authorList.get(i);
				// If it contains massey insert into Massey Table 
				if (author.getAffiliation().toLowerCase().contains("massey"))
				{
					PreparedStatement statementMasseyAuthor = InsertStatements.masseyAuthorStatement(connection, author);
					if (statementMasseyAuthor != null) {
						statementMasseyAuthor.executeUpdate();
						statementMasseyAuthor.close();
					}
				}
				// Else commit to the Misc Table
				else
				{
					PreparedStatement statementMiscAuthor = InsertStatements.miscAuthorStatement(connection, author);
					
					if (statementMiscAuthor != null) {
						statementMiscAuthor.executeUpdate();
						statementMiscAuthor.close();
					}
				}
				
				i++;
			}
			//commits all stored statements to the DB, re-enables auto commit. 
			connection.commit();
			connection.setAutoCommit(true);	
			
		}

		
}
