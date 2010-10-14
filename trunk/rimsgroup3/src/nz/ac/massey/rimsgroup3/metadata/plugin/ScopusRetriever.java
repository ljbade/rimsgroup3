/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import nz.ac.massey.rimsgroup3.metadata.MetadataRetriever;
import nz.ac.massey.rimsgroup3.metadata.bean.Author;
import nz.ac.massey.rimsgroup3.metadata.bean.Journal;
import nz.ac.massey.rimsgroup3.metadata.bean.Publication;

/**
 * @author Leith
 *
 */
public class ScopusRetriever implements MetadataRetriever {

	/* (non-Javadoc)
	 * @see nz.ac.massey.rimsgroup3.metadata.plugin.MetadataRetriever#getName()
	 */
	@Override
	public String getName() {
		return "Scopus";
	}

	/* (non-Javadoc)
	 * @see nz.ac.massey.rimsgroup3.metadata.plugin.MetadataRetriever#retrievePublication(java.lang.String)
	 */
	@Override
	public Publication retrievePublication(String doi, HttpServletRequest request) {
		String json = performQuery(doi, request);
		// test json exists
		if(json == null) {
			return null;
		} else {
			Publication publication = decodeJson(json);
		
			return publication;
		}
	}
	
	private Publication decodeJson(String json)
	{
		if (json.isEmpty()) {
			return null;
		}
		
		json = json.substring(json.indexOf("(") + 1, json.lastIndexOf(")"));
		
		if (json.isEmpty()) {
			return null;
		}
		
		Gson gson = new Gson();
		
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		if (jsonObj == null || jsonObj.PartOK == null || jsonObj.PartOK.Results.length == 0)
		{
			return null;
		}
		JsonResult result = jsonObj.PartOK.Results[0];
		
		if (!result.doctype.equals("ar") && !result.doctype.equals("ip") && !result.doctype.equals("bz")) {
			return null;	
		}
		
		Journal journal = new Journal();
		journal.setAbstractText(StringEscapeUtils.unescapeHtml(result.abstractString));
		journal.setArticleTitle(StringEscapeUtils.unescapeHtml(result.title));
		journal.setDoi(StringEscapeUtils.unescapeHtml(result.doi));
		if (result.page != null && !result.page.equals("null"))
		{
			journal.setStartPage(StringEscapeUtils.unescapeHtml(result.page).split(("-"))[0]);
			journal.setEndPage(StringEscapeUtils.unescapeHtml(result.page).split(("-"))[1]);
		}
		journal.setIssn(StringEscapeUtils.unescapeHtml(result.issn));
		journal.setIssue(StringEscapeUtils.unescapeHtml(result.issue));
		journal.setJournalTitle(StringEscapeUtils.unescapeHtml(result.sourcetitle));
		journal.setYear(StringEscapeUtils.unescapeHtml(result.pubdate));
		journal.setUrl(StringEscapeUtils.unescapeHtml(result.inwardurl));
		journal.setVolume(StringEscapeUtils.unescapeHtml(result.vol));
		

		ArrayList<Author> authors = new ArrayList<Author>();
		Author author = new Author();
		
		if (result.firstauth != null) {
			String[] parts = result.firstauth.split(", ");
			
			if (parts.length < 2) {
				author.setFirstName("");
				author.setMiddleName("");
				author.setLastName("");
				author.setAffiliation("");
			} else if (parts.length < 3) {
				author.setFirstName(parts[0]);
				author.setMiddleName("");
				author.setLastName(parts[1]);
			} else {
				author.setFirstName(parts[0]);
				author.setMiddleName(parts[1]);
				author.setLastName(parts[parts.length - 1]);
			}
			
			author.setAffiliation(result.affiliations);
		} else {
			author.setFirstName("");
			author.setMiddleName("");
			author.setLastName("");
			author.setAffiliation("");
		}
		
		authors.add(author);
		journal.setAuthors(authors);
		
		return journal;
	}
	
	static class JsonObject
	{
		public JsonPartOK PartOK;
	}
	
	static class JsonPartOK
	{		
		public int TotalResults;
		public int ReturnedResults;
		public int Position;
		public JsonResult Results[];
		public String Warnings[];
	}
	
	static class JsonResult
	{
		public String title;
		public String doctype;
		public String citedbycount;
		public String issn;
		public String vol;
		public String issue;
		public String page;
		public String eid;
		public String scp;
		public String doi;
		public String authlist;
		public String affiliations;
		@SerializedName("abstract")	public String abstractString;
		public String firstauth;
		public String pubdate;
		public String sourcetitle;
		public String inwardurl;
	}
	
	
	
	/**
	 * Takes the DOI and fetches the JSON description for it from Scopus.
	 * 
	 * @param doi the DOI to fetch
	 * @return the JSON to parse
	 */
	private String performQuery(String doi, HttpServletRequest request)
	{
		InputStream stream;
		// retrieve scopus API devId from configuration.properties file
		Properties config = new Properties();
		String devId = null;
		try {
			config.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
			devId = config.getProperty("scopusDevId");
		} catch (Exception ex) {
			// need to send back error message - possibly redirecting to help file, anchored to solution?
		}
		if (devId.length() < 1) {
			String err = "A Scopus developer ID does not exist in the configuration.properties file. Please check the help file for the resolution.";
			
			HttpSession session = request.getSession(true);   
         	session.setAttribute("error", err);        	 
			return null;
		}
		String queryString = "http://searchapi.scopus.com/search.url?preventCache=HtiAvheNOPEiOU3Ecpkq&devId=" + devId + "&search=DOI(" + doi + ")&callback=rimsgroup3" +
		"&fields=title,doctype,citedbycount,inwardurl,sourcetitle,issn,vol,issue,page,pubdate,eid,scp,doi,firstauth,authlist,affiliations,abstract";
		try {
			// Build the query string
			URL url = new URL(String.format(queryString, doi));			

			// Do the query
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.connect();
	        stream = connection.getInputStream();
			
	        return convertStreamToString(stream);
	        
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// from http://www.kodejava.org/examples/266.html
    public String convertStreamToString(InputStream is) throws IOException {
        /*
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
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
}
