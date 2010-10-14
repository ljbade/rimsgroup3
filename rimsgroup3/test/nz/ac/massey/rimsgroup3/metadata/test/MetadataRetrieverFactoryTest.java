/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.test;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

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
		dois.add("10.3998/3336451.0009.101");
		dois.add("10.1016/j.entcs.2006.02.004");
		
		for (String doi : dois)	{
			HttpServletRequest request = null;
			Publication publication = factory.retrievePublication(doi,request);
			
			assertTrue(publication != null);
		}
	}
}
