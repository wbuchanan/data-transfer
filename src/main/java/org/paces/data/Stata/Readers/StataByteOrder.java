package org.paces.data.Stata.Readers;

import java.nio.ByteOrder;


/***
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class StataByteOrder {

	/***
	 * Field indicating the endianness of the host system
	 */
	protected ByteOrder SYS_ENDIANNESS;

	/**
	 * Field used to provide a string representation of the endianness used
	 * when writing a file to disk
	 */
	protected String sysendString;

	/***
	 * String Indicating the bit order
	 */
	protected String element;

	/***
	 * Enum class with Stata constants that define endianness
	 */
	protected ByteOrder fileByteOrder;

	/***
	 * Boolean used to indicate whether or not bytes need to be swapped when
	 * reading data into the JVM from disk.
	 */
	protected Boolean byteswap;

	/***
	 * Member indicating the order which the bytes should be read
	 */
	public java.nio.ByteOrder swapto;

	/**
	 * ByteOrder constructor used when string datum is passed to class
	 * constructor
	 * @param element String byte order indicator (either 'MSF' or 'LSF')
	 */
	public StataByteOrder(String element) {

		// If Most significant bit first set the fileByteOrder field to MSF
		if ("MSF".equals(element)) {
			this.fileByteOrder = ByteOrder.BIG_ENDIAN;
			this.element = "MSF";

		// If least significant bit first set the fileByteOrder field to LSF
		} else {

			this.fileByteOrder = ByteOrder.LITTLE_ENDIAN;
			this.element = "LSF";

		}

		// Check the system order...if little endian system set
		// sys_endianness to LSF
		if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
			this.SYS_ENDIANNESS = ByteOrder.LITTLE_ENDIAN;
			this.sysendString = "LSF";
		}

		// Otherwise set sys_endianness to MSF
		else {
			this.SYS_ENDIANNESS = ByteOrder.BIG_ENDIAN;
			this.sysendString = "MSF";
		}

		// If sys and file endianness are the same set byteswap to false
		if (this.SYS_ENDIANNESS == this.fileByteOrder) this.byteswap = false;

		// If they are different set byteswap to true
		else this.byteswap = true;

		// If the bytes need to be swapped and system is MSF set to big endian
		if (this.byteswap && "MSF".equals(this.sysendString)) this.swapto = ByteOrder.BIG_ENDIAN;

		// If the bytes need to be swapped and system is LSF set to big endian
		else if (this.byteswap && "LSF".equals(this.sysendString)) this.swapto = ByteOrder.LITTLE_ENDIAN;

		// Otherwise, set the swapto member to the value in the file
		else this.swapto = this.fileByteOrder;

	} // End class constructor method


	/**
	 * ByteOrder constructor used when byte array datum is passed to class
	 * constructor
	 * @param element A byte array of length 1; used in releases 113, 114,
	 *                   and 115.
	 */
	public StataByteOrder(byte[] element) {

		// If Most significant bit first set the fileByteOrder field to MSF
		if (element[0] == 0x01) {
			fileByteOrder = ByteOrder.BIG_ENDIAN;
			this.element = "MSF";

		// If least significant bit first set the fileByteOrder field to LSF
		} else {

			this.fileByteOrder = ByteOrder.LITTLE_ENDIAN;
			this.element = "LSF";
		}

		// Check the system order...if little endian system set
		// sys_endianness to LSF
		if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
			SYS_ENDIANNESS = ByteOrder.LITTLE_ENDIAN;
			this.sysendString = "LSF";
		}

		// Otherwise set sys_endianness to MSF
		else {
			SYS_ENDIANNESS = ByteOrder.BIG_ENDIAN;
			this.sysendString = "MSF";
		}

		// If sys and file endianness are the same set byteswap to false
		if (SYS_ENDIANNESS == this.fileByteOrder) this.byteswap = false;

			// If they are different set byteswap to true
		else this.byteswap = true;

		// If the bytes need to be swapped and system is MSF set to big endian
		if (this.byteswap && "MSF".equals(this.sysendString)) this.swapto = ByteOrder.BIG_ENDIAN;

		// If the bytes need to be swapped and system is LSF set to big endian
		else if (this.byteswap && "LSF".equals(this.sysendString)) this.swapto = ByteOrder.LITTLE_ENDIAN;

		// Otherwise, set the swapto member to the value in the file
		else this.swapto = this.fileByteOrder;

	} // End class constructor method

	/***
	 * Returns the endianness string
	 * @return The endianness of the file being parsed
	 */
	public String getElement() {
		return element;
	}

	/***
	 * Method returning indicator used to triger byte swapping when
	 * processing binary data
	 * @return A Boolean indicating whether or not the bytes need to be
	 * swapped when reading a file from disk.
	 */
	public Boolean getByteswap() {
		return byteswap;
	}

	/***
	 * Method used to retrieve the endianness of the host system used when
	 * writing a Stata formatted file to disk
	 * @return A string containing either MSF (BIG Endian) or LSF (Little
	 * Endian) used when indicating the byteorder when writing a Stata file
	 * to disk.
	 */
	public String getSysEndianness() {
		return sysendString;
	}

}
