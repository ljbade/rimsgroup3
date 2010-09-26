/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.bean.test;

import nz.ac.massey.rimsgroup3.metadata.bean.Person;
import junit.framework.TestCase;

/**
* @author Leith
*
*/
public class PersonTest extends TestCase {

	public void testPerson() {
		Person person = new Person();
		
		person.setID("0123456789");
		person.setFirstName("John");
		person.setMiddleName("B.");
		person.setLastName("Doe");
		
		assertEquals("0123456789", person.getID());
		assertEquals("John", person.getFirstName());
		assertEquals("B.", person.getMiddleName());
		assertEquals("Doe", person.getLastName());
	}
}
