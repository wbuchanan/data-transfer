package org.paces.data.Stata.Readers.Blobs;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Strl {

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
	private final byte[] strlArray = new byte[8];

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
	 * @param x A RandomAccessFile object used to access the bytes of the file
	 * @param position The byte position from which to read the bytes
	 * @param bo The ordering of the bytes for interpretation
	 * @throws IOException Error thrown if issue with reading the data from
	 * the file
	 */
	public Strl(RandomAccessFile x, Long position, ByteOrder bo) throws
			IOException {
		this.bo = bo;
		FileChannel fc = x.getChannel();
		MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, position, 8L);
		buffer.order(bo);
		buffer.get(this.strlArray);
		setV();
		setO();
	}

	/**
	 * Class constructor to parse strL datum elements from the data element
	 * of the .dta file
	 * @param x A ByteBuffer object used to access the bytes of the file
	 * @param bo The ordering of the bytes for interpretation
	 */
	public Strl(ByteBuffer x, ByteOrder bo) {
		this.bo = bo;
		x.get(this.strlArray);
		setV();
		setO();
	}

	/**
	 * Method used to set the variable element from strl values in the data
	 * element
	 */
	public void setV() {
		byte[] velem = Arrays.copyOfRange(this.strlArray, vdstart, vdend);
		this.v = ByteBuffer.wrap(Arrays.copyOf(velem, 4)).order(this.bo).getInt();
	}

	/**
	 * Method used to set the observation element from strl values in the data
	 * element
	 */
	public void setO() {
		byte[] oelem = Arrays.copyOfRange(this.strlArray, odstart, odend);
		this.o = ByteBuffer.wrap(Arrays.copyOf(oelem, 8)).order(this.bo).getLong();
	}

	/**
	 * Method used to retrieve the variable index member
	 *
	 * @return A 4-byte integer value containing the variable position for the
	 * strL datum
	 */
	public Integer getV() {
		return this.v;
	}

	/**
	 * Method used to retrieve the observation index number
	 *
	 * @return An 8-byte long value containing the observation number for the
	 * strL datum
	 */
	public Long getO() {
		return this.o;
	}

}
