package org.paces.data.Stata.MissingValues;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class IntegerMissing {

	/**
	 * Member used to store the missing and extended missing values and their
	 * corresponding string masks
	 */
	public NavigableMap<Integer, String> intmask = new TreeMap<>();

	public IntegerMissing() {
		intMissing();
	}


	/**
	 * Method to test if a Stata long type value (a Java int) is an extended
	 * missing value or not.
	 * @param value An Integer value to test
	 * @return A boolean indicating if the value is an extended missing value
	 * or not.
	 */
	public Boolean isExtendedMissing(Integer value) {
		
		// If the value is between the .a and .z values return true
		// If not return false
		return value >= 2147483621 && value <= 2147483647;
		
	} // End of method declaration
	
	/***
	 * Method returning a map object containing Stata missing values and
	 * their string masks (e.g., .a - .z)
	 * @return A map object containing mapped values of Stata long types (e.g
	 * ., Java int types)
	 */
	public void intMissing() {
		intmask.put((int) 2147483621, ".");
		intmask.put((int) 2147483622, ".a");
		intmask.put((int) 2147483623, ".b");
		intmask.put((int) 2147483624, ".c");
		intmask.put((int) 2147483625, ".d");
		intmask.put((int) 2147483626, ".e");
		intmask.put((int) 2147483627, ".f");
		intmask.put((int) 2147483628, ".g");
		intmask.put((int) 2147483629, ".h");
		intmask.put((int) 2147483630, ".i");
		intmask.put((int) 2147483631, ".j");
		intmask.put((int) 2147483632, ".k");
		intmask.put((int) 2147483633, ".l");
		intmask.put((int) 2147483634, ".m");
		intmask.put((int) 2147483635, ".n");
		intmask.put((int) 2147483636, ".o");
		intmask.put((int) 2147483637, ".p");
		intmask.put((int) 2147483638, ".q");
		intmask.put((int) 2147483639, ".r");
		intmask.put((int) 2147483640, ".s");
		intmask.put((int) 2147483641, ".t");
		intmask.put((int) 2147483642, ".u");
		intmask.put((int) 2147483643, ".v");
		intmask.put((int) 2147483644, ".w");
		intmask.put((int) 2147483645, ".x");
		intmask.put((int) 2147483646, ".y");
		intmask.put((int) 2147483647, ".z");
	} // End of method declaration
	
	/**
	 * Method that returns the string mask for missing values of type int.
	 * <em>The int type in Java is equivalent to the long type in Stata </em>
	 * @param value An Integer value for which the mask should be returned
	 * @return A string containing the extended missing value mask
	 */
	public String getMask(Integer value) {
		return intmask.get(value);
	}


}
