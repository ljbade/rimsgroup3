package nz.ac.massey.rimsgroup3.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdvancedRequest
 * Processed form submit from index.jsp when the advanced search options are used
 */
public class AdvancedRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvancedRequest() {
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	// get parameters from request
    	
    	String fName = request.getParameter("fName").toString().trim();
    	String lName = request.getParameter("lName").toString().trim();
    	boolean search = advancedSearch(fName, lName);
    }
    
    public boolean advancedSearch(String fName, String lName) {
    	try {
    		// crossref complimentary uri
    		String uri1 = "http://www.crossref.org/openurl?pid=s_allannz@yahoo.com";
    		String params = "&aulast=" + lName + " " + lName;
                       
            URL url = new URL(uri1 + params);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream stream = connection.getInputStream();
            
            // call DOI object to process data
            //Boolean doiWorked = doiObj.processStream(stream);
            
            //return doiWorked;
            
    	} catch (Exception ex) {
    		return false;
    	}
    	return true;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
