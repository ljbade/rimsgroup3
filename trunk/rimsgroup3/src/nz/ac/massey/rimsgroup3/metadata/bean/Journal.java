/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.bean;

/**
 * @author Leith
 *
 */
public class Journal extends Publication {
	private int volume;
	private int issue;
	private String journalTitle;
	private String articleTitle;
	
	/**
	 * @return the volume
	 */
	public int getVolume() {
		return volume;
	}
	
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	/**
	 * @return the issue
	 */
	public int getIssue() {
		return issue;
	}
	
	/**
	 * @param issue the issue to set
	 */
	public void setIssue(int issue) {
		this.issue = issue;
	}
	
	/**
	 * @return the journalTitle
	 */
	public String getJournalTitle() {
		return journalTitle;
	}
	
	/**
	 * @param journalTitle the journalTitle to set
	 */
	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}
	
	/**
	 * @return the articleTitle
	 */
	public String getArticleTitle() {
		return articleTitle;
	}
	
	/**
	 * @param articleTitle the articleTitle to set
	 */
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
}
