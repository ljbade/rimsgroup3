package nz.ac.massey.rimsgroup3.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import nz.ac.massey.rimsgroup3.metadata.bean.*;

public class InsertDetails {
	
		public static void detailsToInsert(Connection connect, Publication publication) throws SQLException {
			
			Connection connection = connect;
			connection.setAutoCommit(false);
			
			List <Author> authorList = publication.getAuthors();
			
			String doi = publication.getDoi();
			
			PreparedStatement statementPublication = InsertStatements.publicationStatment(connection, doi);
			statementPublication.executeUpdate();
			if (statementPublication != null){
				statementPublication.close();
			}
		
			int i = 0;
			while (i != authorList.size())
			{
				Author author = new Author();
				author = authorList.get(i);
				if (author.getAffiliation().toLowerCase().contains("massey"))
				{
					PreparedStatement statementMasseyAuthor = InsertStatements.masseyAuthorStatement(connection, author);
					statementMasseyAuthor.executeUpdate();
					if (statementMasseyAuthor != null) {
						statementMasseyAuthor.close();
					}
				}
				else
				{
					PreparedStatement statementMiscAuthor = InsertStatements.miscAuthorStatement(connection, author);
					statementMiscAuthor.executeUpdate();
					if (statementMiscAuthor != null) {
						statementMiscAuthor.close();
					}
				}
				
				i++;
			}
			connection.commit();
			connection.setAutoCommit(true);	
			
		}

		
}
