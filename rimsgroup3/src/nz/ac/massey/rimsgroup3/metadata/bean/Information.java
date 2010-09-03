package nz.ac.massey.rimsgroup3.metadata.bean;

import java.util.List;

public class Information {
	
	private List<Author> authors;
	private List<Editor> editors;
	private Conference conference;
	private Book book;
	private Journal journal;
	private Publication publication;
	
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
