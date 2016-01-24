package org.paces.data.Stata.Readers.Blobs;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class StrlData implements Strl {

	/**
	 * Member containing the byte order to use when interpretting the bytes
	 */
	private final ByteOrder bo;

	/**
	 * Member used to store the [v, o] array from the data element in the
	 * .dta file
	 */
	private final byte[] strlArray = new byte[8];

	/**
	 * Member used to allocate an array of bytes to read the GSO characters
	 * of the GSO elements
	 */
	private final byte[] gso = new byte[3];

	/**
	 * Member used to store the decimal value of the v element
	 */
	private Integer v = null;

	/**
	 * Member used to store the decimal value of the o element
	 */
	private Long o = null;

	/**
	 * Member used to store the decimal value of the t element
	 */
	private Byte t = null;

	/**
	 * Member used to store the length of the BLOB contents
	 */
	private Integer len = null;

	/**
	 * Member containing the contents
	 */
	private String contents = "";

	StrlData(RandomAccessFile x, ByteOrder bo) throws IOException {
		this.bo = bo;
		x.read(this.strlArray);


	}

	StrlData(ByteBuffer x, ByteOrder bo) {
		this.bo = bo;
		x.get(this.strlArray);
	}


	/**
	 * Method used to set the variable element from strl values in the data
	 * element
	 */
	@Override
	public void setVariable() {
		byte[] velem = Arrays.copyOfRange(this.strlArray, vdstart, vdend);
		this.v = ByteBuffer.wrap(Arrays.copyOf(velem, 4)).order(this.bo).getInt();
	}

	/**
	 * Method used to set the observation element from strl values in the data
	 * element
	 */
	@Override
	public void setObservation() {
		byte[] oelem = Arrays.copyOfRange(this.strlArray, odstart, odend);
		this.o = ByteBuffer.wrap(Arrays.copyOf(oelem, 8)).order(this.bo).getLong();
	}

	/**
	 * Method used to set the v member of a GSO strL value
	 */
	@Override
	public void setV() {


	}

	/**
	 * Method used to set the o member of a GSO strL value
	 */
	@Override
	public void setO() {

	}

	/**
	 * Method used to set the t member of a GSO strL value
	 */
	@Override
	public void setT() {

	}

	/**
	 * Method used to set the len member of a GSO strL value
	 */
	@Override
	public void setLen() {

	}

	/**
	 * Method used to set the contents member of a GSO strL value
	 *
	 * @param type   Either 129 (binary string data) or 130 (ASCII string data)
	 * @param length The length in bytes of the contents
	 */
	@Override
	public void setContents(Byte type, Integer length) {

	}

	/**
	 * Method used to set the contents member of a GSO strL value when the data
	 * are encoded as a binary string
	 */
	@Override
	public String setBinContents() {
		return null;
	}

	/**
	 * Method used to set the contents member of a GSO strL value when the data
	 * are encoded as an ASCII string
	 */
	@Override
	public String setAsciiContents() {
		return null;
	}

	/**
	 * Method that checks the first three bytes of a GSO record equal 'GSO'.
	 * Pass String.valueOf(gso) after reading the bytes to test whether the data
	 * was being parsed correctly.
	 *
	 * @param gsoString A string derived from the first three bytes of the gso
	 *                  variable and should equal 'GSO' if valid
	 * @return A boolean indicating whether or not the datum began with 'GSO'
	 */
	@Override
	public Boolean validRecordStart(String gsoString) {
		return null;
	}

	/**
	 * Method used to retrieve the variable index member
	 *
	 * @return A 4-byte integer value containing the variable position for the
	 * strL datum
	 */
	@Override
	public Integer getV() {
		return null;
	}

	/**
	 * Method used to retrieve the observation index number
	 *
	 * @return An 8-byte long value containing the observation number for the
	 * strL datum
	 */
	@Override
	public Long getO() {
		return null;
	}

	/**
	 * Method used to retrieve the data type indicator
	 *
	 * @return A byte value of either 129 or 130 indicating whether the contents
	 * are encoded as a binary or ASCII string respectively
	 */
	@Override
	public Byte getT() {
		return null;
	}

	/**
	 * Method used to access the length - in bytes - of the datum's contents
	 *
	 * @return A 4-byte integer value used to set the length of the byte array
	 * used to read the datum contents
	 */
	@Override
	public Integer getLen() {
		return null;
	}

	/**
	 * Method used to access the datum contents
	 *
	 * @return A string with the contents of the datum
	 */
	@Override
	public String getContents() {
		return null;
	}
}
