package nz.ac.massey.rimsgroup3.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

import nz.ac.massey.rimsgroup3.metadata.bean.*;
/**
 * Servlet implementation class DatabaseSearchAuthors
 */
public class DatabaseSearchAuthors extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseSearchAuthors() {
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
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		
		HttpSession publicationDOI = request.getSession();
		List<Author> authorList = (List<Author>) publicationDOI.getAttribute("publicationAuthors");
		List<Author> authorsDetails = new ArrayList<Author>();
		Author author = new Author();
		try {
			synchronized (dataSource)
			{		
				connection = dataSource.getConnection();
			}
			int i = 0;
			while (i != authorList.size())
			{
				
				author = authorList.get(i);
				author = ReadStatements.masseyReadStatement(connection, author);
				if (author.getInDatabase() != true)
				{
					author = ReadStatements.miscReadStatement(connection, author);
				}
				authorsDetails.add(author);
				i++;
			}
			//HttpSession session = request.getSession(true);        
		    publicationDOI.setAttribute("publicationAuthors", authorsDetails);
		} 
		catch (SQLException e) {
		// TODO Auto-generated catch block
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
