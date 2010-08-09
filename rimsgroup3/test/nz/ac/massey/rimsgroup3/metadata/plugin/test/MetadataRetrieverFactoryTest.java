/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.plugin.test;

import java.util.ArrayList;

import nz.ac.massey.rimsgroup3.metadata.MetadataRetrieverFactory;
import nz.ac.massey.rimsgroup3.metadata.bean.Publication;
import junit.framework.TestCase;

/**
* @author Leith
*
*/
public class MetadataRetrieverFactoryTest extends TestCase {
	MetadataRetrieverFactory factory;
	
	public void setUp() {
		factory = MetadataRetrieverFactory.get();
	}
	
	public void testFindDoi() {
		ArrayList<String> dois = new ArrayList<String>();
		dois.add("crossref");
		dois.add("scopus");
		
		for (String doi : dois)	{
			Publication publication = factory.retrievePublication(doi);
			
			assertTrue(publication != null);
		}
	}
}
