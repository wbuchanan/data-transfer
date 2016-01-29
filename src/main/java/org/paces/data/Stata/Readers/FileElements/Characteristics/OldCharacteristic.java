package org.paces.data.Stata.Readers.FileElements.Characteristics;

import org.paces.data.Stata.Readers.StConvert;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class OldCharacteristic implements Characteristic {

	/**
	 * Member storing the data type of the characteristic
	 */
	private  Byte chartype;
	
	/**
	 * Member containing all of the contents of this characteristic
	 */
	private  List<String> characteristic = new ArrayList<>();

	/**
	 * Member containing the default characteristic name for files versioned
	 * < 117
	 */
	private byte[] charName = new byte[33];

	/**
	 * Member containing the default variable name for files versioned
	 * < 117
	 */
	private byte[] varName = new byte[33];

	/**
	 * Member used to store the position of the ByteBuffer prior to testing
	 * if there is a subsequent characteristic
	 */
	private  Integer tmppos;

	/**
	 * Member storing boolean indicating if there is a subsequent
	 * characteristic following this one.
	 */
	private  Boolean next;

	/**
	 * Class constructor for Expansion fields (e.g., characteristics from
	 * files versioned before 117)
	 * @param bytes A ByteBuffer from which the object is constructed
	 */
	public OldCharacteristic(ByteBuffer bytes) {

		// Gets the Byte value of the data type indicator
		chartype = bytes.get();

		// Gets the integer length of the contents
		Integer clen = bytes.getInt();

		// Creates a byte array of length defined by clen (above)
		byte[] contents = new byte[clen];

		// Creates a total length value by adding the total byte length (1
		// byte for the type, 4 bytes for the length, and the value of the
		// 4-byte integer for the contents)
		Long totalLength = (long) (1 + 4 +  33 + 33 +clen);

		bytes.get(varName);

		bytes.get(charName);

		// Sets the type position in the list
		characteristic.add(0, String.valueOf(chartype));

		// Sets the variable name position in the list
		characteristic.add(1, StConvert.toStata(this.varName, bytes.order(), ""));

		// Sets the characteristic name position in the list
		characteristic.add(2, StConvert.toStata(this.charName, bytes.order(), ""));

		// Sets the byte length of the contents position in the list
		characteristic.add(3, String.valueOf(clen));

		// Reads the bytes of the contents
		bytes.get(contents);

		// Gets a placeholder for the current position of the ByteBuffer
		tmppos = bytes.position();

		// Sets the contents position in the list
		characteristic.add(4, StConvert.toStata(contents, bytes.order(), ""));

		// Sets the total length position in the list
		characteristic.add(5, String.valueOf(totalLength));

	} // End Class constructor


	/**
	 * Method called by the class constructor to set the value retrieved by the
	 * hasNext() method.
	 *
	 * @param thebytes A bytebuffer from which the class constructor reads the
	 *                 characteristic
	 */
	@Override
	public  void setNext(ByteBuffer thebytes) {
		Byte v1 = thebytes.get();
		Integer v2 = thebytes.getInt();
		thebytes.position(tmppos);
		next = v1 != 0 && v2 != 0;
	}

	/**
	 * A boolean indicating whether or not there is a characteristic after the
	 * current characteristic
	 *
	 * @return A Boolean indicating whether or not there is another
	 * characteristic following the current characteristic
	 */
	@Override
	public  Boolean hasNext() {
		return next;
	}

	/**
	 * Returns the content type value.  A value of 129 denotes binary content,
	 * while a value of 130 denotes ASCII content
	 *
	 * @return The byte value indicating the content type
	 */
	@Override
	public  Byte getType() {
		return Byte.valueOf(characteristic.get(0));
	}

	/**
	 * Method to access the variable name associated with the characteristic.
	 * <em>Note: This returns an empty string for files with release versions
	 * prior to 117.</em>
	 *
	 * @return A String containing the characteristic name
	 */
	@Override
	public  String getVarName() {
		return characteristic.get(1);
	}

	/**
	 * Method to access the characteristic name of the characteristic. <em>Note:
	 * This returns an empty string for files with release versions prior to
	 * 117.</em>
	 *
	 * @return A String containing the characteristic name
	 */
	@Override
	public  String getCharName() {
		return characteristic.get(2);
	}

	/**
	 * Method to access the byte length of the contents
	 *
	 * @return A 4-byte integer containing the length of the datum in bytes
	 */
	@Override
	public  Integer getLength() {
		return Integer.valueOf(characteristic.get(3));
	}

	/**
	 * Method to access the contents as a String constructed from the bytes of
	 * the contents
	 *
	 * @return A String containing the bytes of the characteristic
	 */
	@Override
	public  String getContents() {
		return characteristic.get(4);
	}

	/**
	 * Returns the total Byte length of the characteristic (e.g., the sum of
	 * bytes used to read/write the contents completely)
	 *
	 * @return A long value containing the total number of bytes used for this
	 * characteristic
	 */
	@Override
	public  Long getTotalLength() {
		return Long.valueOf(characteristic.get(5));
	}

	/**
	 * Returns the characteristic in a single containerized object
	 *
	 * @return A list of String values for each of the members
	 */
	@Override
	public  List<String> getCharacteristic() {
		return characteristic;
	}
}
