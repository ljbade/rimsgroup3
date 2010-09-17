package nz.ac.massey.rimsgroup3.database;

import java.io.IOException;
import nz.ac.massey.rimsgroup3.metadata.bean.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.*;

import java.util.*;
import java.sql.*;

/**
 * Servlet implementation class DBtest
 */
public class DatabaseInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseInsert() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
       
    	super.init(config);
    	String db = config.getInitParameter("test");
    	DatabaseConnection datasource = new DatabaseConnection();
    	this.dataSource =  datasource.setUp(db);
    	
      }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * http://www.red5tutorials.net/index.php/Howtos:Tomcat
	 * http://java.sun.com/developer/Books/javaserverpages/tomcat/Sams-Tomcat-KS_ch09.pdf
	 */
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = null;
		HttpSession insertInformation = request.getSession();
		//Information information = (Information) insertInformation.getAttribute("info");
		String doi = insertInformation.getAttribute("publicationDOI").toString();
		List <Author> authorList = (List<Author>) insertInformation.getAttribute("publicationAuthors");
		//Publication publication = information.getPublication();
		Author author = new Author();
		
		
		try 
		{
			synchronized (dataSource)
			{
				connection = dataSource.getConnection();
			}
			connection.setAutoCommit(false);
			
			PreparedStatement statementPublication = InsertStatements.publicationStatment(connection, doi);
			statementPublication.executeUpdate();
			if (statementPublication != null){
				statementPublication.close();
			}
			int i = 0;
			while (i != authorList.size())
			{
				author = authorList.get(i);
				if(author.getInDatabase() == false)
				{
				if (author.getUniversity().toLowerCase().contains("massey"))
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
				}
				i++;
			}
			connection.commit();
			connection.setAutoCommit(true);	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			//connection.rollback();
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
