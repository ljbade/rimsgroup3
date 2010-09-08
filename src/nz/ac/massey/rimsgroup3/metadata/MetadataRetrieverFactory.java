/**
 * 
 */
package nz.ac.massey.rimsgroup3.metadata;

import java.util.ServiceLoader;

import nz.ac.massey.rimsgroup3.metadata.bean.Publication;


/**
 * This class takes a DOI and attempts to find a MetadataRetriever than can return the publication's metadata.
 * 
 * @author Leith
 *
 */
public class MetadataRetrieverFactory {
	private static MetadataRetrieverFactory ref;
	
	private ServiceLoader<MetadataRetriever> metadataRetrievers;
	
	private MetadataRetrieverFactory() {
		// Load the list of plugins found
		// Edit /rimsgroup3/WebContent/META-INF/services/nz.ac.massey.rimsgroup3.metadata.plugin.MetadataRetriever
		// to add more plugins
		metadataRetrievers = ServiceLoader.load(MetadataRetriever.class);
	}
	
	public static synchronized MetadataRetrieverFactory get() {
		if (ref == null)
			ref = new MetadataRetrieverFactory();
		
		return ref;
	}
	
	public synchronized Publication retrievePublication(String doi) {
		
		// Iterate through all the plugins
		for (MetadataRetriever metadataRetriever : metadataRetrievers) {
			
			// Ask each one to find the DOI
			Publication publication = metadataRetriever.retrievePublication(doi);
			
			// If plugin found a DOI return it
			if (publication != null)
				return publication;
			
			// Otherwise ask next one
		}
		
		// No plugin found a publication!
		return null;
	}
}
