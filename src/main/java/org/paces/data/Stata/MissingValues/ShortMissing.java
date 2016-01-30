package org.paces.data.Stata.MissingValues;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class ShortMissing {
	
	/**
	 * Member used to store the missing and extended missing values and their
	 * corresponding string masks
	 */
	public NavigableMap<Short, String> shortmask = new TreeMap<>();

	public ShortMissing() {
		shortMissing();
	}


	/**
	 * Method to test if a Stata int type value (a Java short) is an extended
	 * missing value or not.
	 * @param shortValue A Short value to test
	 * @return A boolean indicating if the value is an extended missing value
	 * or not.
	 */
	public Boolean isExtendedMissing(Short shortValue) {

		// If the value is between the .a and .z values return true
		// If not return false
		return shortValue >= 32742 && shortValue <= 32767;

	} // End of method declaration

	/***
	 * Method returning a map object containing Stata missing values and
	 * their string masks (e.g., .a - .z)
	 */
	public void shortMissing() {
		shortmask.put((short) 32741, ".");
		shortmask.put((short) 32742, ".a");
		shortmask.put((short) 32743, ".b");
		shortmask.put((short) 32744, ".c");
		shortmask.put((short) 32745, ".d");
		shortmask.put((short) 32746, ".e");
		shortmask.put((short) 32747, ".f");
		shortmask.put((short) 32748, ".g");
		shortmask.put((short) 32749, ".h");
		shortmask.put((short) 32750, ".i");
		shortmask.put((short) 32751, ".j");
		shortmask.put((short) 32752, ".k");
		shortmask.put((short) 32753, ".l");
		shortmask.put((short) 32754, ".m");
		shortmask.put((short) 32755, ".n");
		shortmask.put((short) 32756, ".o");
		shortmask.put((short) 32757, ".p");
		shortmask.put((short) 32758, ".q");
		shortmask.put((short) 32759, ".r");
		shortmask.put((short) 32760, ".s");
		shortmask.put((short) 32761, ".t");
		shortmask.put((short) 32762, ".u");
		shortmask.put((short) 32763, ".v");
		shortmask.put((short) 32764, ".w");
		shortmask.put((short) 32765, ".x");
		shortmask.put((short) 32766, ".y");
		shortmask.put((short) 32767, ".z");
	} // End of method declaration

	/**
	 * Method that returns the string mask for missing values of type short.
	 * <em>The short type in Java is equivalent to the int type in Stata.</em>
	 * @param value A Short (Stata int type) value for which the mask should be
	 *                 returned
	 * @return A string containing the extended missing value mask
	 */
	public String getMask(Short value) {
		return shortmask.get(value);
	}


}
