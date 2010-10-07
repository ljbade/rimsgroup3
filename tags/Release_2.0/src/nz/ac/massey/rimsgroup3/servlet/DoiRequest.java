package nz.ac.massey.rimsgroup3.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import nz.ac.massey.rimsgroup3.Service;
import nz.ac.massey.rimsgroup3.database.*;
import nz.ac.massey.rimsgroup3.metadata.bean.Publication;

/**
 *
 * @author Steve
 */
//@WebServlet(name="DoiRequest", urlPatterns={"/doiRequest"})
public class DoiRequest extends HttpServlet {
   
	private static final long serialVersionUID = -2950062021582777363L;
	DataSource dataSource;
	
	  public void init(ServletConfig config) throws ServletException {
	     	super.init(config);
	    	String db = config.getInitParameter("test");
	    	DatabaseConnection datasource = new DatabaseConnection();
	    	this.dataSource =  datasource.setUp(db);
	       }


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException {
        response.setContentType("text/html; charset=utf-8");
        Connection connection = null;  
        String query = request.getParameter("search").toString().trim();
     
        // local database is checked here first for already saved info
        synchronized (dataSource)
		{		
			connection = dataSource.getConnection();
		}
        Boolean doiInDB = ReadStatements.publicationReadStatment(connection, query);
        if (doiInDB == true )
        {

        	response.sendRedirect("index.jsp?success=foundInDatabase");   	

        	// Return value.
            PrintWriter out = response.getWriter();
            out.println("DOI found");
            out.flush();

        }
        else
        {
        // pass DOI to Crossref if local db fails
        	Publication publication = Service.get().retrievePublication(query);        
           
        //redirect to response page once all done processing
        	if (publication != null) {
        		Publication detailedPublication = SearchAuthors.authorsInDatabase(connection, publication);
        		HttpSession session = request.getSession(true);   
             	session.setAttribute("publication", detailedPublication);  		
        		response.sendRedirect("results.jsp");
        	} else {
        		response.sendRedirect("index.jsp?success=no");
        	}

        }  
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

        
       /** } */

        
}
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    @Override
    public String getServletInfo() {
        return "DOI request handler";
    }

}