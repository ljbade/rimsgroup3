package nz.ac.massey.rimsgroup3.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AbstractText
 */
public class AbstractText extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AbstractText() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String resource = request.getParameter("resource");
		String abstractText = null;
		try{
		URL url = new URL(resource);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        InputStream stream = connection.getInputStream();
        String streamStr = convertStreamToString(stream);
        
        // process string and return abstract (if possible)
        abstractText = processString(streamStr);
        
        // flush result to ajax function
        PrintWriter pwOut = response.getWriter();
        StringBuffer sfXml = new StringBuffer();
        response.setContentType("text/plain"); // ("text/html;charset=utf-8");
        sfXml.append(abstractText);
        pwOut.println(sfXml.toString());
        pwOut.flush();
        pwOut.close();
		} catch (Exception ex) {
			
		}
	}
	
	/*
	 * processString()
	 * takes input string representing the entire page and attempts to break it down to the abstract text
	 * input - string
	 * output - string
	 */
	public String processString(String input) {
		String result = null;
		input = input.substring(input.indexOf("Abstract")); // strip of head of input so it starts at first Abstract occurrence
		try {
			// get array of positions of "Abstract" in the page
			int[] positions = new int[7]; // guess at max number of occurrences
			int count = 0;
			String shortStr = input; // version of input sting to be manipulated
			int pos = 0; // holds true position of indexOf in string (as shortStr gets progressively shorter
			Boolean foundSome = false;
			while (shortStr.indexOf("Abstract") != -1) {
				// find the location of each 'Abstract' and keep it in the positions array
				positions[count] = shortStr.indexOf("Abstract") + pos; //pos = true position in original string
				pos = pos + shortStr.indexOf("Abstract");
				count++;
				int newPos = pos + 8;
				shortStr = input.substring(newPos); // truncate shortStr by removing the found Abstract instance - + 8 clears the actual word abstract
				foundSome = true;
	        }
			if (!foundSome) {
				// skip the rest of the processing
				return "Unable to extract abstract successfully."; 
			}
			// for each entry in positions array check for html tags near start of string that 
			// indicate entry is not abstract text (</option>, </li>, etc)
			// presence of </h is a good sign
			String test = "";
			int preferred = -1;
			for(int i = 0; i < positions.length; i++) {
				if(positions[i] != 0) {
					test = input.substring(positions[i], 20);
					if(test.contains("</h")) {
						preferred = i;
					}
					else if(test.contains("</option>") || test.contains("</li>") || test.contains("</div>")) {
						// probably poor choice - ignore
					} else {
						preferred = i;
					}
				}
			}
			// now strip out HTML tags from preferred position in positions
			if(preferred == -1) {
				return "Unable to extract abstract successfully."; 
			}
			result = input.substring(positions[preferred], 1200);
			
		} catch (Exception ex) {
			result = ex.getMessage();
		}
        return result;
	}
	
	/**
	 * converts input stream into string using string builder
	 */
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
}
