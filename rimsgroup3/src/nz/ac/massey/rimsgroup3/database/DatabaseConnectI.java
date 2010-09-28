package nz.ac.massey.rimsgroup3.database;

import javax.servlet.ServletException;
import javax.sql.DataSource;

public interface DatabaseConnectI {

	/**
	 * @throws ServletException 
	 * Setups the database connection according to anything passed via the string.  
	 * If nothing is passed in the string, it will use the real database. 
	 */
	public DataSource setUp(String db) throws ServletException;
	
}