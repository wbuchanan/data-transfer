package org.paces.data.Stata.MissingValues;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class DoubleMissing {

	/**
	 * Member used to store the missing and extended missing values and their
	 * corresponding string masks
	 */
	public NavigableMap<Double, String> dblmask = new TreeMap<>();

	public DoubleMissing() {
		doubleMissing();
	}


	/**
	 * Method to test if a Stata long type value (a Java int) is an extended
	 * missing value or not.
	 * @param value An Integer value to test
	 * @return A boolean indicating if the value is an extended missing value
	 * or not.
	 */
	public Boolean isExtendedMissing(Double value) {
		
		// If the value is between the .a and .z values return true
		// If not return false
		return value >= 8.98846567431158E307 && value <= 9.045521364627034E307;
		
	} // End of method declaration
	
	/***
	 * Method returning a map object containing Stata missing values and
	 * their string masks (e.g., .a - .z)
	 * @return A map object containing mapped values of Stata long types (e.g
	 * ., Java int types)
	 */
	public void doubleMissing() {
		dblmask.put(8.98846567431158E307D, ".");
		dblmask.put(8.990660123939097E307D, ".a");
		dblmask.put(8.992854573566614E307D, ".b");
		dblmask.put(8.995049023194132E307D, ".c");
		dblmask.put(8.99724347282165E307D, ".d");
		dblmask.put(8.999437922449167E307D, ".e");
		dblmask.put(9.001632372076684E307D, ".f");
		dblmask.put(9.003826821704202E307D, ".g");
		dblmask.put(9.00602127133172E307D, ".h");
		dblmask.put(9.008215720959237E307D, ".i");
		dblmask.put(9.010410170586754E307D, ".j");
		dblmask.put(9.012604620214272E307D, ".k");
		dblmask.put(9.01479906984179E307D, ".l");
		dblmask.put(9.016993519469307E307D, ".m");
		dblmask.put(9.019187969096824E307D, ".n");
		dblmask.put(9.021382418724342E307D, ".o");
		dblmask.put(9.02357686835186E307D, ".p");
		dblmask.put(9.025771317979377E307D, ".q");
		dblmask.put(9.027965767606894E307D, ".r");
		dblmask.put(9.030160217234412E307D, ".s");
		dblmask.put(9.03235466686193E307D, ".t");
		dblmask.put(9.034549116489447E307D, ".u");
		dblmask.put(9.036743566116964E307D, ".v");
		dblmask.put(9.038938015744481E307D, ".w");
		dblmask.put(9.041132465371999E307D, ".x");
		dblmask.put(9.043326914999516E307D, ".y");
		dblmask.put(9.045521364627034E307D, ".z");
	} // End of method declaration
	
	/**
	 * Method that returns the string mask for missing values of type double.
	 * Unlike the extended missing value types for whole numbers, floating
	 * point missing values are defined by a range of possible floating point
	 * values.  The range can be expressed roughly as n_[1] = [n_[1], n_[2]) or
	 * the minimum value for the extended missing value up to - but excluding
	 * - the next extended missing value.
	 * @param value A Double value for which the mask should be returned
	 * @return A string containing the extended missing value mask
	 */
	public String getMask(Double value) {
		return dblmask.get(dblmask.floorKey(value));
	}
	
	
}
