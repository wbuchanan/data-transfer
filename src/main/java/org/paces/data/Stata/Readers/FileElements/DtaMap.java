package org.paces.data.Stata.Readers.FileElements;

import org.paces.data.Stata.Version.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to store the file map object of the .dta file.
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class DtaMap {

	protected Map<Integer, Long> stmap = new HashMap<>();
	private final ElementTags etags = new ElementTags();

	DtaMap(V113 stata, Long mapOffset, Short nvars) {


	}

	DtaMap(V114 stata, Long mapOffset, Short nvars) {

	}

	DtaMap(V115 stata, Long mapOffset, Short nvars) {

	}

	DtaMap(V117 stata, Long mapOffset, Short nvars) {

	}

	DtaMap(V118 stata, Long mapOffset, Short nvars) {

	}

	public Map<Integer, Long> getOffsets() {
		return this.stmap;
	}

	public Long getOffset(Integer element) {
		return this.stmap.get(element);
	}


}
