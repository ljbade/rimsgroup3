/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.bean;

/**
 * @author Leith
 *
 */
public class Conference extends Publication {
	
	
	private String abstractTitle;
	private String conferenceName;
	private String startDate;
	private String endDate;
	private String location;
	
	/**
	 * @return the abstractTitle
	 */
	public String getAbstractTitle() {
		return abstractTitle;
	}
	
	/**
	 * @param abstractTitle the abstractTitle to set
	 */
	public void setAbstractTitle(String abstractTitle) {
		this.abstractTitle = abstractTitle;
	}
	
	/**
	 * @return the conferenceName
	 */
	public String getConferenceName() {
		return conferenceName;
	}
	
	/**
	 * @param conferenceName the conferenceName to set
	 */
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}
	
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
}
