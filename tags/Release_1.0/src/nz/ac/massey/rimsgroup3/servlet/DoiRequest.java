package nz.ac.massey.rimsgroup3.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nz.ac.massey.rimsgroup3.Service;
import nz.ac.massey.rimsgroup3.metadata.bean.Publication;

/**
 *
 * @author Steve
 */
//@WebServlet(name="DoiRequest", urlPatterns={"/doiRequest"})
public class DoiRequest extends HttpServlet {
   
	private static final long serialVersionUID = -2950062021582777363L;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
                
        String query = request.getParameter("search").toString().trim();
        
        // local database is checked here first for already saved info
        
        // pass DOI to Crossref if local db fails
        
        Publication publication = Service.get().retrievePublication(query);        
        HttpSession session = request.getSession(true);        
        session.setAttribute("publication", publication);
            
        //redirect to response page once all done processing
        if (publication != null) {
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
        return "DOI request handler";
    }

}
