package nz.ac.massey.rimsgroup3.database;

import javax.servlet.ServletException;
import javax.sql.*;

import javax.naming.*;

public class DatabaseConnection implements DatabaseConnectI {
	private DataSource dataSource ;
	
	public DatabaseConnection(){
		
	}
	
	public DataSource setUp(String db) throws ServletException
	{
		try {
			Context init = new InitialContext();
			Context ctx = (Context) init.lookup("java:comp/env");
			if (db == null)
				dataSource = (DataSource) ctx.lookup("jdbc/rimsgroup3");
			else if (db.equals("datatest"))
				dataSource = (DataSource) ctx.lookup("jdbc/datatest");
			else
				dataSource = (DataSource) ctx.lookup("jdbc/swctest");
		}
		catch (NamingException ex) {
			throw new ServletException(
					"JNDI EXCEPTION",ex);
      }
		return dataSource;
	}

}
