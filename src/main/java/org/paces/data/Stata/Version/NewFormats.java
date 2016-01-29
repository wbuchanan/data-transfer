package org.paces.data.Stata.Version;

import org.paces.data.Stata.Readers.FileElements.*;
import org.paces.data.Stata.Readers.FileElements.Characteristics.DtaCharacteristics;
import org.paces.data.Stata.Readers.StataByteOrder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class NewFormats extends FileConstants {

	protected static final int RELEASE = 3;
	protected static final int BYTEORDER = 3;
	protected static final int NVARS = 2;
	public static final int DATASET_LABEL_LENGTH = 2;
	public static final int DATETIME = 1;
	public static final int DATETIME_ENTRY = 17;

	protected DtaCharacteristics characteristics;
	protected DtaData thedata;
	protected DtaDisplayFormats displayFormats;
	protected DtaMap fileMap;
	protected DtaSortOrder dataSortOrder;
	protected DtaValueLabel valueLabels;
	protected DtaValueLabelNames vallabNames;
	protected DtaVariableLabels varLabels;
	protected DtaVariableNames varnames;
	protected DtaVarTypes dataTypes;
	protected DtaStrLs stBlobs;

	/**
	 * Member used to Parse/Store Blobs
	 */
	public DtaStrLs blobs = null;

	protected static final int RELEASE_OFFSET = ElementTags.getTagValue("odta") +
												ElementTags.getTagValue("oheader") +
												ElementTags.getTagValue("orelease");

	protected static final int BYTEORDER_OFFSET = RELEASE_OFFSET + 3 +
												  ElementTags.getTagValue("crelease") +
			   									  ElementTags.getTagValue("obyteorder");

	protected static final int NVARS_OFFSET = BYTEORDER_OFFSET + 3 +
			ElementTags.getTagValue("cbyteorder") +
			ElementTags.getTagValue("onvars");

	protected static final int NOBS_OFFSET = NVARS_OFFSET + 2 +
			ElementTags.getTagValue("cnvars") +
			ElementTags.getTagValue("onobs");

	public ElementTags etags;

	protected NewFormats() {
		this.etags = new ElementTags();
	}

	/**
	 * Method to access the element tags
	 * @return An ElementTags class object...used to lookup the byte length
	 * of the xml style character tags in the file
	 */
	public ElementTags getEtags() {
		return this.etags;

	}

	/***
	 * Method to return a Map object for type map after the introduction of
	 * Binary Large OBject types in Stata
	 * @return A map with integer keys and string values
	 */
	public Map<Integer, Integer> getDataTypes() {
		Map<Integer, Integer> stataTypes = new HashMap<>();
		for (int i = 1; i < 2045; i++) {
			stataTypes.put(i, i);
		}
		stataTypes.put(32768, 0);
		stataTypes.put(65526, 8);
		stataTypes.put(65527, 4);
		stataTypes.put(65528, 4);
		stataTypes.put(65529, 2);
		stataTypes.put(65530, 1);
		return stataTypes;
	}

	/**
	 * Static method used to parse the file header from files created from
	 * versions 8, 10, or 12 of Stata
	 * The order of the values returned in the list object are:
	 * <table>
	 *     <th>
	 *         <td>Element</td><td>Class/Casting</td>
	 *     </th>
	 *     <tr>
	 *         <td>Release Number</td><td>java.lang.Integer</td>
	 *     </tr>
	 *     <tr>
	 *         <td>Byte Swap/Endianness</td><td>java.nio.ByteOrder</td>
	 *     </tr>
	 *     <tr>
	 *     		<td>Variables</td><td>java.lang.Short</td>
	 *     </tr>
	 *     <tr>
	 *     		<td>Observations</td><td>java.lang.Integer (V117) / java.lang.Long (V118) </td>
	 *     </tr>
	 *     <tr>
	 *     		<td>Dataset Label</td><td>java.lang.String</td>
	 *     </tr>
	 *     <tr>
	 *     		<td>Dataset Timestamp</td><td>java.lang.String</td>
	 *     </tr>
	 *     <tr>
	 *     		<td>Filemap Offset/Position</td><td>java.lang.Integer</td>
	 *     </tr>
	 * </table>
	 * @param stdata The representation of the .dta file on the JVM
	 * @param byteReader A List of byte arrays created by the Load class
	 *                      based on the release version of the file
	 * @return A list of objects that contain the header data and will need
	 * to be recast when being decoded
	 * @throws IOException This will be caught by the class constructor for
	 * the Load class
	 */
	public static List<Object> readHeader(ByteBuffer stdata,
											List<byte[]> byteReader) throws IOException {

		// List object used to return the header values
		List<Object> values = new ArrayList<>();

		// Set the position of the file at the beginning
		stdata.position(RELEASE_OFFSET);

		// Reads the bytes that fit into the first byte array
		stdata.get(byteReader.get(0));

		// Converts the value to an Integer value
		Integer release = Integer.valueOf(new String(byteReader.get(0)));

		// Moves the byte position of the reader to the next set of values
		stdata.position(BYTEORDER_OFFSET);

		// Reads the byte order into the next byte array
		stdata.get(byteReader.get(1));

		// Stores the byte order as a String
		String bo = new String(byteReader.get(1));

		// Create a byte order variable using the string in position 1 of
		// the bytereader (this is the header variable passed to the method)
		StataByteOrder sbo = new StataByteOrder(bo);

		// Sets the current byteswap order
		stdata.order(sbo.swapto);

		// Moves the reader to the bytes containing the number of variables
		stdata.position(NVARS_OFFSET);

		// Reads the number of variables into the two-byte array in the list
		Short K = stdata.getShort();

		// Adds the integer value of the release in index 0
		values.add(0, release);

		// Adds the byte order to use when reading the data
		values.add(1, sbo.swapto);

		// Add the number of variables to the list object
		values.add(2, K);

		// Moves the reader to the position where the number of observations
		// are stored
		stdata.position(NOBS_OFFSET);

		// Files created by Stata 13 use 4-byte integer number of observations
		if (release == 117) {

			// Parse the number of observations
			Integer N = stdata.getInt();

			// Add the number of observations to the list object
			values.add(3, N);

		// If the release number is later number of observations is a long
		} else {

			// Newest version of Stata uses 8 byte number of observations
			Long N = stdata.getLong();

			// Add the number of observations to the list object
			values.add(3, N);

		} // End ELSE Block for Stata files generated by Version >= 14

		// Adds the byte length of the </N> and <label> tags to the current
		// position
		Integer offset = stdata.position() + ElementTags.getTagValue
				("cnobs") + ElementTags.getTagValue("odatalabel");

		// Moves to that position
		stdata.position(offset);

		// Create byte array variable for dataset label
		byte[] dlab;

		// IF file created by Stata 13
		if (release == 117) {

			// Length of the data set label is a 1 byte integer
			Byte lablength = stdata.get();

			// Initialize new byte array to read the dataset label
			dlab = new byte[(int) lablength];

		// If file created with Stata 14 or later
		} else {

			// Length of the data set label is a 2 byte integer
			Short lablength = stdata.getShort();

			// Initialize new byte array to read the dataset label
			dlab = new byte[(int) lablength];

		} // End ELSE Block for Stata 14 and Later files

		// Parse the label into a string
		String datalabel = String.valueOf(stdata.get(dlab));

		// Add the dataset label to the list object
		values.add(4, datalabel);

		// Update the offset after reading the dataset label
		offset = stdata.position() + ElementTags.getTagValue
				("cdatalabel") + ElementTags.getTagValue("otimestamp");

		// Move the byte reader
		stdata.position(offset);

		// Parse the dataset timestamp
		Byte hasTimeStamp = stdata.get();

		// byte array to store the timestamp
		byte[] tsdata = new byte[17];

		// Container for the string value used to represent the timestamp
		// element
		String timestamp;

		// If the first byte is 17 the file has a time stamp
		if (hasTimeStamp == 17) {

			// Convert the byte array with the time stamp into a String
			timestamp = String.valueOf(stdata.get(tsdata));

		// If it is any other value
		} else {

			// Create a zero length string
			timestamp = "";

		} // End ELSE Block

		// Add the time stamp to the list object
		values.add(5, timestamp);

		// Moves to the start of the Map element
		offset = stdata.position() + ElementTags.getTagValue("ctimestamp");

		// Adds the offset for the map element in the last position of the list
		values.add(6, offset);

		// Return the list object
		return values;

	} // End of Method declaration


}
