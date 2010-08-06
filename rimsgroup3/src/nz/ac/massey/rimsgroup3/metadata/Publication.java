/**
 *  
 */
package nz.ac.massey.rimsgroup3.metadata;

import java.util.List;

/**
 * @author Leith
 *
 */
public abstract class Publication {
	String id;
	int year;
	int startPage, endPage;
	String issnIsbn;
	String abstractText;
	List<String> keyWords;
	String url;	
	boolean isQualityAssured;
	List<Author> authors;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * @return the startPage
	 */
	public int getStartPage() {
		return startPage;
	}
	
	/**
	 * @param startPage the startPage to set
	 */
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	/**
	 * @return the endPage
	 */
	public int getEndPage() {
		return endPage;
	}
	
	/**
	 * @param endPage the endPage to set
	 */
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	/**
	 * @return the issnIsbn
	 */
	public String getIssnIsbn() {
		return issnIsbn;
	}
	
	/**
	 * @param issnIsbn the issnIsbn to set
	 */
	public void setIssnIsbn(String issnIsbn) {
		this.issnIsbn = issnIsbn;
	}
	
	/**
	 * @return the abstractText
	 */
	public String getAbstractText() {
		return abstractText;
	}
	
	/**
	 * @param abstractText the abstractText to set
	 */
	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}
	
	/**
	 * @return the keyWords
	 */
	public List<String> getKeyWords() {
		return keyWords;
	}
	
	/**
	 * @param keyWords the keyWords to set
	 */
	public void setKeyWords(List<String> keyWords) {
		this.keyWords = keyWords;
	}
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return the isQualityAssured
	 */
	public boolean isQualityAssured() {
		return isQualityAssured;
	}
	
	/**
	 * @param isQualityAssured the isQualityAssured to set
	 */
	public void setQualityAssured(boolean isQualityAssured) {
		this.isQualityAssured = isQualityAssured;
	}
	
	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}
	
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
}
