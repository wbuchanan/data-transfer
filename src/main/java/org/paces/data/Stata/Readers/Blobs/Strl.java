package org.paces.data.Stata.Readers.Blobs;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public interface Strl {

	/**
	 * Starting position used to select bytes from the array containing the
	 * [v, o] elements to parse the v element.
	 */
	final Integer vdstart = 0;

	/**
	 * Ending position used to select bytes from the array containing the
	 * [v, o] elements to parse the v element.
	 */
	final Integer vdend = 2;

	/**
	 * Starting position used to select bytes from the array containing the
	 * [v, o] elements to parse the o element.
	 */
	final Integer odstart = 2;

	/**
	 * Ending position used to select bytes from the array containing the
	 * [v, o] elements to parse the o element.
	 */
	final Integer odend = 8;

	/**
	 * Method used to set the variable element from strl values in the data
	 * element
	 */
	void setVariable();

	/**
	 * Method used to set the observation element from strl values in the
	 * data element
	 */
	void setObservation();

	/**
	 * Method used to set the v member of a GSO strL value
	 */
	void setV();

	/**
	 * Method used to set the o member of a GSO strL value
	 */
	void setO();

	/**
	 * Method used to set the t member of a GSO strL value
	 */
	void setT();

	/**
	 * Method used to set the len member of a GSO strL value
	 */
	void setLen();

	/**
	 * Method used to set the contents member of a GSO strL value
	 * @param type Either 129 (binary string data) or 130 (ASCII string data)
	 * @param length The length in bytes of the contents
	 */
	void setContents(Byte type, Integer length);

	/**
	 * Method used to set the contents member of a GSO strL value when the
	 * data are encoded as a binary string
	 */
	String setBinContents();

	/**
	 * Method used to set the contents member of a GSO strL value when the
	 * data are encoded as an ASCII string
	 */
	String setAsciiContents();

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
	 * @return A 4-byte integer value containing the variable position for the
	 * strL datum
	 */
	Integer getV();

	/**
	 * Method used to retrieve the observation index number
	 * @return An 8-byte long value containing the observation number for the
	 * strL datum
	 */
	Long getO();

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

} // End declaration of the interface class
