/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import nz.ac.massey.rimsgroup3.metadata.MetadataRetriever;
import nz.ac.massey.rimsgroup3.metadata.bean.Author;
import nz.ac.massey.rimsgroup3.metadata.bean.Journal;
import nz.ac.massey.rimsgroup3.metadata.bean.Publication;

/**
 * @author Leith
 *
 */
public class CrossrefRetriever implements MetadataRetriever {
	
	/* (non-Javadoc)
	 * @see nz.ac.massey.rimsgroup3.metadata.plugin.MetadataRetriever#getName()
	 */
	@Override
	public String getName() {
		return "Crossref";
	}

	/* (non-Javadoc)
	 * @see nz.ac.massey.rimsgroup3.metadata.plugin.MetadataRetriever#retrievePublication(java.lang.String)
	 */
	@Override
	public Publication retrievePublication(String doi) {
		InputStream xml = performQuery(doi);
		return processResult(xml, doi);
	}
	
	/**
	 * Takes the XML fetched by performQuery() to create the Publication bean.
	 * 
	 * @param stream the XML to parse
	 * @return the bean filled out from the XML
	 */
	private Publication processResult(InputStream stream, String doi)
	{
		if (stream == null)
			return null;
		
		// Create an XML document object from the InputStream
        DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
        xmlFactory.setValidating(false);
        Document xmlDoc = null;
		
	    try {
	    	xmlDoc = xmlFactory.newDocumentBuilder().parse(stream);
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		// check for failed response from cross ref
		try {
		NodeList nodeList = xmlDoc.getElementsByTagName("msg");
		if(nodeList.item(0).getTextContent().equals("DOI not found in CrossRef")) {
			return null;
		}
		} catch (Exception ex) {}
		
		// Begin parsing elements of XML document
		Publication result = parseJournal(xmlDoc);
		
		// TODO: we are better off using <doi type="...">
		// but this needs expand-result="true"
		// otherwise have to use rules in XSD comment
		
		result.setDoi(doi); // TODO: it would be better to use the returned DOI...
		result.setUrl("http://dx.doi.org/" + doi); // TODO: should we use the <resource> ? 
		result.setIssn(safelyGetElement(xmlDoc, "issn")); // TODO: reformat this ?
		result.setYear((safelyGetElement(xmlDoc, "year")));
		result.setStartPage((safelyGetElement(xmlDoc, "first_page")));
		result.setEndPage((safelyGetElement(xmlDoc, "last_page")));
		ArrayList<Author> authors = parseAuthors(xmlDoc);
		result.setAuthors(authors);
		
		return result;
	}
	
	private ArrayList<Author> parseAuthors(Document xmlDoc) {
		ArrayList<Author> authors = new ArrayList<Author>();
		
		NodeList nodeList = xmlDoc.getElementsByTagName("contributor");
		if (nodeList == null)
			return authors;
		
		for(int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			
			if (!element.getAttribute("contributor_role").equals("author"))
				continue;
			
			Author author = new Author();
			
			String givenName = safelyGetElement(element, "given_name");
			
			if (givenName.length() > 0) {
				givenName = givenName.replace(".", "");
				int middleIndex = givenName.indexOf(' ');
				if (middleIndex != -1) {
					author.setFirstName(givenName.substring(0, middleIndex));
					author.setMiddleName(givenName.substring(middleIndex + 1, givenName.length() - 1));
				} else {
					author.setFirstName(givenName);
					author.setMiddleName("");
				}
				
				author.setLastName(safelyGetElement(element, "surname"));
			} else {
				author.setFirstName("");
				author.setMiddleName("");
				author.setLastName("");
			}
				
			authors.add(author);
		}
		
		return authors;
	}
	
	private Journal parseJournal(Document xmlDoc) {
		Journal result = new Journal();
		
		result.setJournalTitle(safelyGetElement(xmlDoc, "journal_title"));
		result.setVolume((safelyGetElement(xmlDoc, "volume")));
		result.setIssue((safelyGetElement(xmlDoc, "issue")));
		result.setArticleTitle(safelyGetElement(xmlDoc, "article_title"));
		
		return result;
	}
	
	/**
	 * Retrieves the element from the XML document handling the case where the element does not exist.
	 * 
	 * @param document the XML document to retrieve from
	 * @param tag the element to retrieve
	 * @return the contents of the element, or null if it was not found
	 */
	private String safelyGetElement(Document document, String tag) {
		NodeList nodeList = document.getElementsByTagName(tag);
		
		if (nodeList == null)
			return "";
		
		Node node = nodeList.item(0);
		
		if (node == null)
			return "";
		
		return node.getTextContent();
	}
	
	// TODO: clean up duplicate code
	/**
	 * Retrieves the element from the XML element handling the case where the element does not exist.
	 * 
	 * @param element the XML element to retrieve from
	 * @param tag the element to retrieve
	 * @return the contents of the element, or null if it was not found
	 */
	private String safelyGetElement(Element element, String tag) {
		NodeList nodeList = element.getElementsByTagName(tag);
		
		if (nodeList == null)
			return "";
		
		Node node = nodeList.item(0);
		
		if (node == null)
			return "";
		
		return node.getTextContent();
	}
	
	/**
	 * Takes the DOI and fetches the XML description for it from Crossref.
	 * 
	 * @param doi the DOI to fetch
	 * @return the XML to parse
	 */
	private InputStream performQuery(String doi)
	{
		InputStream stream;
		// retrieve crossref API email from configuration.properties file
		Properties config = new Properties();
		String email = null;
		try {
			config.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
			email = config.getProperty("crossrefEmail");
		} catch (Exception ex) {
			// need to send back error message - possibly redirecting to help file, anchored to solution?
		}
		
		String queryString = "http://www.crossref.org/openurl/?id=doi:%s&noredirect=true&pid=" + email + "&format=xsd_xml";
		try {
			// Build the query string
			URL url = new URL(String.format(queryString, doi));			

			// Do the query
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.connect();
	        stream = connection.getInputStream();
			
	        return stream;
	        
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
