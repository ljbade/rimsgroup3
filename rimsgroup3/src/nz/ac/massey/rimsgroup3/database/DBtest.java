package nz.ac.massey.rimsgroup3.database;

import java.io.IOException;
import nz.ac.massey.rimsgroup3.metadata.bean.*;
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
         // System.out.println("win");
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
		Author author = new Author();
		
		author.setCollege("Science");
		author.setDepartment("SEAT");
		author.setEmail("madeup@hotmail.com");
		author.setFirstName("Harry");
		author.setID("4");
		author.setLastName("Sparrow");
		author.setMiddleName("Jack");
		author.setType("Student");
		
		try 
		{
			synchronized (dataSource)
			{
				connection = dataSource.getConnection();
			}
			/*PreparedStatement statementAuthor = connection.prepareStatement("INSERT INTO AUTHOR "
				+ "VALUES(?,?,?,?,?,?,?,?)");
			statementAuthor.setString(1, author.getID());
			statementAuthor.setString(2, author.getFirstName());
			statementAuthor.setString(3, author.getLastName());
			statementAuthor.setString(4, author.getMiddleName());
			statementAuthor.setString(5, author.getType());
			statementAuthor.setString(6, author.getDepartment());
			statementAuthor.setString(7, author.getCollege());
			statementAuthor.setString(8, author.getEmail()); 
			statementAuthor.executeUpdate();
			if(statementAuthor != null) statementAuthor.close(); */
			PreparedStatement statementAuthor = Statements.authorStatement(connection, author);
			statementAuthor.executeUpdate();
			response.getWriter().print("<html><head><title>Test Page</title><body><h1>Hello Peter!</h1></body></html>");
			
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
