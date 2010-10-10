/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.test;

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
	
	// TODO: change this to use a mock plugin?
	public void testFindDoi() {
		ArrayList<String> dois = new ArrayList<String>();
		dois.add("10.3998/3336451.0009.101");
		dois.add("10.1016/j.entcs.2006.02.004");
		
		for (String doi : dois)	{
			Publication publication = factory.retrievePublication(doi);
			
			assertTrue(publication != null);
		}
	}
}
