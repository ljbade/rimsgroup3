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
	
	protected String doiType;
	protected String id;
	protected String doi;
	protected String year;
	protected String startPage, endPage;
	protected String issn;
	protected String abstractText;
	protected List<String> keyWords;
	protected String url;	
	protected List<Author> authors;
	protected String publisher;
	protected String publicationCategory;
	protected int numberOfAuthors;
	protected String source;
	
	public int getNumberOfAuthors() { 
		if (authors == null){
			return 0;
		}
		return authors.size();
	}
	public String getDoiType() {
		return doiType;
	}
	public void setDoiType(String type) {
		doiType = type;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publication other = (Publication) obj;
		if (abstractText == null) {
			if (other.abstractText != null)
				return false;
		} else if (!abstractText.equals(other.abstractText))
			return false;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (doi == null) {
			if (other.doi != null)
				return false;
		} else if (!doi.equals(other.doi))
			return false;
		if (endPage == null) {
			if (other.endPage != null)
				return false;
		} else if (!endPage.equals(other.endPage))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (issn == null) {
			if (other.issn != null)
				return false;
		} else if (!issn.equals(other.issn))
			return false;
		if (keyWords == null) {
			if (other.keyWords != null)
				return false;
		} else if (!keyWords.equals(other.keyWords))
			return false;
		if (publicationCategory == null) {
			if (other.publicationCategory != null)
				return false;
		} else if (!publicationCategory.equals(other.publicationCategory))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (startPage == null) {
			if (other.startPage != null)
				return false;
		} else if (!startPage.equals(other.startPage))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
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
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getSource() {
		return source;
	}
}
