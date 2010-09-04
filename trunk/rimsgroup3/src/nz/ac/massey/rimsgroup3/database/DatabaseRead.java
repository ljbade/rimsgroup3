package nz.ac.massey.rimsgroup3.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
public class DatabaseRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseRead() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
        /* try {
           Context init = new InitialContext();
           Context ctx = (Context) init.lookup("java:comp/env");
           dataSource = (DataSource) ctx.lookup("jdbc/rimsgroup3");
          // System.out.println("win");
         }
         catch (NamingException ex) {
           throw new ServletException(
             "JNDI EXCEPTION",ex);
         }*/
     	dataSource = DatabaseConnection.setUp();
       }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		Information information = new Information();
		
		HttpSession publicationDOI = request.getSession();
		String query = publicationDOI.getAttribute("publicationDOI").toString();
		System.out.println(query);
		try {
			synchronized (dataSource)
			{		
				connection = dataSource.getConnection();
			}
			List<Author> authorRS = new ArrayList<Author>();
			Publication publicationRS = new Publication();
			Conference conferenceRS = null;
			Journal journalRS = null;
			Book bookRS = null;
			List <Editor> editorRS = null;
			
			authorRS = ReadStatements.authorReadStatement(connection);
			publicationRS = ReadStatements.publicationReadStatment(connection);
			information.setAuthors(authorRS);
			information.setPublication(publicationRS);
			
			
			String category = publicationRS.getPublicationCategory().toLowerCase();
			
			if (category == "conference")
			{
				conferenceRS = new Conference();
				conferenceRS = ReadStatements.conferenceReadStatment(connection);
				information.setConference(conferenceRS);
			}
			if (category == "journal") 
			{
				journalRS = new Journal();
				journalRS = ReadStatements.journalReadStatment(connection);
				information.setJournal(journalRS);
				
			}
			if (category == "book")
			{
				bookRS = new Book();
				editorRS = new ArrayList<Editor>();
				bookRS = ReadStatements.bookReadStatment(connection);
				editorRS = ReadStatements.editorReadStatment(connection);
				information.setBook(bookRS);
				information.setEditors(editorRS);
			}
			
			HttpSession session = request.getSession(true);        
		    session.setAttribute("information", information);
			
		    /*if (information != null)
		    {
		    	response.sendRedirect("results.jsp");
		    }*/
			
			
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
