package nz.ac.massey.rimsgroup3.database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import nz.ac.massey.rimsgroup3.metadata.bean.*;

public class SearchAuthors {

	
	public static Publication authorsInDatabase(Connection connection, Publication publication){
			List<Author> authorList = publication.getAuthors(); 
			List<Author> authorsDetails = new ArrayList<Author>();
			int i = 0;
			while (i != authorList.size())
			{
				
				Author author = authorList.get(i);
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
