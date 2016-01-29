package org.paces.data.Stata.Readers.FileElements.Characteristics;

import org.paces.data.Stata.Readers.DtaExceptions.CharacteristicException;
import org.paces.data.Stata.Readers.StConvert;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class NewCharacteristic implements Characteristic {

	/**
	 * Member containing all of the contents of this characteristic
	 */
	private List<String> characteristic = new ArrayList<>();

	/**
	 * Member used to store the position of the ByteBuffer prior to testing
	 * if there is a subsequent characteristic
	 */
	private Integer tmppos;

	/**
	 * Member storing boolean indicating if there is a subsequent
	 * characteristic following this one.
	 */
	private Boolean next;

	/**
	 * Member not used but stored to maintain consistency with accessors
	 * across new and old characteristic classes
	 */
	private final Byte chartype = 119;

	/**
	 * Valid starting byte sequence string for a characteristic
	 */
	private final String startVal = "<ch>";

	/**
	 * Valid ending byte sequence string for a characteristic
	 */
	private final String endVal = "</ch>";

	/**
	 * Container used to read the starting bytes
	 */
	private byte[] start = new byte[4];

	/**
	 * Container used to read the ending byte sequence of the characteristic
	 */
	private byte[] end = new byte[5];

	/**
	 * Container for the variable name.  Is \0 terminated, so the StConvert
	 * class is used to handle parsing the string with the \0 terminating
	 * character
	 */
	private byte[] varnm = new byte[129];

	/**
	 * Container for the characteristic name.  Is \0 terminated, so the
	 * StConvert class is used to handle parsing the string with the \0
	 * terminating character
	 */
	private byte[] charnm = new byte[129];

	/**
	 * Container that will be initialized by the constructor to store the
	 * bytes of content that must be read.
	 */
	private byte[] contents;

	/**
	 * Class constructor for Characteristics for files version >= 117
	 * @param bytes A bytebuffer from which to read the data
	 * @throws CharacteristicException An exception thrown if the
	 * characteristic begins with an invalid sequence of bytes
	 */
	public NewCharacteristic(ByteBuffer bytes) throws CharacteristicException {
		bytes.get(start);
		if (validStart(start)) {
			Integer charlen = bytes.getInt();
			contents = new byte[charlen];
			Long totalLength = (long) (4 + 129 + 129 + charlen);
			bytes.get(varnm);
			bytes.get(charnm);
			bytes.get(contents);
			characteristic.add(0, String.valueOf(chartype));
			characteristic.add(1, StConvert.toStata(varnm, bytes.order(), ""));
			characteristic.add(1, StConvert.toStata(charnm, bytes.order(), ""));
			characteristic.add(3, String.valueOf(charlen));
			characteristic.add(4, StConvert.toStata(contents, bytes.order(), ""));
			characteristic.add(5, String.valueOf(totalLength));
			setNext(bytes);
		} else {
			throw new CharacteristicException();
		}

	}

	/**
	 * Method used to check whether or not the characteristic begins with
	 * {@literal '<ch>'}
	 * @param charStart The 4 bytes starting the characteristic
	 * @return A boolean indicating if the 4-byte sequence is a valid start
	 * to a characteristic
	 */
	public Boolean validStart(byte[] charStart) {
		return (charStart.length == 4 && startVal.equals(new String(charStart)));
	}


	/**
	 * Method called by the class constructor to set the value retrieved by the
	 * hasNext() method.
	 *
	 * @param thebytes A bytebuffer from which the class constructor reads the
	 *                 characteristic
	 */
	@Override
	public void setNext(ByteBuffer thebytes) {
		byte[] nextStart = new byte[4];
		thebytes.get(end);
		tmppos = thebytes.position();
		thebytes.get(nextStart);
		next = startVal.equals(new String(nextStart)) && endVal.equals(new String(end));
		thebytes.position(tmppos);
	}

	/**
	 * A boolean indicating whether or not there is a characteristic after the
	 * current characteristic
	 *
	 * @return A Boolean indicating whether or not there is another
	 * characteristic following the current characteristic
	 */
	@Override
	public Boolean hasNext() {
		return next;
	}

	/**
	 * Returns the content type value.  A value of 129 denotes binary content,
	 * while a value of 130 denotes ASCII content
	 *
	 * @return The byte value indicating the content type
	 */
	@Override
	public Byte getType() {
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
	public String getVarName() {
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
	public String getCharName() {
		return characteristic.get(2);
	}

	/**
	 * Method to access the byte length of the contents
	 *
	 * @return A 4-byte integer containing the length of the datum in bytes
	 */
	@Override
	public Integer getLength() {
		return Integer.valueOf(characteristic.get(3));
	}

	/**
	 * Method to access the contents as a String constructed from the bytes of
	 * the contents
	 *
	 * @return A String containing the bytes of the characteristic
	 */
	@Override
	public String getContents() {
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
	public Long getTotalLength() {
		return Long.valueOf(characteristic.get(5));
	}

	/**
	 * Returns the characteristic in a single containerized object
	 *
	 * @return A list of String values for each of the members
	 */
	@Override
	public List<String> getCharacteristic() {
		return characteristic;
	}

}
