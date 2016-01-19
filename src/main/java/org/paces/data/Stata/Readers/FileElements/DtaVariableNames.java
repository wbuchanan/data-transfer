package org.paces.data.Stata.Readers.FileElements;

import org.paces.data.Stata.Readers.StConvert;
import org.paces.data.Stata.Version.FileVersion;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class DtaVariableNames {

	/**
	 * Member used to store the variable names.
	 */
	private List<String> varnames = new ArrayList<>();

	/**
	 * Class constructor method for DtaVariableNames object
	 * @param stdata A release version specific object
	 * @param offset The offset indicating the byte position where
	 *                         variable names start
	 * @param numberVariables The number of variables in the dataset
	 * @throws IOException An exception thrown if there is an error reading
	 * the file.
	 */
	DtaVariableNames(FileVersion<?> stdata, Long offset, Integer numberVariables)
			throws IOException {

		// Creates connection to the dataset file
		RandomAccessFile x = stdata.getDtaFile();

		// Moves the byte reader to the position where variable names start
		x.seek(offset);

		// Loop over the number of variables in the dataset
		for (int i = 0; i < numberVariables; i++) {

			// Get the right size byte array from the getByteArray method
			byte[] vnm = getByteArray(stdata.getVersionNumber());

			// Read bytes into the byte array
			x.read(vnm);

			// Convert the bytes to a string and add it to the list of
			// variable names
			this.varnames.add(i, StConvert.toStata(vnm, stdata.getByteSwap(), ""));

		} // End Loop over the variables

		// Closes the connection to the file
		x.close();

	} // End Method declaration

	/**
	 * Method used to set the length of the byte array used to store each
	 * variable name
	 * @param version An integer value containing the release number value
	 * @return A byte array of length 129 (for Stata 14 or later) or 33 bytes
	 * used to store an individual variable name
	 */
	public byte[] getByteArray(Integer version) {

		// Switch statement
		switch (version) {

			// For files written in Stata 14 or later
			case 118:

				// Return an array that is 129 bytes long
				return new byte[129];

			// For files written by other Statas
			default:

				// Return an array that is 33 bytes long
				return new byte[33];

		} // End Switch statement

	} // End method declaration

	/**
	 * Method to return a List object with all of the parsed variable names
	 * @return A List of String objects containing the variable names from
	 * the data set
	 */
	public List<String> getVarNames() {
		return this.varnames;
	}

	/**
	 * Method used to retrieve a single variable name from the object
	 * @param index The variable index for which the variable name is requested
	 * @return A String object containing the variable name at the index
	 * passed to the method
	 */
	public String getVarName(Integer index) {
		return this.varnames.get(index);
	}

}
