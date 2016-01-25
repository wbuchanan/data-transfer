package org.paces.data.Stata.Readers.Blobs;

import org.paces.data.Stata.Readers.DtaExceptions.StrlException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Strl118 implements Strl {

	/**
	 * Starting position used to select bytes from the array containing the
	 * [v, o] elements to parse the v element.
	 */
	private static final Integer vdstart = 0;

	/**
	 * Ending position used to select bytes from the array containing the
	 * [v, o] elements to parse the v element.
	 */
	private static final Integer vdend = 2;

	/**
	 * Starting position used to select bytes from the array containing the
	 * [v, o] elements to parse the o element.
	 */
	private static final Integer odstart = 2;

	/**
	 * Ending position used to select bytes from the array containing the
	 * [v, o] elements to parse the o element.
	 */
	private static final Integer odend = 8;

	/**
	 * Member used to store the [v, o] array from the data element in the
	 * .dta file
	 */
	private byte[] strlArray = new byte[8];

	/**
	 * Member used to store the decimal value of the v element
	 */
	private Integer v = null;

	/**
	 * Member used to store the decimal value of the o element
	 */
	private Long o = null;

	/**
	 * Member containing the byte order to use when interpretting the bytes
	 * from the file
	 */
	private ByteOrder bo;



	/**
	 * Class constructor to parse strL datum elements from the data element
	 * of the .dta file
	 * @param x A ByteBuffer object used to access the bytes of the file
	 */
	public Strl118(ByteBuffer x) {
		this.bo = x.order();
		parseMembers(x);
	}

	/**
	 * Class constructor to parse strL datum elements from the data element
	 * of the .dta file
	 * @param bytes An array of bytes to parse
	 * @param bo The ordering of the bytes for interpretation
	 */
	public Strl118(byte[] bytes, ByteOrder bo) throws StrlException {
		this.bo = bo;
		if (bytes.length == 8) {
			this.strlArray = bytes;
			parseMembers();
		}
		else throw new StrlException("Byte array passed to Strl117 class not " +
				"8 bytes in length.  This class should only be used to read " +
				"the [v, o] element from within the <data></data> element of " +
				"a .dta file.");

	}


	/**
	 * Method used to parse a ByteBuffer into the corresponding [v, o] element.
	 * <em>Note, this is the preferred method to use when parsing release
	 * version 117 files.</em>
	 *
	 * @param buffer A ByteBuffer containing the [v, o] elements
	 */
	@Override
	public void parseMembers(ByteBuffer buffer) {

		// Read the next 8 byte block
		buffer.get(this.strlArray);

		// Call the parseMembers method that does not have any parameters
		parseMembers();

	}

	/**
	 * Method used to parse an Array of bytes into the corresponding [v, o]
	 * elements. <em>Note, this is the preferred method to use when parsing
	 * release version 118 files.  This is due to the requirement to handle the
	 * 8-byte sequences as a 2-byte and 6 byte integer.</em>
	 */
	@Override
	public void parseMembers() {

		// Create a byte array from the first two elements of the strlArray
		// object and copy it into an Array that is 4 bytes long, then read
		// the datum and cast to an Integer
		this.v = ByteBuffer.wrap(Arrays.copyOf(Arrays.copyOfRange(this
				.strlArray, vdstart, vdend), 4)).order(this.bo).getInt();

		// Create a byte array from second through last elements of the
		// strlArray object and copy it into an Array that is 8 bytes long,
		// then read the datum and cast to a Long.
		this.o = ByteBuffer.wrap(Arrays.copyOf(Arrays.copyOfRange(this
				.strlArray, odstart, odend), 8)).order(this.bo).getLong();

	} // End of Method declaration

	/**
	 * Member to access the variable index for the strL datum
	 *
	 * @return An integer containing the variable index to use to look up the
	 * appropriate GSO datum
	 */
	@Override
	public Integer getV() {
		return this.v;
	}

	/**
	 * Member to access the variable index for the strL datum
	 *
	 * @return An integer containing the variable index to use to look up the
	 * appropriate GSO datum
	 */
	public Long getO() {
		return this.o;
	}


}
