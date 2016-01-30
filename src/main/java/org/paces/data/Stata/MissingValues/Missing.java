package org.paces.data.Stata.MissingValues;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Missing {
	
	/**
	 * Method containing an instance of ByteMissing class
	 */
	private static ByteMissing byteval = new ByteMissing();

	/**
	 * Method containing an instance of ShortMissing class
	 */
	private static ShortMissing shortval = new ShortMissing();

	/**
	 * Method containing an instance of IntegerMissing class
	 */
	private static IntegerMissing intval = new IntegerMissing();

	/**
	 * Method containing an instance of LongMissing class
	 */
	private static LongMissing longval = new LongMissing();

	/**
	 * Method containing an instance of FloatMissing class
	 */
	private static FloatMissing floatval = new FloatMissing();

	/**
	 * Method containing an instance of DoubleMissing class
	 */
	private static DoubleMissing doubleval = new DoubleMissing();

	/**
	 * Generic class constructor
	 */
	public Missing() {
	}

	/**
	 * Method to test if a Stata byte type value (a Java byte) is an extended
	 * missing value or not.
	 *
	 * @param value A byte value to test
	 * @return A boolean indicating if the value is an extended missing value or
	 * not.
	 */
	public Boolean isExtendedMissing(Byte value) {
		return byteval.isExtendedMissing(value);
	}
	
	/**
	 * Method to test if a Stata int type value (a Java short) is an extended
	 * missing value or not.
	 *
	 * @param value A short value to test
	 * @return A boolean indicating if the value is an extended missing value or
	 * not.
	 */
	public Boolean isExtendedMissing(Short value) {
		return shortval.isExtendedMissing(value);
	}
	
	/**
	 * Method to test if a Stata long type value (a Java int) is an extended
	 * missing value or not.
	 *
	 * @param value An Integer value to test
	 * @return A boolean indicating if the value is an extended missing value or
	 * not.
	 */
	public Boolean isExtendedMissing(Integer value) {
		return intval.isExtendedMissing(value);
	}
	
	/**
	 * Currently a placeholder for future support for 64-bit integers will
	 * return false untill such time that these types are supported
	 *
	 * @param value A long value to test
	 * @return A boolean indicating if the value is an extended missing value or
	 * not.
	 */
	public Boolean isExtendedMissing(Long value) {
		return longval.isExtendedMissing(value);
	}
	
	/**
	 * Method to test if a Stata float type value is an extended missing value
	 * or not.
	 *
	 * @param value A float value to test
	 * @return A boolean indicating if the value is an extended missing value or
	 * not.
	 */
	public Boolean isExtendedMissing(Float value) {
		return floatval.isExtendedMissing(value);
	}
	
	/**
	 * Method to test if a Stata double type value is an extended missing value
	 * or not.
	 *
	 * @param value A double value to test
	 * @return A boolean indicating if the value is an extended missing value or
	 * not.
	 */
	public Boolean isExtendedMissing(Double value) {
		return doubleval.isExtendedMissing(value);
	}
	
	/**
	 * Method to retrieve the string mask for a missing value
	 *
	 * @param value The byte value for which you wish to retrieve the string
	 *              mask
	 * @return The string mask for the missing/extended missing value
	 */
	public String getMask(Byte value) {
		return byteval.getMask(value);
	}
	
	/**
	 * Method to retrieve the string mask for a missing value
	 *
	 * @param value The short value for which you wish to retrieve the string
	 *              mask
	 * @return The string mask for the missing/extended missing value
	 */
	public String getMask(Short value) {
		return shortval.getMask(value);
	}
	
	/**
	 * Method to retrieve the string mask for a missing value
	 *
	 * @param value The int value for which you wish to retrieve the string
	 *              mask
	 * @return The string mask for the missing/extended missing value
	 */
	public String getMask(Integer value) {
		return intval.getMask(value);
	}
	
	/**
	 * Method to retrieve the string mask for a missing value
	 *
	 * @param value The long value for which you wish to retrieve the string
	 *              mask
	 * @return The string mask for the missing/extended missing value
	 */
	public String getMask(Long value) {
		return longval.getMask(value);
	}
	
	/**
	 * Method to retrieve the string mask for a missing value
	 *
	 * @param value The float value for which you wish to retrieve the string
	 *              mask
	 * @return The string mask for the missing/extended missing value
	 */
	public String getMask(Float value) {
		return floatval.getMask(value);
	}
	
	/**
	 * Method to retrieve the string mask for a missing value
	 *
	 * @param value The double value for which you wish to retrieve the string
	 *              mask
	 * @return The string mask for the missing/extended missing value
	 */
	public String getMask(Double value) {
		return doubleval.getMask(value);
	}
	
}
