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
	 * http://www.red5tutorials.net/index.php/Howtos:Tomcat
	 * http://java.sun.com/developer/Books/javaserverpages/tomcat/Sams-Tomcat-KS_ch09.pdf
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = null;
		Information information = (Information) request.getAttribute("info");
		
		Book book = information.getBook();
		Journal journal = information.getJournal();
		Conference conference = information.getConference();
		List <Author> authors = information.getAuthors();
		Publication publication = information.getPublication();
		List <Editor> editors = information.getEditors();
		
		try 
		{
			synchronized (dataSource)
			{
				connection = dataSource.getConnection();
			}
			connection.setAutoCommit(false);
			
			PreparedStatement statementPublication = InsertStatements.publicationStatment(connection, publication);
			statementPublication.executeUpdate();
			
			int i = 0;
			while (i != authors.size())
			{
				PreparedStatement statementAuthor = InsertStatements.authorStatement(connection, authors.get(i));
				PreparedStatement statementPublished = InsertStatements.publishedStatment(connection, publication, authors.get(i));
				statementAuthor.executeUpdate();
				statementPublished.executeUpdate();
				i++;
				if (statementAuthor != null) statementAuthor.close();
				if (statementPublished != null) statementPublished.close();
			}
			PreparedStatement statementBook = InsertStatements.bookStatment(connection, book);
			PreparedStatement statementJournal = InsertStatements.journalStatment(connection, journal);
			PreparedStatement statementConference = InsertStatements.conferenceStatment(connection, conference);
			
			//statementAuthor.executeUpdate();

			statementBook.executeUpdate();
			statementJournal.executeUpdate();
			statementConference.executeUpdate();
			
			//if (statementAuthor != null) statementAuthor.close();
			if (statementPublication != null) statementPublication.close();
			if (statementBook != null) statementBook.close();
			if (statementJournal != null) statementJournal.close();
			if (statementConference != null) statementConference.close();
			
			i = 0;
			while (i != editors.size())
			{
				PreparedStatement statementEditor = InsertStatements.editorStatment(connection, editors.get(i), book);
				statementEditor.executeUpdate();
				i++;
				if (statementEditor != null) statementEditor.close();
			}
			connection.commit();
			connection.setAutoCommit(true);
			
			/*
			List<Author> authorRS = ReadStatements.authorReadStatement(connection);
			Publication publicationRS = ReadStatements.publicationReadStatment(connection);
			Conference conferenceRS = ReadStatements.conferenceReadStatment(connection);
			Book bookRS = ReadStatements.bookReadStatment(connection);
			Journal journalRS = ReadStatements.journalReadStatment(connection);
			List<Editor> editorRS = ReadStatements.editorReadStatment(connection);
			*/
			
			/*int a = 0;
			String check = "";
			while (a != authorRS.size())
			{
				Author authorCheck = new Author();
				authorCheck = authorRS.get(a);
				check = check + authorCheck.getID();
				a++;
			}*/
			/*int b = 0;
			String checkpub = "";
			List<String> listKeyWords = publicationRS.getKeyWords();
			while (b != listKeyWords.size())
			{
				checkpub = checkpub + listKeyWords.get(b);
				b++;
			}*/
			
			
			
			response.getWriter().print("<html><head><title>Test Page</title><body><h1>Hello Peter!</h1>" +
					"" + //check +
					"" + //checkpub +
					"</body></html>");
			
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
