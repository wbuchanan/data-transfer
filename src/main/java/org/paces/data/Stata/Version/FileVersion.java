package org.paces.data.Stata.Version;

import java.util.Map;

/**
 * Interface used to construct file version objects
 * @author Billy Buchanan
 * @version 0.0.0
 */
public abstract interface FileVersion<T> {

	/**
	 * Method used to retrieve the version number
	 * @return The integer value of the release number
	 */
	public Integer getVersionNumber();

	/**
	 * Method used to retrieve the data type mappings for each file release
	 * version
	 * @return A map object that maps the data type value to the number of
	 * bytes required to store the datum of that type.
	 */
	Map<Integer, Integer> getDataTypes();

}
