package org.paces.data.Stata.MissingValues;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class FloatMissing {

	/**
	 * Member used to store the missing and extended missing values and their
	 * corresponding string masks
	 */
	public NavigableMap<Float, String> floatmask = new TreeMap<>();

	public FloatMissing() {
		floatMissing();
	}


	/**
	 * Method to test if a Stata long type value (a Java int) is an extended
	 * missing value or not.
	 * @param value A Float value to test
	 * @return A boolean indicating if the value is an extended missing value
	 * or not.
	 */
	public Boolean isExtendedMissing(Float value) {
		
		// If the value is between the .a and .z values return true
		// If not return false
		return value >= (long) 1.7014118E38 && value <= (long) 1.7122118E38;
		
	} // End of method declaration
	
	/***
	 * Method returning a map object containing Stata missing values and
	 * their string masks (e.g., .a - .z)
	 * @return A map object containing mapped values of Stata long types (e.g
	 * ., Java int types)
	 */
	public void floatMissing() {
		floatmask.put(1.7014118E38F, ".");
		floatmask.put(1.7018272E38F, ".a");
		floatmask.put(1.7022426E38F, ".b");
		floatmask.put(1.702658E38F, ".c");
		floatmask.put(1.7030734E38F, ".d");
		floatmask.put(1.7034888E38F, ".e");
		floatmask.put(1.7039041E38F, ".f");
		floatmask.put(1.7043195E38F, ".g");
		floatmask.put(1.704735E38F, ".h");
		floatmask.put(1.7051503E38F, ".i");
		floatmask.put(1.7055657E38F, ".j");
		floatmask.put(1.705981E38F, ".k");
		floatmask.put(1.7063964E38F, ".l");
		floatmask.put(1.7068118E38F, ".m");
		floatmask.put(1.7072272E38F, ".n");
		floatmask.put(1.7076426E38F, ".o");
		floatmask.put(1.708058E38F, ".p");
		floatmask.put(1.7084734E38F, ".q");
		floatmask.put(1.7088887E38F, ".r");
		floatmask.put(1.7093041E38F, ".s");
		floatmask.put(1.7097195E38F, ".t");
		floatmask.put(1.7101349E38F, ".u");
		floatmask.put(1.7105503E38F, ".v");
		floatmask.put(1.7109657E38F, ".w");
		floatmask.put(1.711381E38F, ".x");
		floatmask.put(1.7117964E38F, ".y");
		floatmask.put(1.7122118E38F, ".z");
	} // End of method declaration
	
	/**
	 * Method that returns the string mask for missing values of type float.
	 * Unlike the extended missing value types for whole numbers, floating
	 * point missing values are defined by a range of possible floating point
	 * values.  The range can be expressed roughly as n_[1] = [n_[1], n_[2]) or
	 * the minimum value for the extended missing value up to - but excluding
	 * - the next extended missing value.
	 * @param value A Float value for which the mask should be returned
	 * @return A string containing the extended missing value mask
	 */
	public String getMask(Float value) {
		return floatmask.get(floatmask.floorKey(value));
	}
	
}
