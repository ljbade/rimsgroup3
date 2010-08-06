/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata;

import java.util.List;

/**
 * @author Leith
 *
 */
public class Book extends Publication {
	String placePublished;
	String chapterTitle;
	String bookTitle;
	List<Editor> editors;
	
	/**
	 * @return the placePublished
	 */
	public String getPlacePublished() {
		return placePublished;
	}
	
	/**
	 * @param placePublished the placePublished to set
	 */
	public void setPlacePublished(String placePublished) {
		this.placePublished = placePublished;
	}
	
	/**
	 * @return the chapterTitle
	 */
	public String getChapterTitle() {
		return chapterTitle;
	}
	
	/**
	 * @param chapterTitle the chapterTitle to set
	 */
	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}
	
	/**
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}
	
	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	/**
	 * @return the editors
	 */
	public List<Editor> getEditors() {
		return editors;
	}
	
	/**
	 * @param editors the editors to set
	 */
	public void setEditors(List<Editor> editors) {
		this.editors = editors;
	}
}
