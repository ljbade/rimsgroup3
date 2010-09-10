package nz.ac.massey.rimsgroup3.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
    	PrintWriter out = response.getWriter();
    	out.print("hello world");
    	// get parameters from request
    	String fName = request.getParameter("fName").toString().trim();
    	String lName = request.getParameter("lName").toString().trim();
    	String pubYear = request.getParameter("year").toString().trim();
    	String journal = request.getParameter("journalTitle").toString().trim();
    	String search = advancedSearch(fName, lName, pubYear, journal);
    	
    	out.print(search);
    }
    
    public String advancedSearch(String fName, String lName, String pubYear, String journal) {
    	try {
    		// crossref complimentary uri
    		String uri1 = "http://www.crossref.org/openurl?pid=s_allannz@yahoo.com";
    		String params = "&aulast=" + lName + "?title=" + journal + "?date=" + pubYear;
                       
            URL url = new URL(uri1 + params);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream stream = connection.getInputStream();
            return uri1 + params;
            // call DOI object to process data
            //Boolean doiWorked = doiObj.processStream(stream);
            
            //return doiWorked;
            
    	} catch (Exception ex) {
    		return "failed";
    	}
    	
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
