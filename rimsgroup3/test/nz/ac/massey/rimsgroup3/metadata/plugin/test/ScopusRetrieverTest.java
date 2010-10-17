/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.plugin.test;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

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
		//expected.setAbstractText("A Random test generator generates executable tests together with their expected results. In the form of a noise-maker, it seeds the program with conditional scheduling primitives (such as yield()) that may cause context switches. As a result different interleavings are potentially produced in different executions of the program. Determining a-priori the set of seeded locations required for a bug to manifest itself is rarely possible. This work proposes to reformulate random test generation of concurrent Java programs as a search problem. Hence, it allows applying a set of well known search techniques from the domain of AI to the input space of the test generator. By iteratively refining the input parameters fed to the test generator, the search process creates testing scenarios (i.e. interleavings) that maximizes predefined objective functions. We develop geneticFinder, a noise-maker that uses a genetic algorithm as a search method. We demonstrate our approach by maximizing two objective functions: the high manifestation rate of concurrent bugs and the exporting of a high degree of debugging information to the user. Experimental results show our approach is effective. © 2006 Elsevier B.V. All rights reserved.");
		
		Author expectedAuthor = new Author();
		expectedAuthor.setFirstName("Eytani");
		expectedAuthor.setMiddleName("");
		expectedAuthor.setLastName("Y.");
		expectedAuthor.setAffiliation("Computer Science Dept. University of Haifa");
		
		ArrayList<Author> expectedAuthors = new ArrayList<Author>();
		expectedAuthors.add(expectedAuthor);
		expected.setAuthors(expectedAuthors);
		
		HttpServletRequest request = null;
		Journal result = (Journal) retriever.retrievePublication(doi,request);
		
		assertTrue(result != null);
		assertEquals(expected, result);
	}
}
