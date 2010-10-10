package nz.ac.massey.rimsgroup3.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nz.ac.massey.rimsgroup3.metadata.MetadataRetrieverFactory;
import nz.ac.massey.rimsgroup3.metadata.bean.Publication;

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
    	// all strings lower cased to make search function case insensitive
    	String lName = request.getParameter("lName").toString().trim().toLowerCase();
    	String pubYear = request.getParameter("year").toString().trim().toLowerCase();
    	String journal = request.getParameter("journalTitle").toString().trim().toLowerCase();
    	String articleTitle = request.getParameter("articleTitle").toString().trim().toLowerCase();
    	journal = journal.replaceAll(" ", "%20");
    	// advanced search creates and calls openUrl with search criteria - returns string array of returned HTML
    	String[] resultsArray = advancedSearch(lName, pubYear, journal);

        // run through array looking for doi and article title
        // if array length is less than 23 only one result was returned
    	if(resultsArray.length < 4) {
    		// no usable result returned
    		// Return value to AJAX script
            PrintWriter out = response.getWriter();
            out.println("search failed");
            out.flush();
    	}
    	else if(resultsArray.length < 24) {
        	// break string down to doi - initial string =  colspan=1 width=230><a href=http://dx.doi.org/10.3998/3336451.0009.101> doi:10.3998/3336451.0009.101</a>
            String[] subStr = resultsArray[19].split("org/"); // doi is at 19 cell in array
            subStr = subStr[1].split(">");
            
            // send DOI found in array to service class for processing
            DOIsearch(subStr[1].substring(5, subStr[1].length() - 3), request, response);  
            
        } else {
            // loop through results checking rows 19 (for doi) and 21 (for article title) for first result
            // and every 8th and 10th there after
        	int doiPos = 19;
        	int titlePos = 21;
        	
        	while (titlePos < resultsArray.length) {
        		// check title in response matches entered title - if so use DOI from matching row
        		if(resultsArray[titlePos].contains(articleTitle)) {
        			String[] subStr = resultsArray[doiPos].split("org/");
                    subStr = subStr[1].split(">");
                    
                    // send DOI found in array to service class for processing
                    titlePos = resultsArray.length; // to clear loop on return
                    DOIsearch(subStr[1].substring(5, subStr[1].length() - 3), request, response); 
        		}
        		else { // increase doiPos and titlePos to next row
        			doiPos += 11;
        			titlePos += 11;
        		}        		
        	}            
        }    	
    }
    
    // send found DOI to normal search methods
    private void DOIsearch(String doi, HttpServletRequest request, HttpServletResponse response) {
        Publication publication = MetadataRetrieverFactory.get().retrievePublication(doi);
        HttpSession session = request.getSession(true);
        session.setAttribute("publication", publication);

        //redirect to response page once all done processing
        try {
        if (publication != null) {
        	response.sendRedirect("results.jsp");
        } else {
        	response.sendRedirect("index.jsp?success=no");
        }
        } catch (Exception ex) {}
    }
    
    public String[] advancedSearch(String lName, String pubYear, String journal) {
    	try {
    		// crossref complimentary uri
    		String uri1 = "http://www.crossref.org/openurl?pid=s_allannz@yahoo.com";
    		String params = "&aulast=" + lName + "&title=" + journal + "&date=" + pubYear;
                       
            URL url = new URL(uri1 + params);
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
           
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream stream = connection.getInputStream();
            
            String streamStr = convertStreamToString(stream);
            streamStr = streamStr.toLowerCase(); // lowercase it to make search function case insensitive
            String[] splitStream;
            splitStream = streamStr.split("<td");

            return splitStream;
            
    	} catch (Exception ex) {
    		return null;
    	}
    	
    }

    public String convertStreamToString(InputStream is) throws IOException {
        /*
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder         * and returned as String.
       */
       if (is != null) {
           StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                is.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
