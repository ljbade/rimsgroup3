/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.bean.test;

import nz.ac.massey.rimsgroup3.metadata.bean.Author;
import junit.framework.TestCase;

/**
* @author Leith
*
*/
public class AuthorTest extends TestCase {
	
	public void testAuthor() {
		Author author = new Author();
		
		author.setID("0123456789");
		author.setFirstName("John");
		author.setMiddleName("B.");
		author.setLastName("Doe");
		
		author.setAffiliation("Massey University");
		author.setType("Staff");
		author.setDepartment("School of Engineering and Advanced Technology");
		author.setCollege("College of Science");
		author.setEmail("jdoe@massey.ac.nz");
		author.setInDatabase(true);
		
		assertEquals("0123456789", author.getID());
		assertEquals("John", author.getFirstName());
		assertEquals("B.", author.getMiddleName());
		assertEquals("Doe", author.getLastName());
		
		assertEquals("Massey University", author.getAffiliation());
		assertEquals("Staff", author.getType());
		assertEquals("School of Engineering and Advanced Technology", author.getDepartment());
		assertEquals("College of Science", author.getCollege());
		assertEquals("jdoe@massey.ac.nz", author.getEmail());
		assertEquals(true, author.getInDatabase());
	}
}
