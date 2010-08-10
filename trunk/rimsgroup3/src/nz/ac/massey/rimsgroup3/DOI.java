package nz.ac.massey.rimsgroup3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Steve
 */
public class DOI {
    // variables
    String journal, abbrev, issn, doi, resource, month, day, year, date, volume, issue, title, given_name, surname;
    String startPage, lastPage, authorsStr;
    ArrayList<String> authors;
    
    // constructor
    public DOI() {
               
    }

    // setters
    public void setJournal(String j) {
        journal = j;
    }
    public void setAbbrev(String a) {
        abbrev = a;
    }
    public void setIssn(String a) {
        issn = a;
    }
    public void setDoi(String d) {
        doi = d;
    }
    public void setResource(String r) {
        resource = r;
    }
    public void setMonth(String m) {
        month = m;
    }
    public void setDay(String d) {
        day = d;
    }
    public void setYear(String y) {
        year = y;
    }
    public void setVolume(String v) {
        volume = v;
    }
    public void setIssue(String i) {
        issue = i;
    }
    public void setTitle(String t) {
        title = t;
    }
    public void setName(String n) {
        given_name = n;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setStartPage(String page) {
    	startPage = page;
    }
    public void setLastPage(String page) {
    	lastPage = page;
    }
    public void setAuthorsStr(String a) {
    	authorsStr = a;
    }
    // getters
    public String getJournal() {
        return journal;
    }
    public String getAbbrev() {
        return abbrev;
    }
    public String getIssn() {
        return issn;
    }
    public String getDoi() {
        return doi;
    }
    public String getResource() {
        return resource;
    }
    public String getMonth() {
        return month;
    }
    public String getDay() {
        return day;
    }
    public String getYear() {
        return year;
    }
    public String getVolume() {
        return volume;
    }
    public String getIssue() {
        return issue;
    }
    public String getTitle() {
        return title;
    }
    public String getName() {
        return given_name;
    }
    public String getSurname() {
        return surname;
    }
    public String getStartPage() {
    	return startPage;
    }
    public String getLastPage() {
    	return lastPage;
    }
    public String getAuthorsStr() {
    	return authorsStr;
    }
    // accept inputStream from servlet
	public Boolean processStream(InputStream stream) {
		// xml document from stream object
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating( false );
        Document d = null;
		
	    try {
			d = f.newDocumentBuilder().parse(stream);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		// nl is used to catch results of getElementsByTagName and check more than zero elements exist
		NodeList nl;
		
		// check for failure of crossref - existence of <error> tag
		nl = d.getElementsByTagName("error");
		if(nl.getLength() > 0) return false; // no doi info returned
		
		 
		// begin parsing elements of xml document
        journal = d.getElementsByTagName("full_title").item(0).getTextContent();
        abbrev = d.getElementsByTagName("abbrev_title").item(0).getTextContent();
        title = d.getElementsByTagName("title").item(0).getTextContent();
        // may be more than 1 issn
        issn = d.getElementsByTagName("issn").item(0).getTextContent();
        
        // cab be two resource elements (one for the journal, one for the article)
        // so take the second if more than one exist
        nl = d.getElementsByTagName("resource");
        if(nl.getLength() > 1) {
        	resource = nl.item(1).getTextContent();
        } else resource = nl.item(0).getTextContent();
        
        month = d.getElementsByTagName("month").item(0).getTextContent();
        day = d.getElementsByTagName("day").item(0).getTextContent();
        year = d.getElementsByTagName("year").item(0).getTextContent();
        date = day + "/" + month + "/" + year;
        
     // optional elements in XML file
        nl = d.getElementsByTagName("first_page");
        if(nl.getLength() > 0) {
        	startPage = nl.item(0).getTextContent();
        } else startPage = "";
        
        nl = d.getElementsByTagName("last_page");
        if(nl.getLength() > 0) {
        	lastPage = nl.item(0).getTextContent();
        } else lastPage = "";
                
        nl = d.getElementsByTagName("volume");
        if (nl.getLength() > 0) {
        	volume = nl.item(0).getTextContent();
        } else volume = "";
        
        nl = d.getElementsByTagName("issue");
        if (nl.getLength() > 0) {
        	issue = nl.item(0).getTextContent();
        } else issue = "";
        
               
        // processing for catching all authors listed in XML
        NodeList authorsNL = d.getElementsByTagName("person_name");
        authors = new ArrayList<String>();
        // run through authorsNL node list and concat first and last names in arrayList
        for (int i = 0; i < authorsNL.getLength(); i++) {
        	if(authorsNL.item(i) != null) {
        	authors.add(authorsNL.item(i).getFirstChild().getTextContent() + " " + authorsNL.item(i).getLastChild().getTextContent());
        	authorsStr += authorsNL.item(i).getFirstChild().getTextContent() + " " + authorsNL.item(i).getLastChild().getTextContent() + ", ";
        	}
        }
        
        // all processing worked
        return true;
		
	}
}
