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
	protected static ByteOrder SYS_ENDIANNESS;

	/**
	 * Field used to provide a string representation of the endianness used
	 * when writing a file to disk
	 */
	protected static String sysendString;

	/***
	 * String Indicating the bit order
	 */
	protected static String element;

	/***
	 * Enum class with Stata constants that define endianness
	 */
	protected static ByteOrder fileByteOrder;

	/***
	 * Boolean used to indicate whether or not bytes need to be swapped when
	 * reading data into the JVM from disk.
	 */
	protected static Boolean byteswap;

	/***
	 * Member indicating the order which the bytes should be read
	 */
	public static java.nio.ByteOrder swapto;

	/***
	 * Class constructor
	 */
	public static void main(String[] args) {
		StataByteOrder sbo = new StataByteOrder(args[0]);
	}

	public StataByteOrder(String element) {

		// If Most significant bit first set the fileByteOrder field to MSF
		if ("MSF".equals(element)) fileByteOrder = ByteOrder.BIG_ENDIAN;

		// If least significant bit first set the fileByteOrder field to LSF
		else fileByteOrder = ByteOrder.LITTLE_ENDIAN;

		// Check the system order...if little endian system set
		// sys_endianness to LSF
		if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
			SYS_ENDIANNESS = ByteOrder.LITTLE_ENDIAN;
			sysendString = "LSF";
		}

		// Otherwise set sys_endianness to MSF
		else {
			SYS_ENDIANNESS = ByteOrder.BIG_ENDIAN;
			sysendString = "MSF";
		}

		// If sys and file endianness are the same set byteswap to false
		if (SYS_ENDIANNESS == fileByteOrder) byteswap = false;

		// If they are different set byteswap to true
		else byteswap = true;

		if (byteswap && "MSF".equals(sysendString)) swapto = ByteOrder.BIG_ENDIAN;

		else if (byteswap && "LSF".equals(sysendString)) swapto = ByteOrder.LITTLE_ENDIAN;

	} // End class constructor method

	/***
	 * Returns the endianness string
	 * @return
	 */
	public static String getElement() {
		return element;
	}

	/***
	 * Method returning indicator used to triger byte swapping when
	 * processing binary data
	 * @return A Boolean indicating whether or not the bytes need to be
	 * swapped when reading a file from disk.
	 */
	public static Boolean getByteswap() {
		return byteswap;
	}

	/***
	 * Method used to retrieve the endianness of the host system used when
	 * writing a Stata formatted file to disk
	 * @return A string containing either MSF (BIG Endian) or LSF (Little
	 * Endian) used when indicating the byteorder when writing a Stata file
	 * to disk.
	 */
	public static String getSysEndianness() {
		return sysendString;
	}

}
