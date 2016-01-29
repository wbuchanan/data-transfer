package org.paces.data.Stata.Readers.FileElements.Blobs;

import org.paces.data.Stata.Readers.DtaExceptions.StrlException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Strl117 implements Strl {

	/**
	 * Starting position used to select bytes from the array containing the
	 * [v, o] elements to parse the v element.
	 */
	private static final Integer vdstart = 0;

	/**
	 * Ending position used to select bytes from the array containing the
	 * [v, o] elements to parse the v element.
	 */
	private static final Integer vdend = 4;

	/**
	 * Starting position used to select bytes from the array containing the
	 * [v, o] elements to parse the o element.
	 */
	private static final Integer odstart = 4;

	/**
	 * Ending position used to select bytes from the array containing the
	 * [v, o] elements to parse the o element.
	 */
	private static final Integer odend = 8;

	/**
	 * Member used to store the [v, o] array from the data element in the
	 * .dta file
	 */
	private byte[] strlArray = null;

	private ByteOrder sbo = ByteOrder.LITTLE_ENDIAN;


	Integer v;

	Integer o;


	public Strl117(ByteBuffer buffer) {
		parseMembers(buffer);
	}

	public Strl117(byte[] bytes, ByteOrder sbo) throws StrlException {
		sbo = sbo;
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
		this.v = buffer.getInt();
		this.o = buffer.getInt();
	}

	/**
	 * Method used to parse an Array of bytes into the corresponding [v, o]
	 * elements.
	 * <em>Note, this is the preferred method to use when parsing release
	 * version 118 files.  This is due to the requirement to handle the
	 * 8-byte sequences as a 2-byte and 6 byte integer.</em>
	 */
	@Override
	public void parseMembers() {

		// Casts the first four bytes of the array into an Integer stored in v
		this.v = ByteBuffer.wrap(Arrays.copyOfRange(this.strlArray, vdstart, vdend)).order(this.sbo).getInt();

		// Casts the second four bytes of the array into an Integer stored in o
		this.o = ByteBuffer.wrap(Arrays.copyOfRange(this.strlArray, odstart, odend)).order(this.sbo).getInt();

	}

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
	 * Member to access the observation index for the strL datum
	 *
	 * @return An integer containing the variable index to use to look up the
	 * appropriate GSO datum
	 */
	public Integer getO() {
		return this.o;
	}


}
