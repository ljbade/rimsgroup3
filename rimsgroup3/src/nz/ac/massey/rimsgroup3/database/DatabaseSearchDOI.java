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
 * Servlet implementation class DatabaseRead
 */
public class DatabaseSearchDOI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseSearchDOI() {
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
		String query = publicationDOI.getAttribute("publicationDOI").toString();
		try {
			synchronized (dataSource)
			{		
				connection = dataSource.getConnection();
			}
			Boolean doiInDatabase = null;
			doiInDatabase = ReadStatements.publicationReadStatment(connection, query);
			HttpSession session = request.getSession(true);        
		    session.setAttribute("information", doiInDatabase);
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
