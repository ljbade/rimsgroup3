package nz.ac.massey.rimsgroup3.metadata.bean.test;

import nz.ac.massey.rimsgroup3.metadata.bean.Editor;
import junit.framework.TestCase;

public class EditorTest extends TestCase {
	public void testEditor() {
		Editor editor = new Editor();
		
		editor.setID("0123456789");
		editor.setFirstName("John");
		editor.setMiddleName("B.");
		editor.setLastName("Doe");
		
		assertEquals("0123456789", editor.getID());
		assertEquals("John", editor.getFirstName());
		assertEquals("B.", editor.getMiddleName());
		assertEquals("Doe", editor.getLastName());
		
		assertEquals(editor, editor);
	}
}
