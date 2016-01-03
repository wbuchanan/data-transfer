package org.paces.data.Stata.Version;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by billy on 12/27/15.
 */
public abstract class OldFormats extends FileConstants {

	/***
	 * Number of bytes to reserve for reading the release ID from the file
	 */
	protected static final int RELEASE = 1;

	/***
	 * Number of bytes to reserve for reading the endianness indicator from
	 * the file header
	 */
	protected static final int BYTEORDER = 1;

	/***
	 * Number of bytes to reserve for reading the filetype field from the
	 * file header
	 */
	protected static final int FILETYPE = 1;

	/***
	 * Number of bytes to reserve for padding in the file header
	 */
	protected static final int BLANK = 1;

	/***
	 * Number of bytes to reserve for reading the number of variables in the
	 * data set
	 */
	protected static final int NVARS = 2;

	/***
	 * Number of bytes to reserve for reading the number of observations in
	 * the data set
	 */
	protected static final int NOBS = 4;

	/***
	 * Number of bytes to reserve for reading the dataset label from the file
	 */
	protected static final int DATA_LABEL = 81;

	/***
	 * Number of bytes to reserve for reading the file's timestamp field
	 */
	protected static final int TIME_STAMP = 18;

	/***
	 * Method used to store the data type list when reading from disk
	 * @param nvars An integer containing the number of variables in the data
	 *                 set
	 * @return A byte array with # variables elements used to store the data
	 * types for the corresponding variables
	 */
	public byte[] getTypeListContainer(Integer nvars) {
		return new byte[nvars];
	}

	/***
	 * Method used to store the variable name list
	 * @param nvars An integer containing the number of variables in the data
	 *                 set
	 * @return A byte array with # variables elements each 32 bytes long
	 * (will need to add 1 byte to seek the next position and avoid writing
	 * any binary zeroes into the file)
	 */
	public byte[][] getVarListContainer(Integer nvars) {
		return new byte[nvars][32];
	}

	/***
	 * Method used to store the display format of the data. <it>In version
	 * 113 formatted files, formats beginning with \%d are Date/DateTime
	 * formats</it>.
	 * @param nvars An integer containing the number of variables in the data
	 *                 set
	 * @return A byte array with # variables elements each 49 bytes wide used
	 * to store the display format of the data.
	 */
	public byte[][] getFormatListContainer(Integer nvars) {
		return new byte[nvars][49];
	}

	/***
	 * Method used to store the value label names associated with a given
	 * variable
	 * @param nvars An integer containing the number of variables in the data
	 *                 set
	 * @return A byte array with # variables elements each of 32 bytes in
	 * length containing the name of the associated variable label(s)
	 */
	public byte[][] getLabelListContainer(Integer nvars) {
		return new byte[nvars][33];
	}

	/***
	 * Method used to store the sort order list of variables
	 * @param nvars An integer containing the number of variables in the data
	 *                 set
	 * @return A byte array with # variables elements containing 2-byte
	 * unsigned ints with the variable index indicating the order in which
	 * data are sorted.
	 */
	public byte[][] getSortOrderListContaint(Integer nvars) {
		return new byte[nvars + 1][2];
	}

	/***
	 * Method to return a Map object for type map prior to the introduction
	 * of Binary Large OBject types in Stata
	 * @return A map with integer keys and string values
	 */
	public Map<Integer, Integer> getDataTypes() {
		Map<Integer, Integer> stataTypes = new HashMap<>();
		for (int i = 1; i < 244; i++) {
			stataTypes.put(i, i);
		}
		stataTypes.put(251, 1);
		stataTypes.put(252, 2);
		stataTypes.put(253, 4);
		stataTypes.put(254, 4);
		stataTypes.put(255, 8);
		return stataTypes;
	}

}
