/**
 *  
 */
package nz.ac.massey.rimsgroup3.metadata.bean;

import java.util.List;

/**
 * @author Leith
 *
 */
public class Publication {
	
	protected String id;
	protected String doi;
	protected String year;
	protected String startPage, endPage;
	protected String issn;
	protected String abstractText;
	protected List<String> keyWords;
	protected String url;	
	protected boolean isQualityAssured;
	protected List<Author> authors;
	protected String publisher;
	protected String publicationCategory;
	protected int numberOfAuthors;
	
	public void setNumberOfAuthors(int numberOfAuthors) {
		this.numberOfAuthors = numberOfAuthors;
	}

	public int getNumberOfAuthors() {
		return numberOfAuthors;
	}

	/**
	 * @return the id
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setID(String id) {
		this.id = id;
	}
	
	/**
	 * @return the publisher
	 */
	public String getPublicationCategory() {
		return publicationCategory;
	}
	
	/**
	 * @param publicationCategory the publicationCategroy to set
	 */
	public void setPublicationCategory(String publicationCategory) {
		this.publicationCategory = publicationCategory;
	}
	
	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 *  @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * @return the id
	 */
	public String getDoi() {
		return doi;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setDoi(String id) {
		this.doi = id;
	}
	
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * @return the startPage
	 */
	public String getStartPage() {
		return startPage;
	}
	
	/**
	 * @param startPage the startPage to set
	 */
	public void setStartPage(String startPage) {
		this.startPage = startPage;
	}
	
	/**
	 * @return the endPage
	 */
	public String getEndPage() {
		return endPage;
	}
	
	/**
	 * @param endPage the endPage to set
	 */
	public void setEndPage(String endPage) {
		this.endPage = endPage;
	}
	
	/**
	 * @return the issnIsbn
	 */
	public String getIssn() {
		return issn;
	}
	
	/**
	 * @param issnIsbn the issnIsbn to set
	 */
	public void setIssn(String issnIsbn) {
		this.issn = issnIsbn;
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
	public boolean getIsQualityAssured() {
		return isQualityAssured;
	}
	
	/**
	 * @param isQualityAssured the isQualityAssured to set
	 */
	public void setIsQualityAssured(boolean isQualityAssured) {
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
	
	public String getAuthorsString() {
		StringBuilder authorsString = new StringBuilder();
		
		for (Author author : authors) {
			authorsString.append(author.getFirstName() + " " 
					+ author.getMiddleName() + (author.getMiddleName().length() != 0 ? " " : "") 
						+ author.getLastName() + ", ");
		}
		
		return authorsString.toString();
	}
	
}
