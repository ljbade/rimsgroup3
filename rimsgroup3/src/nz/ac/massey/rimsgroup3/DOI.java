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
        return resource;
    }
    public String getName() {
        return given_name;
    }
    public String getSurname() {
        return surname;
    }

    // return all variables in HTML formatted string
    public String getString() {
        String result = "";
        result = journal.toString() + "<br />" + abbrev.toString() + "<br />" + issn.toString() + "<br />";
        result += resource.toString() + "<br />" + month.toString() + "<br />" + day.toString() + "<br />";
        result += year.toString() + "<br />" + volume.toString() + "<br />" + issue.toString() + "<br />";
        result += title.toString() + "<br />";
        //result += given_name.toString() + "<br />" + surname.toString() + "<br />";

        return result;
    }
    

    // accept inputStream from servlet
	public void processStream(InputStream stream) {
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
		
		// begin parsing elements of xml document
        journal = d.getElementsByTagName("full_title").item(0).getTextContent();
        abbrev = d.getElementsByTagName("abbrev_title").item(0).getTextContent();;
        issn = d.getElementsByTagName("issn").item(0).getTextContent();
        resource = d.getElementsByTagName("resource").item(0).getTextContent();;
        month = d.getElementsByTagName("month").item(0).getTextContent();
        day = d.getElementsByTagName("day").item(0).getTextContent();
        year = d.getElementsByTagName("year").item(0).getTextContent();
        volume = d.getElementsByTagName("volume").item(0).getTextContent();
        issue = d.getElementsByTagName("issue").item(0).getTextContent();
        title = d.getElementsByTagName("title").item(0).getTextContent();
        
        /*
        // special  processing for catching all authors listed in XML
        NodeList authorsNL = d.getElementsByTagName("person_name");
        ArrayList<String> authors = new ArrayList<String>();
        // run through authorsNL node list and concat first and last names in arrayList
        for (int i = 0; i < authorsNL.getLength(); i++) {
        	authors.add(authorsNL.item(i).getFirstChild().getTextContent() + " " + authorsNL.item(i).getLastChild().getTextContent());
        }
        */
    
		
	}
}
