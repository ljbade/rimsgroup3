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
 * Servlet implementation class DatabaseModify
 */
public class DatabaseModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource datasource ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseModify() {
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
     	//datasource = DatabaseConnection.setUp();
       }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession modifyInformation = request.getSession();
		Information modifyToThisInformation = (Information) modifyInformation.getAttribute("modify");
		Information originalInformation = (Information) modifyInformation.getAttribute("information");
		
		Publication origPublication = originalInformation.getPublication();
		List<Author> origAuthor = originalInformation.getAuthors();
		
		Publication newPublication = modifyToThisInformation.getPublication();
		List<Author> newAuthor = modifyToThisInformation.getAuthors();
		
		String origCategory = origPublication.getPublicationCategory();
		String newCategory = newPublication.getPublicationCategory();
		if(!origCategory.equals(newCategory))
		{
			
		}
		else
		{
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
