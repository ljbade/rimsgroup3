/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata;

import javax.servlet.http.HttpServletRequest;

import nz.ac.massey.rimsgroup3.metadata.bean.Publication;


/**
 * Describes a plugin that can be used to retrieve the publication metadata for a given DOI.
 * 
 * @author Leith
 */
public interface MetadataRetriever {
	/**
	 * Returns the name of the MetadataRetriever implementation
	 * @return the name
	 */
	String getName();
	
	/**
	 * Searches the database for the publication matching the given DOI and returns all the metadata found.
	 * @param doi the Document Object Identifier to search for
	 * @param request 
	 * @return the found metadata
	 */
	Publication retrievePublication(String doi, HttpServletRequest request);
}
