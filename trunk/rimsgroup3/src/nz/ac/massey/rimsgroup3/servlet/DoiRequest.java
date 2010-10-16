package nz.ac.massey.rimsgroup3.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import nz.ac.massey.rimsgroup3.database.*;
import nz.ac.massey.rimsgroup3.metadata.MetadataRetrieverFactory;
import nz.ac.massey.rimsgroup3.metadata.bean.Publication;

/**
 *
 * @author Steve
 */
//@WebServlet(name="DoiRequest", urlPatterns={"/doiRequest"})
public class DoiRequest extends HttpServlet {
   
	private static final long serialVersionUID = -2950062021582777363L;
	DataSource dataSource;
	ServletContext servletCtx;
	
	  public void init(ServletConfig config) throws ServletException {
	     	super.init(config);
	     	servletCtx = getServletContext();
	        servletCtx.log("DoiRequest Init has been invoked");
	    	String db = config.getInitParameter("test");
	    	DatabaseConnectI datasource = new DatabaseConnection();
	    	this.dataSource =  datasource.setUp(db);
	       }

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        response.setContentType("text/html; charset=utf-8");
        
        Connection connection = null;  
        Boolean doiInDB = false;
        String query = request.getParameter("search").toString().trim();
        query = query.replace(" ","");
        query = query.toLowerCase().replace("doi:","");
        query = query.replace("http://", "");
        query = query.replace("dx.doi.org/", "");
        // reset error attribute of session for new search
        HttpSession session = request.getSession(true);
        session.setAttribute("error", null);
        
        // local database is checked here first for already saved info
        try 
        {
        	synchronized (dataSource)
        	{		
        		connection = dataSource.getConnection();
        	}
        	doiInDB = ReadStatements.publicationReadStatment(connection, query);	
        }        
        catch(SQLException e)	
        {
        	 servletCtx.log("Failed reading the publication", e);
        }
        if (doiInDB == true )
        {  	
        	// Return value.
            PrintWriter out = response.getWriter();
            out.println("DOI found");
            out.flush();
            out.close();
        }
        else
        {
        // pass DOI to Crossref if local db fails
        	Publication publication = MetadataRetrieverFactory.get().retrievePublication(query,  request);        
           
        //redirect to response page once all done processing
        	if (publication != null) {
        		Publication detailedPublication = publication;
        		try {
        			
        			detailedPublication = SearchAuthors.authorsInDatabase(connection, publication);
        		}
        		catch(Exception e) {
        			servletCtx.log("Failed at Searching Authors", e);
        		}
        		session = request.getSession(true);   
             	session.setAttribute("publication", detailedPublication);  		

             	String type = detailedPublication.getDoiType();
             	if(type==null){
             		type= "journal";
             	}
             // Return value.
                PrintWriter out = response.getWriter();
                out.println("OK " + type);
                out.flush();
                out.close();

        	}  else {
        		// check for error messages
        		session = request.getSession(true);   
             	if(session.getAttribute("error") != null) {
             	response.sendRedirect("index.jsp");
             	} else {
        		// Return value.
                PrintWriter out = response.getWriter();
                out.println("DOI not found");
                out.flush();
                out.close();
             	}
        	
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
			servletCtx.log("Failed at closing the connection", e);
		}        
}
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
   
    @Override
    public String getServletInfo() {
        return "DOI request handler";
    }

}
