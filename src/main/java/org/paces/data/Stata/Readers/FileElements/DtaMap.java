package org.paces.data.Stata.Readers.FileElements;

import org.paces.data.Stata.Version.V113;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to store the file map object of the .dta file.
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class DtaMap {

	Map<Integer, Long> stmap = new HashMap<Integer, Long>();

	DtaMap(V113 stata8) {


	}

}
