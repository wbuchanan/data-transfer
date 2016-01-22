package org.paces.data.Stata.Version;

import org.paces.data.Stata.Readers.DtaFileParser;
import org.paces.data.Stata.Readers.FileElements.*;

import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class V113 extends OldFormats implements FileVersion, DtaFileParser {

	/**
	 * Member containing the RandomAccessFile class object used to read the
	 * bytes from the .dta file.
	 */
	private FileChannel dataset;

	/**
	 * Member containing the release/version number of the file
	 */
	private Integer release;

	/**
	 * Member containing the endianness used to write the bytes of the file
	 */
	private ByteOrder endian;

	/**
	 * Member containing the number of variables in the data set
	 */
	private Short K;

	/**
	 * Member containing the number of observations in the data set
	 */
	private Integer N;

	/**
	 * Member containing the datalabel element if it exists
	 */
	private String datasetLabel;

	/**
	 * Member containing the timestamp element if it exists
	 */
	private String datasetTimeStamp;

	/**
	 * Member containing the position at while the map element begins.
	 */
	private Integer mapOffset;

	/**
	 * Class constructor for .dta file type 113.
	 * The class is constructed by the .getVersion method of the FileFormats
	 * class
	 * @param stdata A RandomAccessFile object used to parse/read the bytes
	 *                  from the .dta file
	 * @param release The release number of the file
	 * @param endian The endianness the file was written with
	 * @param K The number of variables in the file
	 * @param N The number of observations in the file
	 * @param datasetLabel The dataset label from the file if it exists
	 * @param datasetTimeStamp The timestamp from the file if it exists
	 * @param mapOffset The position where the map element of the .dta file
	 *                     begins
	 * @see org.paces.data.Stata.Version.FileFormats
	 */
	public V113(FileChannel stdata, Integer release, ByteOrder endian,
				Short K, Integer N, String datasetLabel, String datasetTimeStamp,
				Integer mapOffset) {

		this.dataset = stdata;
		this.release = release;
		this.endian = endian;
		this.K = K;
		this.N = N;
		this.datasetLabel = datasetLabel;
		this.datasetTimeStamp = datasetTimeStamp;
		this.mapOffset = mapOffset;

	} // End of Class Constructor declaration

	/**
	 * Method used to initialize a DtaMap object.  This object is used to
	 * represent and parse the {@literal <map>...</map>} element from the .dta
	 * file. This version of the method should be used for files with release
	 * numbers 117 and 118.
	 *
	 * @param stData    An object representing a .dta file of a specific release
	 *                  number
	 * @param mapOffset The byte position where the map element begins in the
	 *                  file
	 * @param sbo       A ByteOrder class value used to specify the order in
	 *                  which the byte values of the file should be read.  This
	 *                  should be passed from the Load class and will be the
	 *                  value from StataByteOrder.swapto.
	 * @return An initialized DtaMap object that can be used to navigate the
	 * byte positions for data elements in the .dta file
	 */
	@Override
	public DtaMap setFileMap(FileVersion<?> stData, Long mapOffset, ByteOrder sbo) {
		return null;
	}

	/**
	 * Method used to initialize a DtaMap object.  This object is used to
	 * represent and parse the {@literal <map>...</map>} element from the .dta
	 * file.
	 *
	 * @param stData    An object representing a .dta file of a specific release
	 *                  number
	 * @param mapOffset The byte position where the map element begins in the
	 *                  file
	 * @param nvars     The number of variables in the dataset (this is only
	 *                  necessary/used for files created with Stata's 8 through
	 *                  12)
	 * @param sbo       A ByteOrder class value used to specify the order in
	 *                  which the byte values of the file should be read.  This
	 *                  should be passed from the Load class and will be the
	 *                  value from StataByteOrder.swapto.
	 * @return An initialized DtaMap object that can be used to navigate the
	 * byte positions for data elements in the .dta file
	 */
	@Override
	public DtaMap setFileMap(FileVersion<?> stData, Long mapOffset, Short nvars, ByteOrder sbo) {
		return null;
	}

	/**
	 * Method used to initialize an object representing the data types for the
	 * variables in the dataset.
	 *
	 * @param stData The version specific object used to control
	 *               reading/parsing
	 * @param nvars  The number of variables in the dataset
	 * @param sbo    A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should be
	 *               passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaVarTypes object (used to determine how to
	 * read/cast the bytes of the file)
	 */
	@Override
	public DtaVarTypes setDataTypes(FileVersion<?> stData, Short nvars, ByteOrder sbo) {
		return null;
	}

	/**
	 * Method used to initialize an object containing the variable names from
	 * the Stata dataset
	 *
	 * @param stData The version specific file used to control reading/parsing
	 * @param nvars  The number of variables in the data set
	 * @param sbo    A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should be
	 *               passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaVariableNames object
	 */
	@Override
	public DtaVariableNames setVariableNames(FileVersion<?> stData, Short nvars, ByteOrder sbo) {
		return null;
	}

	/**
	 * Method used to initialize an object containing the order in which the
	 * data are sorted (if any) based on variable names
	 *
	 * @param stData The version specific file used to control reading/parsing
	 * @param nvars  The number of variables in the data set
	 * @param sbo    A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should be
	 *               passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaSortOrder object
	 */
	@Override
	public DtaSortOrder setSortOrder(FileVersion<?> stData, Short nvars, ByteOrder sbo) {
		return null;
	}

	/**
	 * Method used to initialize an object containing the display formats for
	 * the variables in the data set
	 *
	 * @param stData The version specific file used to control reading/parsing
	 * @param nvars  The number of variables in the data set
	 * @param sbo    A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should be
	 *               passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaDisplayFormats object
	 */
	@Override
	public DtaDisplayFormats setDisplayFmts(FileVersion<?> stData, Short nvars, ByteOrder sbo) {
		return null;
	}

	/**
	 * Method used to initialize an object containing the value label names used
	 * in the .dta file
	 *
	 * @param stData The version specific file used to control reading/parsing
	 * @param nvars  The number of variables in the data set
	 * @param sbo    A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should be
	 *               passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaValueLabelNames object
	 */
	@Override
	public DtaValueLabelNames setValLabNames(FileVersion<?> stData, Short nvars, ByteOrder sbo) {
		return null;
	}

	/**
	 * Method used to initialize an object containing the variable labels from
	 * the Stata dataset
	 *
	 * @param stData The version specific file used to control reading/parsing
	 * @param nvars  The number of variables in the data set
	 * @param sbo    A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should be
	 *               passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaVariableLabels object
	 */
	@Override
	public DtaVariableLabels setVarLabs(FileVersion<?> stData, Short nvars, ByteOrder sbo) {
		return null;
	}

	/**
	 * Method used to initialize an object containing the .dta characteristic
	 * elements
	 *
	 * @param stData The version specific file used to control reading/parsing
	 * @param sbo    A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should be
	 *               passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaCharacteristics object
	 */
	@Override
	public DtaCharacteristics setCharacteristics(FileVersion<?> stData, ByteOrder sbo) {
		return null;
	}

	/**
	 * Method used to initialize an object containing the strLs (if any) in the
	 * dataset.
	 *
	 * @param stData The version specific file used to control reading/parsing
	 * @param sbo    A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should be
	 *               passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaStrLs object
	 */
	@Override
	public DtaStrLs setStrLs(FileVersion<?> stData, ByteOrder sbo) {
		return null;
	}

	/**
	 * Method used to initialize an object containing the value label tables
	 * from the .dta file
	 *
	 * @param stData The version specific file used to control reading/parsing
	 * @param sbo    A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should be
	 *               passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaValueLabel object
	 */
	@Override
	public DtaValueLabel setValueLabels(FileVersion<?> stData, ByteOrder sbo) {
		return null;
	}

	/**
	 * Method used to initialize an object containing the data from the .dta
	 * file
	 *
	 * @param stData The version specific file used to control reading/parsing
	 * @param nvars  The number of variables in the data set
	 * @param nobs   The number of observations in the data set
	 * @param sbo    A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should be
	 *               passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaData class object
	 */
	@Override
	public DtaData setData(FileVersion<?> stData, Short nvars, Long nobs, ByteOrder sbo) {
		return null;
	}

	/**
	 * Method used to access the DtaMap object of the class
	 *
	 * @return A DtaMap object
	 */
	@Override
	public DtaMap getFileMap() {
		return null;
	}

	/**
	 * Method used to access the DtaVarTypes object of the class
	 *
	 * @return A DtaVarTypes object
	 */
	@Override
	public DtaVarTypes getDtaTypes() {
		return null;
	}

	/**
	 * Method used to access the DtaVariableNames object of the class
	 *
	 * @return A DtaVariableNames object
	 */
	@Override
	public DtaVariableNames getVariableNames() {
		return null;
	}

	/**
	 * Method used to access the DtaSortOrder object of the class
	 *
	 * @return A DtaSortOrder object
	 */
	@Override
	public DtaSortOrder getSortOrder() {
		return null;
	}

	/**
	 * Method used to access the DtaDisplayFormats object of the class
	 *
	 * @return A DtaDisplayFormats object
	 */
	@Override
	public DtaDisplayFormats getDisplayFmts() {
		return null;
	}

	/**
	 * Method used to access the DtaValueLabelNames object of the class
	 *
	 * @return A DtaValueLabelNames object
	 */
	@Override
	public DtaValueLabelNames getValLabNames() {
		return null;
	}

	/**
	 * Method used to access the DtaVariableLabels object of the class
	 *
	 * @return A DtaVariableLabels object
	 */
	@Override
	public DtaVariableLabels getVarLabs() {
		return null;
	}

	/**
	 * Method used to access the DtaCharacteristics object of the class
	 *
	 * @return A DtaCharacteristics object
	 */
	@Override
	public DtaCharacteristics getCharacteristics() {
		return null;
	}

	/**
	 * Method used to access the DtaStrLs object of the class
	 *
	 * @return A DtaStrLs object
	 */
	@Override
	public DtaStrLs getStrLs() {
		return null;
	}

	/**
	 * Method used to access the DtaValueLabel object of the class
	 *
	 * @return A DtaValueLabel object
	 */
	@Override
	public DtaValueLabel getValueLabels() {
		return null;
	}

	/**
	 * Method used to access the DtaData object of the class
	 *
	 * @return A DtaData object
	 */
	@Override
	public DtaData getData() {
		return null;
	}

	/**
	 * Method used to retrieve the swapto member of the StataByteOrder class
	 *
	 * @return A ByteOrder variable used to define the order in which the bytes
	 * from the .dta file should be read.
	 */
	@Override
	public ByteOrder getByteSwap() {
		return this.endian;
	}

	/**
	 * Method used to access the byte representation of the .dta file being
	 * read
	 *
	 * @return A RandomAccessFile object representing the .dta file
	 */
	@Override
	public FileChannel getDtaFile() {
		return this.dataset;
	}

	/**
	 * Method used to retrieve the version number
	 *
	 * @return The integer value of the release number
	 */
	@Override
	public Integer getVersionNumber() {
		return this.release;
	}

	/**
	 * Method used to retrieve the number of variables from the Stata File
	 *
	 * @return An integer value of type short containing the number of variables
	 * in the Stata data set
	 */
	@Override
	public Short getNumVars() {
		return this.K;
	}

	/**
	 * Method used to retrieve the number of observations in the Stata file
	 * @return Returns a long valued integer containing the number of
	 * observations in the dataset
	 */
	public Integer getNumObs() {
		return this.N;
	};

	/**
	 * Method used to retrieve the datalabel element from the Stata file if one
	 * exists
	 *
	 * @return Returns an empty string if no datalabel is in the file, else
	 * provides the label
	 */
	@Override
	public String getDatasetLabel() {
		return this.datasetLabel;
	}

	/**
	 * Method used to retrieve the dataset timestamp from the Stata file if it
	 * exists
	 *
	 * @return Returns either an empty string or the time stamp element from the
	 * Stata data set
	 */
	@Override
	public String getDatasetTimeStamp() {
		return this.datasetTimeStamp;
	}

	/**
	 * Method used to retrieve the position in the file where the offset map
	 * exists
	 *
	 * @return A long valued integer containing the start of the Map element of
	 * the file.  For Stata 13 and 14 files this should be the position where
	 * {@literal <map>} appears.  For older versions of Stata this should be the
	 * position where the file map bytes begin.
	 */
	@Override
	public Integer getMapOffset() {
		return this.mapOffset;
	}

}

