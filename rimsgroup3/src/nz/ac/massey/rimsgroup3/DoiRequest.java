package nz.ac.massey.rimsgroup3;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        PrintWriter out = response.getWriter();
        try {
            String doiStart = "http://www.crossref.org/openurl/?id=doi:";
            String doiEnd = "&noredirect=true&pid=s_allannz@yahoo.com&format=unixref";
            String doi = request.getParameter("search").trim();
            
            URL url = new URL(doiStart + doi + doiEnd);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream stream = connection.getInputStream();
            
            // call DOI object to process data
            DOI doiObj = new DOI();
            doiObj.processStream(stream);
            
            // test doiObj data
            String output = doiObj.getString();
            out.print(output);
            
            
          } finally {
            
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

}
