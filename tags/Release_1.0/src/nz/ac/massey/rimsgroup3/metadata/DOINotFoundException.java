/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata;

/**
 * This exception is thrown when a MetadataRetriever cannot find the publication matching the given DOI.
 * 
 * @see MetadataRetriever
 * 
 * @author Leith
 */
public class DOINotFoundException extends Exception {
	private String doi;
	
	public DOINotFoundException(String doi) {
		this.doi = doi;
	}
	
	public String toString()
	{
		return "The publication matching the DOI " + doi + " was not found.";
	}
}
