/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.plugin.test;

import java.util.ArrayList;

import nz.ac.massey.rimsgroup3.metadata.bean.Author;
import nz.ac.massey.rimsgroup3.metadata.bean.Journal;
import nz.ac.massey.rimsgroup3.metadata.plugin.ScopusRetriever;
import junit.framework.TestCase;

/**
 * @author Leith
 *
 */
public class ScopusRetrieverTest extends TestCase {
	ScopusRetriever retriever;
	
	public void setUp() {
		retriever = new ScopusRetriever();
	}
	
	public void testJournalDoi() {
		String doi = "10.1016/j.entcs.2006.02.004";
		Journal expected = new Journal();
		expected.setJournalTitle("Electronic Notes in Theoretical Computer Science");
		expected.setIssn("15710661");
		expected.setVolume("144");
		expected.setYear("2006");
		expected.setIssue("4 SPEC. ISS.");
		expected.setArticleTitle("Concurrent Java Test Generation as a Search Problem");
		expected.setDoi("10.1016/j.entcs.2006.02.004");
		expected.setStartPage("57");
		expected.setEndPage("72");
		expected.setUrl("http://www.scopus.com/inward/record.url?eid=2-s2.0-33646561797&partnerID=65&md5=ba5c1fe7949a1b7be72163d618b95e28");
		
		Author expectedAuthor = new Author();
		expectedAuthor.setFirstName("Eytani");
		expectedAuthor.setLastName("Y.");
		expectedAuthor.setAffiliation("Computer Science Dept. University of Haifa");
		
		ArrayList<Author> expectedAuthors = new ArrayList<Author>();
		expectedAuthors.add(expectedAuthor);
		expected.setAuthors(expectedAuthors);
		
		Journal result = (Journal) retriever.retrievePublication(doi);
		
		assertTrue(result != null);
		assertEquals(expected, result);
		
		result = (Journal) retriever.retrievePublication("askjdh lkjhsad");
		assertTrue(result == null);
	}
}
