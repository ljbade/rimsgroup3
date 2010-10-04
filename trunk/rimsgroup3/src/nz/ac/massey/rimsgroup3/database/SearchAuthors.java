package nz.ac.massey.rimsgroup3.database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import nz.ac.massey.rimsgroup3.metadata.bean.*;

public class SearchAuthors {

	/**
	 * Searches the authors in the database, 
	 * If they are in the database it updates the details, returns the same publication with
	 * updated details. 
	 * @param connection
	 * @param publication
	 * @return
	 */
	public static Publication authorsInDatabase(Connection connection, Publication publication){
			List<Author> authorList = publication.getAuthors(); 
			List<Author> authorsDetails = new ArrayList<Author>();
			int i = 0;
			while (i != authorList.size())
			{
				Author author = authorList.get(i);
				//resets middleName back to null, database is better suited with nulls. 
				if (author.getMiddleName() == ""){
					author.setMiddleName(null);
				}
				// tries the massey table first. if not in the database it tries misc
				author = ReadStatements.masseyReadStatement(connection, author);
				if (author.getInDatabase() != true)
				{
					author = ReadStatements.miscReadStatement(connection, author);
				}
				authorsDetails.add(author);
				i++;
			}
		return publication;
		
	}
	
}
