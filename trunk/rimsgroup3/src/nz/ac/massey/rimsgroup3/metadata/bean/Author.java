/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.bean;

/**
 * @author Leith
 *
 */
public class Author extends Person {
	
	private String type;
	private String department, college;
	private String email;
	private String affiliation;
	private boolean inDatabase;
	
	/**
	 * @return the affiliation
	 */
	public String getAffiliation() {
		return affiliation;
	}
	
	/**
	 * @param affiliation the affiliation to set
	 */
	public void setAffiliation(String affiliation){
		this.affiliation = affiliation;
	}
	
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**
	 * @return the college
	 */
	public String getCollege() {
		return college;
	}
	
	/**
	 * @param college the college to set
	 */
	public void setCollege(String college) {
		this.college = college;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the InDatabase
	 */
	public void setInDatabase(boolean inDatabase) {
		this.inDatabase = inDatabase;
	}
	/**
	 * @param InDatabase the InDatabase to set
	 */
	public boolean getInDatabase() {
		return inDatabase;
	}
	
}
