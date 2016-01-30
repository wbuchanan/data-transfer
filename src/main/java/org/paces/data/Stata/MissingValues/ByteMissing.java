package org.paces.data.Stata.MissingValues;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class ByteMissing {

	/**
	 * Member used to store the missing and extended missing values and their
	 * corresponding string masks
	 */
	static NavigableMap<Byte, String> bytemask = new TreeMap<>();
	
	
	/**
	 * Method to test if a Stata byte type value (a Java byte) is an extended
	 * missing value or not.
	 * @return A boolean indicating if the value is an extended missing value
	 * or not.
	 * @param value
	 */
	public Boolean isExtendedMissing(Byte value) {
		
		// If the value is between the .a and .z values return true
		// If not return false
		return value >= 102 && value <= 127;
		
	} // End of method declaration

	public ByteMissing() {
		byteMissing();
	}

	/***
	 * Method returning a map object containing Stata missing values and
	 * their string masks (e.g., .a - .z)
	 * @return A map object containing mapped values of Stata byte types
	 */
	public void byteMissing() {
		bytemask.put((byte) 101, ".");
		bytemask.put((byte) 102, ".a");
		bytemask.put((byte) 103, ".b");
		bytemask.put((byte) 104, ".c");
		bytemask.put((byte) 105, ".d");
		bytemask.put((byte) 106, ".e");
		bytemask.put((byte) 107, ".f");
		bytemask.put((byte) 108, ".g");
		bytemask.put((byte) 109, ".h");
		bytemask.put((byte) 110, ".i");
		bytemask.put((byte) 111, ".j");
		bytemask.put((byte) 112, ".k");
		bytemask.put((byte) 113, ".l");
		bytemask.put((byte) 114, ".m");
		bytemask.put((byte) 115, ".n");
		bytemask.put((byte) 116, ".o");
		bytemask.put((byte) 117, ".p");
		bytemask.put((byte) 118, ".q");
		bytemask.put((byte) 119, ".r");
		bytemask.put((byte) 120, ".s");
		bytemask.put((byte) 121, ".t");
		bytemask.put((byte) 122, ".u");
		bytemask.put((byte) 123, ".v");
		bytemask.put((byte) 124, ".w");
		bytemask.put((byte) 125, ".x");
		bytemask.put((byte) 126, ".y");
		bytemask.put((byte) 127, ".z");
	} // End of method declaration
	
	/**
	 * Method that returns the string mask for missing values of type byte
	 * @param value A Byte value for which the mask should be returned
	 * @return A string containing the extended missing value mask
	 */
	public String getMask(Byte value) {
		return bytemask.get(value);
	}
	
	
}
