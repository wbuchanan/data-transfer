package org.paces.data.Stata.Readers.FileElements;

import org.paces.data.Stata.Readers.StConvert;

import java.nio.ByteOrder;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class DtaCharacteristic {

	/**
	 * List of string objects with the following structure:
	 * <table>
	 *     <th>
	 *         <td>Data</td><td>Length</td>
	 *     </th>
	 *     <tr>
	 *         <td>Variable Name</td><td>129 bytes</td>
	 *     </tr>
	 *     <tr>
	 *         <td>Characteristic Name</td><td>129 bytes</td>
	 *     </tr>
	 *     <tr>
	 *         <td>Content</td><td><= 67,784 bytes</td>
	 *     </tr>
	 * </table>
	 */
	private List<String> characteristic;

	/***
	 * Class constructor for Stata Characteristics
	 * @param bytes A two-dimensional byte array where the first dimension
	 *                 corresponds to the data elements described in the
	 *                 characteristics member.
	 * @param sbo A ByteOrder constant used to check for byteswapping
	 */
	public DtaCharacteristic(List<byte[]> bytes, ByteOrder sbo) {
		characteristic.add(0, StConvert.toStata(bytes.get(0), sbo, ""));
		characteristic.add(1, StConvert.toStata(bytes.get(1), sbo, ""));
		characteristic.add(2, StConvert.toStata(bytes.get(2), sbo, ""));
	}

	/***
	 * Method to return a list of string elements containing a dataset
	 * characteristic
	 * @return A list with the values of the characteristics to add
	 */
	public List<String> getCharacteristic() {
		return this.characteristic;
	}

}
