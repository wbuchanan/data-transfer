package org.paces.data.Stata.Readers.FileElements.Blobs;

import java.nio.ByteBuffer;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public interface Strl {

	/**
	 * Method used to parse a ByteBuffer into the corresponding [v, o] element.
	 * <em>Note, this is the preferred method to use when parsing release
	 * version 117 files.</em>
	 * @param buffer A ByteBuffer containing the [v, o] elements
	 */
	void parseMembers(ByteBuffer buffer) ;

	/**
	 * Method used to parse an Array of bytes into the corresponding [v, o]
	 * elements.
	 * <em>Note, this is the preferred method to use when parsing release
	 * version 118 files.  This is due to the requirement to handle the
	 * 8-byte sequences as a 2-byte and 6 byte integer.</em>
	 */
	void parseMembers();

	/**
	 * Member to access the variable index for the strL datum
	 * @return An integer containing the variable index to use to look up the
	 * appropriate GSO datum
	 */
	Integer getV();

}
