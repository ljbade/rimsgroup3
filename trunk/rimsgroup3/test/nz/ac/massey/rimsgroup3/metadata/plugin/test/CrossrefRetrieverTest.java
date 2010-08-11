/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.plugin.test;

import java.util.ArrayList;

import nz.ac.massey.rimsgroup3.metadata.bean.Journal;
import nz.ac.massey.rimsgroup3.metadata.plugin.CrossrefRetriever;
import junit.framework.TestCase;

/**
* @author Leith
*
*/
public class CrossrefRetrieverTest extends TestCase {
	CrossrefRetriever retriever;
	
	public void setUp() {
		retriever = new CrossrefRetriever();
	}
	
	public void testJournalDoi() {
		//String doi = "10.3998/3336451.0009.101";
		String doi = "10.1109/79.952804";
		Journal expected = new Journal();
		expected.setJournalTitle("Journal of Electronic Publishing");
		expected.setIssn("10802711");
		expected.setVolume("9");
		expected.setYear("2006");
		expected.setIssue("1");
		expected.setArticleTitle("In Google We Trust?");
		// TODO need to test other fields too!
		
		Journal result = (Journal) retriever.retrievePublication(doi);
		
		assertTrue(result != null);
		
		assertTrue(result.getJournalTitle().equals(expected.getJournalTitle()));
		assertTrue(result.getIssn().equals(expected.getIssn()));
		assertTrue(result.getVolume() == expected.getVolume());
		assertTrue(result.getYear() == expected.getYear());
		assertTrue(result.getIssue() == expected.getIssue());
		assertTrue(result.getArticleTitle().equals(expected.getArticleTitle()));
	}
}