/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leith
 *
 */
public class Book extends Publication {
	private String placePublished;
	private String chapterTitle;
	private String bookTitle;
	private List<Editor> editors; 
	private String editorsStr;
	
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
	public void setEditors(ArrayList<Editor> editors) {
		this.editors = editors;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookTitle == null) {
			if (other.bookTitle != null)
				return false;
		} else if (!bookTitle.equals(other.bookTitle))
			return false;
		if (chapterTitle == null) {
			if (other.chapterTitle != null)
				return false;
		} else if (!chapterTitle.equals(other.chapterTitle))
			return false;
		if (editors == null) {
			if (other.editors != null)
				return false;
		} else if (!editors.equals(other.editors))
			return false;
		if (placePublished == null) {
			if (other.placePublished != null)
				return false;
		} else if (!placePublished.equals(other.placePublished))
			return false;
		return true;
	}
	
	// get string version of editors
	public String getEditorsStr() {
		return editorsStr;
	}
	public void setEditorStr(String editorsStr) {
		this.editorsStr = editorsStr;
	}
}
