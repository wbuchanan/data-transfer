package org.paces.data.Stata.Readers.FileElements.Blobs;

import java.nio.ByteBuffer;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public interface GSO {

	/**
	 * Helper method used to call the class member setters
	 * @param buffer A ByteBuffer either created by the class constructor or
	 *                  passed as a parameter to the class constructor.
	 */
	void builder(ByteBuffer buffer);

	/**
	 * Method used to set object attribute indicating whether or not there is
	 * another GSO object after the current one
	 * @param buff A ByteBuffer from which to read the datum
	 * @param position The position at which the next three bytes should be
	 *                    read and tested (also used to reset the position
	 *                    indicator in case there is another GSO object).
	 */
	void setNext(ByteBuffer buff, Integer position);

	/**
	 * Method used to set the v member of a GSO strL value
	 * @param fileBytes A ByteBuffer used to read/access the datum
	 */
	void setV(ByteBuffer fileBytes);

	/**
	 * Method used to set the o member of a GSO strL value
	 * @param fileBytes A ByteBuffer used to read/access the datum
	 */
	void setO(ByteBuffer fileBytes);

	/**
	 * Method used to set the t member of a GSO strL value
	 * @param fileBytes A ByteBuffer used to read/access the datum
	 */
	void setT(ByteBuffer fileBytes);

	/**
	 * Method used to set the len member of a GSO strL value
	 * @param fileBytes A ByteBuffer used to read/access the datum
	 */
	void setLen(ByteBuffer fileBytes);

	/**
	 * Method used to set the contents member of a GSO strL value
	 * @param fileBytes A ByteBuffer used to read/access the datum
	 */
	void setContents(ByteBuffer fileBytes);

	/**
	 * Method used to set the contents member of a GSO strL value when the
	 * data are encoded as a binary string
	 */
	String setBinContents(ByteBuffer fileBytes, byte[] container);

	/**
	 * Method used to set the contents member of a GSO strL value when the
	 * data are encoded as an ASCII string
	 */
	String setAsciiContents(ByteBuffer fileBytes, char[] container);

	/**
	 * Method that checks the first three bytes of a GSO record equal 'GSO'.
	 * Pass String.valueOf(gso) after reading the bytes to test whether the
	 * data was being parsed correctly.
	 * @param gsoString A string derived from the first three bytes of the
	 *                     gso variable and should equal 'GSO' if valid
	 * @return A boolean indicating whether or not the datum began with 'GSO'
	 */
	Boolean validRecordStart(String gsoString);

	/**
	 * Method used to retrieve the variable index member
	 * @return Integer with the variable index where the value is located
	 */
	Integer getV();

	/**
	 * Method used to retrieve the data type indicator
	 * @return A byte value of either 129 or 130 indicating whether the
	 * contents are encoded as a binary or ASCII string respectively
	 */
	Byte getT();

	/**
	 * Method used to access the length - in bytes - of the datum's contents
	 * @return A 4-byte integer value used to set the length of the byte
	 * array used to read the datum contents
	 */
	Integer getLen();

	/**
	 * Method used to access the datum contents
	 * @return A string with the contents of the datum
	 */
	String getContents();

	void setTotalByteLength();


}
