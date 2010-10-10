/*
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.bean.test;

import java.util.ArrayList;

import nz.ac.massey.rimsgroup3.metadata.bean.Author;
import nz.ac.massey.rimsgroup3.metadata.bean.Publication;
import junit.framework.TestCase;

/**
* @author Leith
*
*/
public class PublicationTest extends TestCase {
	public void testPublication() {
		Publication publication = new Publication();

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
		
		publication.setID("0123456789");
		publication.setPublicationCategory("Article");
		publication.setPublisher("Big Publisher Ltd.");
		publication.setDoi("doi:10.1000/182");
		publication.setYear("2010");
		publication.setStartPage("10");
		publication.setEndPage("20");
		publication.setIssn("1173-201X");
		publication.setAbstractText("This is a cool article");
		publication.setKeyWords(keyWords);
		publication.setUrl("http://www.google.com");
		publication.setAuthors(authors);
		
		assertEquals("0123456789", publication.getID());
		assertEquals("Article", publication.getPublicationCategory());
		assertEquals("Big Publisher Ltd.", publication.getPublisher());
		assertEquals("doi:10.1000/182", publication.getDoi());
		assertEquals("2010", publication.getYear());
		assertEquals("10", publication.getStartPage());
		assertEquals("20", publication.getEndPage());
		assertEquals("1173-201X", publication.getIssn());
		assertEquals("This is a cool article", publication.getAbstractText());
		assertEquals(keyWords, publication.getKeyWords());
		assertEquals("http://www.google.com", publication.getUrl());
		assertEquals(authors, publication.getAuthors());
		
		assertEquals(publication, publication);
	}
}
