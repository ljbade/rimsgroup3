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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import nz.ac.massey.rimsgroup3.metadata.MetadataRetriever;
import nz.ac.massey.rimsgroup3.metadata.bean.*;
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
		NodeList nodeList = null;
		try {
		nodeList = xmlDoc.getElementsByTagName("msg");
			if(nodeList.item(0).getTextContent().equals("DOI not found in CrossRef")) {
				return null;
			}
		} catch (Exception ex) {
			// index not found in nodeList
		}
		nodeList = xmlDoc.getElementsByTagName("body");
		try {
			if(nodeList.item(0).getTextContent().contains("Malformed")) {
				// checks for malformed doi error in response
				return null;
			}
		} catch (Exception ex) {
			// index not found in nodeList
		}
		Publication result = new Publication();
		
		// use unixref format
		try {
		nodeList = xmlDoc.getElementsByTagName("crossref");
		NodeList children = nodeList.item(0).getChildNodes();
		Node node;
		for(int i = 0; i < children.getLength(); i++ ) {
			node = children.item(i);
			if(node.getNodeName().equals("journal")) {
				// create journal object
				result = new Journal();
				result = parseJournal(xmlDoc, (Journal) result);
				result.setDoiType("journal");
				// exit  if
				i = children.getLength();
			} else if(node.getNodeName().equals("book")) {
				result = new Book();
				result.setDoiType("book");
				result = parseBook(xmlDoc, (Book) result);
				// exit  if
				i = children.getLength();
			} else if(node.getNodeName().equals("conference")) {
				result = new Conference();
				result.setDoiType("conference");
				result = parseConference(xmlDoc, (Conference) result);
				// exit  if
				i = children.getLength();
			}
		}
		} catch (Exception ex) {}
		
		// set up standard tags across all doi types
		result.setDoi(doi); 
		result.setUrl("http://dx.doi.org/" + doi);  
		result.setIssn(safelyGetElement(xmlDoc, "issn")); 
		result.setYear((safelyGetElement(xmlDoc, "year")));
		result.setStartPage((safelyGetElement(xmlDoc, "first_page")));
		result.setEndPage((safelyGetElement(xmlDoc, "last_page")));
		ArrayList<Author> authors = parseAuthors(xmlDoc);
		result.setAuthors(authors);
		
		return result;
	}
	
	private ArrayList<Author> parseAuthors(Document xmlDoc) {
		ArrayList<Author> authors = new ArrayList<Author>();
		
		NodeList nodeList = xmlDoc.getElementsByTagName("person_name");
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
	
	/* 
	 * parses special fields that occur in a journal article
	 * @param Document xmlDoc
	 * @param Journal result
	 * @return Journal
	 */
	private Journal parseJournal(Document xmlDoc, Journal result) {
		result.setJournalTitle(safelyGetElement(xmlDoc, "full_title"));
		result.setVolume((safelyGetElement(xmlDoc, "volume")));
		result.setIssue((safelyGetElement(xmlDoc, "issue")));
		result.setArticleTitle(safelyGetElement(xmlDoc, "title"));
		
		return result;
	}
	
	
	/* 
	 * parses special fields that occur in a book
	 * @param Document xmlDoc
	 * @param Book result
	 * @return Book
	 */
	private Book parseBook(Document xmlDoc, Book result) {
		 
		result.setEditorStr(parseEditorsToString(xmlDoc));
		result.setBookTitle(safelyGetElement(xmlDoc, "title"));
		result.setPublisher(safelyGetElement(xmlDoc, "publisher_name"));
		result.setPlacePublished(safelyGetElement(xmlDoc, "publisher_place"));
		result.setChapterTitle(safelyGetElement(xmlDoc, "chapter_title"));
		result.setIssn(safelyGetElement(xmlDoc, "isbn"));
		return result;
	}
	
	/* 
	 * parses special fields that occur in a conference
	 * @param Document xmlDoc
	 * @param Conference result
	 * @return Conference
	 */
	private Conference parseConference(Document xmlDoc, Conference result) {
		// get start and end date
		result = getConferenceDates(result, xmlDoc);
		result.setLocation(safelyGetElement(xmlDoc, "conference_location"));
		result.setConferenceName(safelyGetElement(xmlDoc, "conference_name"));
		result.setAbstractTitle(safelyGetElement(xmlDoc, "title"));
		result.setIssn(safelyGetElement(xmlDoc, "isbn"));
		return result;
	}
	
	/*
	 * takes the xmlDoc object and parses out the start and end dates of the conference - if available
	 * relevant element of xmlDoc
	 * <conference_date start_month="09" start_year="2001" start_day="30" end_year="2001" end_month="10" end_day="05"/>
	 * @param Document xmlDoc - instance of the xml response
	 * @param Conference result - conference object to hold the data
	 * @return Conference result 
	 */
	 private Conference getConferenceDates(Conference result, Document xmlDoc) {
		 try {
		 NodeList datesElem = xmlDoc.getElementsByTagName("conference_date");
		 Node node = datesElem.item(0);
		 NamedNodeMap nodeMap = node.getAttributes();
		 
		 String startDate = nodeMap.item(3).getNodeValue() + "/" + nodeMap.item(4).getNodeValue() + "/" + nodeMap.item(5).getNodeValue();
		 String endDate = nodeMap.item(0).getNodeValue() + "/" + nodeMap.item(1).getNodeValue() + "/" + nodeMap.item(2).getNodeValue();
		 result.setStartDate(startDate);
		 result.setEndDate(endDate);
		 } catch (Exception ex) {
			 result.setStartDate("");
			 result.setEndDate("");
		 }
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
		
		String queryString = "http://www.crossref.org/openurl/?id=doi:%s&noredirect=true&pid=" + email + "&format=unixref";
		// format can also be xsd_xml but this returns less usable data
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
	
	private String parseEditorsToString(Document xmlDoc) {
		String result = "";
		NodeList nodeList = xmlDoc.getElementsByTagName("person_name");
		if (nodeList == null)
			return "";
		
		for(int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			
			if (!element.getAttribute("contributor_role").equals("editor"))
				continue;
			
			Editor editor = new Editor();
			
			String givenName = safelyGetElement(element, "given_name");
			
			if (givenName.length() > 0) {
				givenName = givenName.replace(".", "");
				int middleIndex = givenName.indexOf(' ');
				if (middleIndex != -1) {
					editor.setFirstName(givenName.substring(0, middleIndex));
					editor.setMiddleName(givenName.substring(middleIndex + 1, givenName.length() - 1));
				} else {
					editor.setFirstName(givenName);
					editor.setMiddleName("");
				}
				
				editor.setLastName(safelyGetElement(element, "surname"));
			} else {
				editor.setFirstName("");
				editor.setMiddleName("");
				editor.setLastName("");
			}
			result += editor.getFirstName() + " " + editor.getMiddleName() + " " + editor.getLastName() +", ";	
		}
		return result;
	}
}
