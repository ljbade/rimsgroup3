/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.bean.test;
import java.util.ArrayList;

import nz.ac.massey.rimsgroup3.metadata.bean.Author;
import nz.ac.massey.rimsgroup3.metadata.bean.Conference;
import junit.framework.TestCase;

/**
 * @author Leith
 *
 */
public class ConferenceTest extends TestCase {
	public void testConference() {
		Conference conference = new Conference();

		ArrayList<String> keyWords = new ArrayList<String>();
		keyWords.add("key");
		keyWords.add("words");
		
		Author author1 = new Author();		
		author1.setFirstName("John");
		author1.setMiddleName("B.");
		author1.setLastName("Doe");
		
		Author author2 = new Author();		
		author2.setFirstName("James");
		author2.setMiddleName("F.");
		author2.setLastName("Smith");
		
		ArrayList<Author> authors = new ArrayList<Author>();
		authors.add(author1);
		authors.add(author2);
		
		conference.setID("0123456789");
		conference.setPublicationCategory("Article");
		conference.setPublisher("Big Publisher Ltd.");
		conference.setDoi("doi:10.1000/182");
		conference.setYear("2010");
		conference.setStartPage("10");
		conference.setEndPage("20");
		conference.setIssn("1173-201X");
		conference.setAbstractText("This is a cool article");
		conference.setKeyWords(keyWords);
		conference.setUrl("http://www.google.com");
		conference.setAuthors(authors);
		
		assertEquals("0123456789", conference.getID());
		assertEquals("Article", conference.getPublicationCategory());
		assertEquals("Big Publisher Ltd.", conference.getPublisher());
		assertEquals("doi:10.1000/182", conference.getDoi());
		assertEquals("2010", conference.getYear());
		assertEquals("10", conference.getStartPage());
		assertEquals("20", conference.getEndPage());
		assertEquals("1173-201X", conference.getIssn());
		assertEquals("This is a cool article", conference.getAbstractText());
		assertEquals(keyWords, conference.getKeyWords());
		assertEquals("http://www.google.com", conference.getUrl());
		assertEquals(authors, conference.getAuthors());
		
		conference.setAbstractTitle("How to create an Ant buildscript");
		conference.setConferenceName("Conference on Automated Building");
		conference.setStartDate("26/09/2010");
		conference.setEndDate("03/10/2010");
		conference.setLocation("Auckland");
		
		assertEquals("How to create an Ant buildscript", conference.getAbstractTitle());
		assertEquals("Conference on Automated Building", conference.getConferenceName());
		assertEquals("26/09/2010", conference.getStartDate());
		assertEquals("03/10/2010", conference.getEndDate());
		assertEquals("Auckland", conference.getLocation());
		
		assertEquals(conference, conference);
	}
}
