package nz.ac.massey.rimsgroup3.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import nz.ac.massey.rimsgroup3.database.*;

import nz.ac.massey.rimsgroup3.metadata.bean.*;


/**
 * Servlet implementation class CommitRequest
 */
public class CommitRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource dataSource;
	ServletContext servletCtx;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommitRequest() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
        
    	super.init(config);
    	String db = config.getInitParameter("test");
    	servletCtx = getServletContext();
        servletCtx.log("CommitRequest Init has been invoked");
    	DatabaseConnectI datasource = new DatabaseConnection();
    	this.dataSource =  datasource.setUp(db);
    	
      }
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	response.setContentType("text/html; charset=utf-8");
    	Publication publication = new Publication();
    	
    	List<Author> authorList = new ArrayList<Author>();
    	String doi = request.getParameter("doi");
    	doi = doi.substring(doi.indexOf(":")+1);
    	publication.setDoi(doi);
    	int i = 1;
    	String counter = Integer.toString(i);
    	String id = "id" + counter;
    	String idNumber = request.getParameter(id);
    	
    	while(idNumber != null)
    	{
    		Author author = new Author();
    		author.setID(idNumber);
    		author.setFirstName(request.getParameter("fName" + counter));
    		author.setLastName(request.getParameter("lName"+ counter));
    		if(request.getParameter("mName" + counter) != "")
    		{
    			author.setMiddleName(request.getParameter("mName" + counter));
    		}
    		else
    		{
    			author.setMiddleName(null);
    		}
    		author.setAffiliation(request.getParameter("affiliation" + counter));
    		author.setType(request.getParameter("type" + counter));
    		author.setDepartment(request.getParameter("department" + counter));
    		author.setCollege(request.getParameter("college" + counter));
    		
    		
    		i++;
    		counter = Integer.toString(i);
    		id = "id" + counter;
    		idNumber = request.getParameter(id);
    		authorList.add(author);
    	}
    	
    	publication.setAuthors(authorList);
		Connection connection = null;
		
		try
		{
		synchronized (dataSource)
		{
			connection = dataSource.getConnection();
		}
			InsertDetails.detailsToInsert(connection,publication);
			response.sendRedirect("index.jsp?success=successfulCommit");
		}
		catch(Exception e)
		{
			servletCtx.log("Failed to commit to the Database",e);
			response.sendRedirect("index.jsp");
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
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
				processRequest(request, response);
			
	
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request, response);
			
	}

}
