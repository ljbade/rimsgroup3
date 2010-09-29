/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata.plugin;

import nz.ac.massey.rimsgroup3.metadata.MetadataRetriever;
import nz.ac.massey.rimsgroup3.metadata.bean.Journal;
import nz.ac.massey.rimsgroup3.metadata.bean.Publication;

/**
 * @author Leith
 *
 */
public class ScopusRetriever implements MetadataRetriever {

	/* (non-Javadoc)
	 * @see nz.ac.massey.rimsgroup3.metadata.plugin.MetadataRetriever#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see nz.ac.massey.rimsgroup3.metadata.plugin.MetadataRetriever#retrievePublication(java.lang.String)
	 */
	@Override
	public Publication retrievePublication(String doi) {
		if (doi.equals("scopus"))
			return new Journal();
		
		// TODO Auto-generated method stub
		return null;
	}

}
