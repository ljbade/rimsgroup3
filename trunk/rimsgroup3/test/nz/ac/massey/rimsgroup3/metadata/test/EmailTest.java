/*
 * Test case for Email class
 */

package nz.ac.massey.rimsgroup3.metadata.test;
import junit.framework.TestCase;
import nz.ac.massey.rimsgroup3.email.Email;
/**
 *
 * @author Steve
 */
public class EmailTest extends TestCase {
    Email email;

    public void setUp() {
        email = new Email();
    }

    public void testEmail() {
        String to = "s_allannz@yahoo.com";
        String from = "s_allannz@yahoo.com";
        String message = "Hello:)";

        Boolean emailSent;
        emailSent = email.sendEmail(to, from, message);

        assertTrue(emailSent);
    }
}
