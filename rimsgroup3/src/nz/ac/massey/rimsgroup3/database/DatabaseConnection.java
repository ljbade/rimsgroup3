package nz.ac.massey.rimsgroup3.database;

import javax.servlet.ServletException;
import javax.sql.*;

import java.util.*;
import java.sql.*;
import javax.naming.*;

public class DatabaseConnection {
	private static DataSource dataSource ;
	
	public static DataSource setUp() throws ServletException
	{
		try {
			Context init = new InitialContext();
			Context ctx = (Context) init.lookup("java:comp/env");
			dataSource = (DataSource) ctx.lookup("jdbc/rimsgroup3");
       // System.out.println("win");
		}
		catch (NamingException ex) {
			throw new ServletException(
					"JNDI EXCEPTION",ex);
      }
		return dataSource;
	}

}
