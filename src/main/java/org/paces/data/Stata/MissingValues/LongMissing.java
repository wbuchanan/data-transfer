package org.paces.data.Stata.MissingValues;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * This interface serves primarily as a placeholder in case future versions
 * of Stata support 64-bit integers.  The values currently populated are the
 * Stata long (Java int) type values.
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class LongMissing {

	/**
	 * Member used to store the missing and extended missing values and their
	 * corresponding string masks
	 */
	public NavigableMap<Long, String> longmask = new TreeMap<>();

	public LongMissing() {
		longMissing();
	}


	/**
	 * Method to test if a Stata long type value (a Java int) is an extended
	 * missing value or not.
	 * @param longValue An Integer value to test
	 * @return A boolean indicating if the value is an extended missing value
	 * or not.
	 */
	public Boolean isExtendedMissing(Long longValue) {
		return false;
	} // End of method declaration
	
	/***
	 * Method returning a map object containing Stata missing values and
	 * their string masks (e.g., .a - .z)
	 * @return A map object containing mapped values of Stata long types (e.g
	 * ., Java int types)
	 */
	public void longMissing() {
		longmask.put((long) 2147483621, ".");
		longmask.put((long) 2147483622, ".a");
		longmask.put((long) 2147483623, ".b");
		longmask.put((long) 2147483624, ".c");
		longmask.put((long) 2147483625, ".d");
		longmask.put((long) 2147483626, ".e");
		longmask.put((long) 2147483627, ".f");
		longmask.put((long) 2147483628, ".g");
		longmask.put((long) 2147483629, ".h");
		longmask.put((long) 2147483630, ".i");
		longmask.put((long) 2147483631, ".j");
		longmask.put((long) 2147483632, ".k");
		longmask.put((long) 2147483633, ".l");
		longmask.put((long) 2147483634, ".m");
		longmask.put((long) 2147483635, ".n");
		longmask.put((long) 2147483636, ".o");
		longmask.put((long) 2147483637, ".p");
		longmask.put((long) 2147483638, ".q");
		longmask.put((long) 2147483639, ".r");
		longmask.put((long) 2147483640, ".s");
		longmask.put((long) 2147483641, ".t");
		longmask.put((long) 2147483642, ".u");
		longmask.put((long) 2147483643, ".v");
		longmask.put((long) 2147483644, ".w");
		longmask.put((long) 2147483645, ".x");
		longmask.put((long) 2147483646, ".y");
		longmask.put((long) 2147483647, ".z");
	} // End of method declaration
	
	/**
	 * Method that returns the string mask for missing values of type int.
	 * <em>The int type in Java is equivalent to the long type in Stata </em>
	 * @param value An Integer value for which the mask should be returned
	 * @return A string containing the extended missing value mask
	 */
	public String getMask(Long value) {
		return "";
	}
	
	
}
