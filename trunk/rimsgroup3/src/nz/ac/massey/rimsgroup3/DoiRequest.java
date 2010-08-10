package nz.ac.massey.rimsgroup3;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

/**
 *
 * @author Steve
 */
//@WebServlet(name="DoiRequest", urlPatterns={"/doiRequest"})
public class DoiRequest extends HttpServlet {
   
	private static final long serialVersionUID = -2950062021582777363L;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
                
        String query = request.getParameter("search").toString().trim();
        DOI doiObj = new DOI();
        HttpSession session = request.getSession(true);
        session.setAttribute("doiObj", doiObj);
        doiObj.setDoi(query);
        
        // local database is checked here first for already saved info
        
        // pass DOI to Crossref if local db fails
        
        Boolean crossRefWorked = tryCrossref(query, doiObj);
            
        //redirect to response page once all done processing
        if (crossRefWorked) {
        	response.sendRedirect("results.jsp");
        } else {
        	response.sendRedirect("index.jsp?success=no");
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
        return "Short description";
    }
    
    private Boolean tryCrossref(String query, DOI doiObj) {
    	try {
            String doiStart = "http://www.crossref.org/openurl/?id=doi:";
            String doiEnd = "&noredirect=true&pid=s_allannz@yahoo.com&format=unixref";
            String doi = query;
                       
            URL url = new URL(doiStart + doi + doiEnd);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream stream = connection.getInputStream();
            
            // call DOI object to process data
            Boolean doiWorked = doiObj.processStream(stream);
            
            return doiWorked;
            
    	} catch (Exception ex) {
    		return false;
    	}
    }

}
