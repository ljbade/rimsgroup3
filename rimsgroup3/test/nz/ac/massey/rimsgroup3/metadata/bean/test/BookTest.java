/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.bean.test;

import java.util.ArrayList;

import nz.ac.massey.rimsgroup3.metadata.bean.Author;
import nz.ac.massey.rimsgroup3.metadata.bean.Book;
import nz.ac.massey.rimsgroup3.metadata.bean.Editor;
import junit.framework.TestCase;

/**
 * @author Leith
 *
 */
public class BookTest extends TestCase {
	public void testBook() {
		Book book = new Book();

		ArrayList<String> keyWords = new ArrayList<String>();
		keyWords.add("key");
		keyWords.add("words");
		
		Author author1 = new Author();		
		author1.setFirstName("John");
		author1.setMiddleName("B.");
		author1.setLastName("Doe");
		
		Author author2 = new Author();		
		author2.setFirstName("James");
		author2.setMiddleName("F.");
		author2.setLastName("Smith");
		
		ArrayList<Author> authors = new ArrayList<Author>();
		authors.add(author1);
		authors.add(author2);
		
		book.setID("0123456789");
		book.setPublicationCategory("Article");
		book.setPublisher("Big Publisher Ltd.");
		book.setDoi("doi:10.1000/182");
		book.setYear("2010");
		book.setStartPage("10");
		book.setEndPage("20");
		book.setIssn("1173-201X");
		book.setAbstractText("This is a cool article");
		book.setKeyWords(keyWords);
		book.setUrl("http://www.google.com");
		book.setAuthors(authors);
		
		assertEquals("0123456789", book.getID());
		assertEquals("Article", book.getPublicationCategory());
		assertEquals("Big Publisher Ltd.", book.getPublisher());
		assertEquals("doi:10.1000/182", book.getDoi());
		assertEquals("2010", book.getYear());
		assertEquals("10", book.getStartPage());
		assertEquals("20", book.getEndPage());
		assertEquals("1173-201X", book.getIssn());
		assertEquals("This is a cool article", book.getAbstractText());
		assertEquals(keyWords, book.getKeyWords());
		assertEquals("http://www.google.com", book.getUrl());
		assertEquals(authors, book.getAuthors());
		
		Editor editor1 = new Editor();		
		editor1.setFirstName("John");
		editor1.setMiddleName("B.");
		editor1.setLastName("Doe");
		
		Editor editor2 = new Editor();		
		editor2.setFirstName("James");
		editor2.setMiddleName("F.");
		editor2.setLastName("Smith");
		
		ArrayList<Editor> editors = new ArrayList<Editor>();
		editors.add(editor1);
		editors.add(editor2);
		
		book.setPlacePublished("Wellington");
		book.setChapterTitle("Servlets");
		book.setBookTitle("Java Web Apps");
		book.setEditors(editors);
		
		assertEquals("Wellington", book.getPlacePublished());
		assertEquals("Servlets", book.getChapterTitle());
		assertEquals("Java Web Apps", book.getBookTitle());
		assertEquals(editors, book.getEditors());
		
		assertEquals(book, book);
	}
}
