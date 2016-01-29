package org.paces.data.Stata.Readers.FileElements.Characteristics;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
interface Characteristic {
	
	/**
	 * Method called by the class constructor to set the value retrieved by the
	 * hasNext() method.
	 *
	 * @param thebytes A bytebuffer from which the class constructor reads the
	 *                 characteristic
	 */
	void setNext(ByteBuffer thebytes);
	
	/**
	 * A boolean indicating whether or not there is a characteristic after the
	 * current characteristic
	 *
	 * @return A Boolean indicating whether or not there is another
	 * characteristic following the current characteristic
	 */
	Boolean hasNext();
	
	/**
	 * Returns the content type value.  A value of 129 denotes binary content,
	 * while a value of 130 denotes ASCII content
	 *
	 * @return The byte value indicating the content type
	 */
	Byte getType();
	
	/**
	 * Method to access the variable name associated with the characteristic.
	 * <em>Note: This returns an empty string for files with release versions
	 * prior to 117.</em>
	 *
	 * @return A String containing the characteristic name
	 */
	String getVarName();
	
	/**
	 * Method to access the characteristic name of the characteristic. <em>Note:
	 * This returns an empty string for files with release versions prior to
	 * 117.</em>
	 *
	 * @return A String containing the characteristic name
	 */
	String getCharName();
	
	/**
	 * Method to access the byte length of the contents
	 *
	 * @return A 4-byte integer containing the length of the datum in bytes
	 */
	Integer getLength();
	
	/**
	 * Method to access the contents as a String constructed from the bytes of
	 * the contents
	 *
	 * @return A String containing the bytes of the characteristic
	 */
	String getContents();
	
	/**
	 * Returns the total Byte length of the characteristic (e.g., the sum of
	 * bytes used to read/write the contents completely)
	 *
	 * @return A long value containing the total number of bytes used for this
	 * characteristic
	 */
	Long getTotalLength();
	
	/**
	 * Returns the characteristic in a single containerized object
	 *
	 * @return A list of String values for each of the members
	 */
	List<String> getCharacteristic();

}
