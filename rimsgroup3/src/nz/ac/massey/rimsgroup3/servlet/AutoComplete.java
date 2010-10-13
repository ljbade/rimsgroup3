package nz.ac.massey.rimsgroup3.servlet;

import java.io.*;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AutoComplete
 */
public class AutoComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext servletCtx;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	 public void init(ServletConfig config) throws ServletException 
	 { 
	    super.init(config);
	    servletCtx = getServletContext();
	    servletCtx.log("AutoComplete Init has been invoked");	
     }  	
	 
    public AutoComplete() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
		String targetId = request.getParameter("id");
		boolean publisherFound = false;
	    if (targetId != null) 
	    {
	    	targetId = targetId.trim().toLowerCase();
	    }
	    String path = getClass().getClassLoader().getResource("../../.").getPath();
    	path = path.replace("%20", " ");
    	String fs = System.getProperty("file.separator");
    	try 
    	{
    		InputStream publisherText = new FileInputStream(path + "publishers" + fs + "publishers.txt");
    		BufferedReader reading = new BufferedReader(new InputStreamReader(publisherText));
    		String line = null;
    		StringTokenizer tokenizer;
    		StringBuffer stringbuff = new StringBuffer();
    		while ((line = reading.readLine()) != null)
    		{
    			tokenizer = new StringTokenizer(line, ",");
    			while(tokenizer.hasMoreTokens())
    			{
    				String token = tokenizer.nextToken();
    				if (token.toLowerCase().startsWith(targetId))
    				{
    					
    					 stringbuff.append("<publisher>"); 
    					 stringbuff.append("<name>");
    		             stringbuff.append(token);
    		             stringbuff.append("</name>");
    		             stringbuff.append("</publisher>"); 
    		             publisherFound = true;
    				}
    			}
    		}
    		publisherText.close();
    		if (publisherFound == true) { 
 	           response.setContentType("text/xml"); 
 	           response.setHeader("Cache-Control", "no-cache"); 
 	           response.getWriter().write("<publishers>" + 
 	                   stringbuff.toString() + "</publishers>"); 
 	           out.close();
 	       } 
    		else { 
 	           response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
    		}
    	}
    	catch (Exception e)
        {
    		 servletCtx.log("Failed to access Publisher.txt", e);
    		 response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
        }
     
	
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
