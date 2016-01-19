package org.paces.data.Stata.Version;

import java.io.RandomAccessFile;
import java.nio.ByteOrder;

/**
 * Interface used to construct file version objects
 * @author Billy Buchanan
 * @version 0.0.0
 */
public abstract interface FileVersion<T> {

	/**
	 * Method used to retrieve the swapto member of the StataByteOrder class
	 * @return A ByteOrder variable used to define the order in which the
	 * bytes from the .dta file should be read.
	 */
	public ByteOrder getByteSwap();

	/**
	 * Method used to access the byte representation of the .dta file being read
	 * @return A RandomAccessFile object representing the .dta file
	 */
	public RandomAccessFile getDtaFile();

	/**
	 * Method used to retrieve the version number
	 * @return The integer value of the release number
	 */
	public Integer getVersionNumber();

	/**
	 * Method used to retrieve the endianness of the Stata file
	 * @return String containing either MSF (Big Endian) or LSF (Little Endian)
	 */
	public String getFileEndian();

	/**
	 * Method used to retrieve the number of variables from the Stata File
	 * @return An integer value of type short containing the number of
	 * variables in the Stata data set
	 */
	public Short getNumVars();

	/**
	 * Method used to retrieve the number of observations in the Stata file
	 * @return Returns a long valued integer containing the number of
	 * observations in the dataset
	 */
	public Long getNumObs();

	/**
	 * Method used to retrieve the datalabel element from the Stata file if
	 * one exists
	 * @return Returns an empty string if no datalabel is in the file, else
	 * provides the label
	 */
	public String getDatasetLabel();

	/**
	 * Method used to retrieve the dataset timestamp from the Stata file if
	 * it exists
	 * @return Returns either an empty string or the time stamp element from
	 * the Stata data set
	 */
	public String getDatasetTimeStamp();

	/**
	 * Method used to retrieve the position in the file where the offset map
	 * exists
	 * @return A long valued integer containing the start of the Map element
	 * of the file.  For Stata 13 and 14 files this should be the position
	 * where {@literal <map>} appears.  For older versions of Stata this
	 * should be the position where the file map bytes begin.
	 */
	public Long getMapOffset();

}
