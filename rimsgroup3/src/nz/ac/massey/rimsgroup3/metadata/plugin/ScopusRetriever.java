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
	public Publication retrievePublication(String doi) {
		String json = performQuery(doi);
		Publication publication = decodeJson(json);
		
		return publication;
	}
	
	private Publication decodeJson(String json)
	{
		json = json.substring(json.indexOf("(") + 1, json.lastIndexOf(")"));
		
		Gson gson = new Gson();
		
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		JsonResult result = jsonObj.PartOK.Results[0];
		
		if (!result.equals("ar") && !result.equals("ip") && !result.equals("bz"))
			return null;
		
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
		
		if (result.firstauth != null)
		{
			ArrayList<Author> authors = new ArrayList<Author>();
			Author author = new Author();
			author.setFirstName(result.firstauth.split(", ")[0]);
			author.setLastName(result.firstauth.split(", ")[1]);
			author.setAffiliation(result.affiliations);
			
			authors.add(author);
			journal.setAuthors(authors);
		}
		
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
	private String performQuery(String doi)
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
