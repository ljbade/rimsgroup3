package nz.ac.massey.rimsgroup3.metadata.bean;

import java.util.List;

public class Information {
	
	private List<Author> authors;
	private List<Editor> editors;
	private Conference conference;
	private Book book;
	private Journal journal;
	private Publication publication;
	
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
		Information other = (Information) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (conference == null) {
			if (other.conference != null)
				return false;
		} else if (!conference.equals(other.conference))
			return false;
		if (editors == null) {
			if (other.editors != null)
				return false;
		} else if (!editors.equals(other.editors))
			return false;
		if (journal == null) {
			if (other.journal != null)
				return false;
		} else if (!journal.equals(other.journal))
			return false;
		if (publication == null) {
			if (other.publication != null)
				return false;
		} else if (!publication.equals(other.publication))
			return false;
		return true;
	}

	/**
	 * @return the list of authors
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
	
	/**
	 * @return the list of editors
	 */
	public List<Editor> getEditors(){
		return editors;
	}
	
	/**
	 * @param editors the editors to set
	 */
	public void setEditors(List<Editor> editors){
		this.editors = editors;
	}
	
	/**
	 * @return conference
	 */
	public Conference getConference() {
		return conference;
	}
	
	/**
	 * @param conference the conference to set
	 */
	public void setConference(Conference conference) {
		this.conference = conference;
	}
	
	/**
	 * @return book
	 */
	public Book getBook() {
		return book;
	}
	
	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}
	
	/**
	 * @return journal
	 */
	public Journal getJournal() {
		return journal;
	}
	
	/**
	 * @param journal the journal to set
	 */
	public void setJournal(Journal journal){
		this.journal = journal;
	}
	
	/**
	 * @return publication
	 */
	public Publication getPublication() {
		return publication;
	}
	
	/**
	 * @param publication the publication to set
	 */
	public void setPublication(Publication publication){
		this.publication = publication;
	}
	

}
