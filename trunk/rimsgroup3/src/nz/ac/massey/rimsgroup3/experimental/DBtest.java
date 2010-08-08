package nz.ac.massey.rimsgroup3.experimental;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
/**
 * Servlet implementation class DBtest
 */
public class DBtest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBtest() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
        try {
          Context init = new InitialContext();
          Context ctx = (Context) init.lookup("java:comp/env");
          dataSource = (DataSource) ctx.lookup("jdbc/rimsgroup3");
          System.out.println("win");
        }
        catch (NamingException ex) {
          throw new ServletException(
            "JNDI EXCEPTION",ex);
        }
      }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * http://www.red5tutorials.net/index.php/Howtos:Tomcat
	 * http://java.sun.com/developer/Books/javaserverpages/tomcat/Sams-Tomcat-KS_ch09.pdf
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = null;
		try 
		{
			//synchronized (dataSource) {
				//connection = dataSource.getConnection();
			//}
			//Statement statement = connection.createStatement();
			response.getWriter().print("<html><head><title>Test Page</title><body><h1>Hello Peter!</h1></body></html>");
			//statement.executeUpdate("INSERT INTO AUTHOR " + "VALUES(1,'Julia','Smith','Sandra','Student','SEAT','madeup@hotmail.com')");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				if (connection != null)
				{
					connection.close();
				}
			}
			catch (SQLException e)
			{
				System.err.println(e.getMessage());
			}
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
