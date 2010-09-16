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
        
        // process string version of page to find abstract
        String result = null;
        if(streamStr.contains("Abstract")) {
        	result = streamStr.substring(streamStr.indexOf("Abstract"), streamStr.indexOf("Abstract") + 1011);
        } else {
        	result = "No abstract located";
        }
        
        PrintWriter pwOut = response.getWriter();
        StringBuffer sfXml = new StringBuffer();
        response.setContentType("text/plain"); // ("text/html;charset=utf-8");
        sfXml.append(result);
        pwOut.println(sfXml.toString());
        pwOut.flush();
        pwOut.close();
		} catch (Exception ex) {
			
		}
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
