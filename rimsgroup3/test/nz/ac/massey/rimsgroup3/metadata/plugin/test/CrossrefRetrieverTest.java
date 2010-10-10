/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.plugin.test;

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
		String doi = "10.3998/3336451.0009.101";
		//String doi = "10.1109/79.952804";
		Journal expected = new Journal();
		expected.setJournalTitle("Journal of Electronic Publishing");
		expected.setIssn("10802711");
		expected.setVolume("9");
		expected.setYear("2006");
		expected.setIssue("1");
		expected.setArticleTitle("In Google We Trust?");
		expected.setDoi("10.3998/3336451.0009.101");
		expected.setStartPage("");
		expected.setEndPage("");
		expected.setUrl("http://dx.doi.org/10.3998/3336451.0009.101");
		
		Journal result = (Journal) retriever.retrievePublication(doi);
		
		assertTrue(result != null);
		assertEquals(expected, result);
	}
}