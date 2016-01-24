package org.paces.data.Stata.Readers.Blobs;

import org.paces.data.Stata.Readers.DtaExceptions.StrlException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class StrlGSO {

	/**
	 * Member containing valid string for the start of a GSO datum element
	 */
	private static final String GSO = "GSO";

	/**
	 * Member used to allocate an array of bytes to read the GSO characters
	 * of the GSO elements
	 */
	private byte[] gso = new byte[3];

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

	/**
	 * Member used to store the decimal value of the v element
	 */
	private Integer v = null;

	/**
	 * Member used to store the decimal value of the o element
	 */
	private Long o = null;

	/**
	 * Class constructor for GSO datum
	 * @param x A RandomAccessFile object used to read the bytes from the
	 *             dataset
	 * @param position The position in the file where the GSO datum should be
	 *                    read from
	 *
	 * @param bo The ByteOrder used to correctly interpret the bytes in the file
	 * @throws IOException An exception thrown if there are issues reading
	 * from the RandomAccessFile object.
	 * @throws StrlException An exception thrown if the datum does not begin
	 * with the string "GSO" as specified in the .dta file specification.
	 */
	public StrlGSO(RandomAccessFile x, Long position, ByteOrder bo) throws
			IOException, StrlException {
		FileChannel fc = x.getChannel();
		MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, position, fc.size());
		buffer.order(bo);
		buffer.get(this.gso);
		if (validRecordStart(Arrays.toString(this.gso))) {
			builder(buffer);
		} else {
			throw new StrlException();
		}
	}

	/**
	 * Alternative class constructor using a ByteBuffer directly
	 * @param buffer The ByteBuffer from which to read from
	 * @throws StrlException An exception thrown if the datum does not begin
	 * with the string "GSO" as specified in the .dta file specification.
	 */
	public StrlGSO(ByteBuffer buffer) throws StrlException {
		buffer.get(this.gso);
		if (validRecordStart(Arrays.toString(this.gso))) {
			builder(buffer);
		} else {
			throw new StrlException();
		}
	}

	/**
	 * Helper method used to call the class member setters
	 * @param buffer A ByteBuffer either created by the class constructor or
	 *                  passed as a parameter to the class constructor.
	 */
	private void builder(ByteBuffer buffer) {
		setV(buffer);
		setO(buffer);
		setT(buffer);
		setLen(buffer);
		setContents(buffer);
	}

	/**
	 * Method used to set the v member of a GSO strL value
	 * @param fileBytes A ByteBuffer used to read/access the datum
	 */
	private void setV(ByteBuffer fileBytes) {
		this.v = fileBytes.getInt();
	}

	/**
	 * Method used to set the o member of a GSO strL value
	 * @param fileBytes A ByteBuffer used to read/access the datum
	 */
	private void setO(ByteBuffer fileBytes) {
		this.o = fileBytes.getLong();
	}

	/**
	 * Method used to set the t member of a GSO strL value
	 * @param fileBytes A ByteBuffer used to read/access the datum
	 */
	private void setT(ByteBuffer fileBytes) {
		this.t = fileBytes.get();
	}

	/**
	 * Method used to set the len member of a GSO strL value
	 * @param fileBytes A ByteBuffer used to read/access the datum
	 */
	private void setLen(ByteBuffer fileBytes) {
		this.len = fileBytes.getInt();
	}

	/**
	 * Method used to set the contents member of a GSO strL value
	 * @param fileBytes A ByteBuffer used to read/access the datum
	 */
	private void setContents(ByteBuffer fileBytes) {
		if (getT() == 129) {
			byte[] tmpcontents = new byte[getLen()];
			this.contents = setBinContents(fileBytes, tmpcontents);
		} else {
			char[] tmpcontents = new char[getLen()];
			this.contents = setAsciiContents(fileBytes, tmpcontents);
		}
	}

	/**
	 * Method used to set the contents member of a GSO strL value when the
	 * data are encoded as a binary string
	 */
	private String setBinContents(ByteBuffer fileBytes, byte[] container) {
		fileBytes.get(container);
		return Arrays.toString(container);
	}

	/**
	 * Method used to set the contents member of a GSO strL value when the
	 * data are encoded as an ASCII string
	 */
	private String setAsciiContents(ByteBuffer fileBytes, char[] container) {
		CharBuffer chars = fileBytes.asCharBuffer().get(container);
		return chars.toString();
	}

	/**
	 * Method that checks the first three bytes of a GSO record equal 'GSO'.
	 * Pass String.valueOf(gso) after reading the bytes to test whether the
	 * data was being parsed correctly.
	 * @param gsoString A string derived from the first three bytes of the
	 *                     gso variable and should equal 'GSO' if valid
	 * @return A boolean indicating whether or not the datum began with 'GSO'
	 */
	private Boolean validRecordStart(String gsoString) {
		return GSO.equals(gsoString);
	}

	/**
	 * Method used to retrieve the variable index member
	 * @return A 4-byte integer value containing the variable position for the
	 * strL datum
	 */
	public Integer getV() {
		return this.v;
	}

	/**
	 * Method used to retrieve the observation index number
	 * @return An 8-byte long value containing the observation number for the
	 * strL datum
	 */
	public Long getO() {
		return this.o;
	}

	/**
	 * Method used to retrieve the data type indicator
	 * @return A byte value of either 129 or 130 indicating whether the
	 * contents are encoded as a binary or ASCII string respectively
	 */
	public Byte getT() {
		return this.t;
	}

	/**
	 * Method used to access the length - in bytes - of the datum's contents
	 * @return A 4-byte integer value used to set the length of the byte
	 * array used to read the datum contents
	 */
	public Integer getLen() {
		return this.len;
	}

	/**
	 * Method used to access the datum contents
	 * @return A string with the contents of the datum
	 */
	public String getContents() {
		return this.contents;
	}

} // End declaration of the interface class
