/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.bean.test;

import java.util.ArrayList;

import nz.ac.massey.rimsgroup3.metadata.bean.Author;
import nz.ac.massey.rimsgroup3.metadata.bean.Journal;
import junit.framework.TestCase;

/**
 * @author Leith
 *
 */
public class JournalTest extends TestCase {
	public void testJournal() {
		Journal journal = new Journal();

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
		
		journal.setID("0123456789");
		journal.setPublicationCategory("Article");
		journal.setPublisher("Big Publisher Ltd.");
		journal.setDoi("doi:10.1000/182");
		journal.setYear("2010");
		journal.setStartPage("10");
		journal.setEndPage("20");
		journal.setIssn("1173-201X");
		journal.setAbstractText("This is a cool article");
		journal.setKeyWords(keyWords);
		journal.setUrl("http://www.google.com");
		journal.setIsQualityAssured(true);
		journal.setAuthors(authors);
		
		assertEquals("0123456789", journal.getID());
		assertEquals("Article", journal.getPublicationCategory());
		assertEquals("Big Publisher Ltd.", journal.getPublisher());
		assertEquals("doi:10.1000/182", journal.getDoi());
		assertEquals("2010", journal.getYear());
		assertEquals("10", journal.getStartPage());
		assertEquals("20", journal.getEndPage());
		assertEquals("1173-201X", journal.getIssn());
		assertEquals("This is a cool article", journal.getAbstractText());
		assertEquals(keyWords, journal.getKeyWords());
		assertEquals("http://www.google.com", journal.getUrl());
		assertEquals(true, journal.getIsQualityAssured());
		
		assertEquals("John B. Doe, James F. Smith, ", journal.getAuthorsString());
		
		journal.setVolume("4");
		journal.setIssue("10");
		journal.setJournalTitle("Journal of Java Programming");
		journal.setArticleTitle("Writing Java Test Cases");
		
		assertEquals("4", journal.getVolume());
		assertEquals("10", journal.getIssue());
		assertEquals("Journal of Java Programming", journal.getJournalTitle());
		assertEquals("Writing Java Test Cases", journal.getArticleTitle());
	}
}
