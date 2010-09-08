/**
 * 
 */
package nz.ac.massey.rimsgroup3;

import nz.ac.massey.rimsgroup3.metadata.MetadataRetrieverFactory;
import nz.ac.massey.rimsgroup3.metadata.bean.Publication;

/**
 * @author Leith
 *
 */
public class Service {
	private static Service ref;
	
	private MetadataRetrieverFactory retriever;
	
	private Service() {
		retriever = MetadataRetrieverFactory.get();
	}
	
	public static synchronized Service get() {
		if (ref == null)
			ref = new Service();
		
		return ref;
	}
	
	public Publication retrievePublication(String doi) {
		return retriever.retrievePublication(doi);
	}
}
