/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.bean;

/**
 * @author Leith
 *
 */
public class Journal extends Publication {
	private String volume;
	private String issue;
	private String journalTitle;
	private String articleTitle;

	/**
	 * @return the volume
	 */
	public String getVolume() {
		return volume;
	}
	
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	/**
	 * @return the issue
	 */
	public String getIssue() {
		return issue;
	}
	
	/**
	 * @param issue the issue to set
	 */
	public void setIssue(String issue) {
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
		Journal other = (Journal) obj;
		if (articleTitle == null) {
			if (other.articleTitle != null)
				return false;
		} else if (!articleTitle.equals(other.articleTitle))
			return false;
		if (issue == null) {
			if (other.issue != null)
				return false;
		} else if (!issue.equals(other.issue))
			return false;
		if (journalTitle == null) {
			if (other.journalTitle != null)
				return false;
		} else if (!journalTitle.equals(other.journalTitle))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}
}
