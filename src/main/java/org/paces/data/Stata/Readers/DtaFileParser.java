package org.paces.data.Stata.Readers;

import org.paces.data.Stata.Readers.FileElements.*;
import org.paces.data.Stata.Version.FileVersion;

import java.nio.ByteOrder;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public abstract interface DtaFileParser {

	/**
	 * Method used to initialize a DtaMap object.  This object is used to
	 * represent and parse the {@literal <map>...</map>} element from the
	 * .dta file. This version of the method should be used for files with
	 * release numbers 117 and 118.
	 * @param stData An object representing a .dta file of a specific release
	 *                  number
	 * @param mapOffset The byte position where the map element begins in the
	 *                     file
	 * @param sbo A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should
	 *               be passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaMap object that can be used to navigate the
	 * byte positions for data elements in the .dta file
	 */
	DtaMap setFileMap(FileVersion<?> stData, Long mapOffset, ByteOrder sbo);

	/**
	 * Method used to initialize a DtaMap object.  This object is used to
	 * represent and parse the {@literal <map>...</map>} element from the
	 * .dta file.
	 * @param stData An object representing a .dta file of a specific release
	 *                  number
	 * @param mapOffset The byte position where the map element begins in the
	 *                     file
	 * @param nvars The number of variables in the dataset (this is only
	 *                 necessary/used for files created with Stata's 8
	 *                 through 12)
	 * @param sbo A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should
	 *               be passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaMap object that can be used to navigate the
	 * byte positions for data elements in the .dta file
	 */
	DtaMap setFileMap(FileVersion<?> stData, Long mapOffset, Short nvars, ByteOrder sbo);

	/**
	 * Method used to initialize an object representing the data types for
	 * the variables in the dataset.
	 * @param stData The version specific object used to control reading/parsing
	 * @param nvars The number of variables in the dataset
	 * @param sbo A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should
	 *               be passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaVarTypes object (used to determine how to
	 * read/cast the bytes of the file)
	 */
	DtaVarTypes setDataTypes(FileVersion<?> stData, Short nvars, ByteOrder sbo);

	/**
	 * Method used to initialize an object containing the variable names from
	 * the Stata dataset
	 * @param stData The version specific file used to control reading/parsing
	 * @param nvars The number of variables in the data set
	 * @param sbo A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should
	 *               be passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaVariableNames object
	 */
	DtaVariableNames setVariableNames(FileVersion<?> stData, Short nvars, ByteOrder sbo);

	/**
	 * Method used to initialize an object containing the order in which the
	 * data are sorted (if any) based on variable names
	 * @param stData The version specific file used to control reading/parsing
	 * @param nvars The number of variables in the data set
	 * @param sbo A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should
	 *               be passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaSortOrder object
	 */
	DtaSortOrder setSortOrder(FileVersion<?> stData, Short nvars, ByteOrder sbo);

	/**
	 * Method used to initialize an object containing the display formats for
	 * the variables in the data set
	 * @param stData The version specific file used to control reading/parsing
	 * @param nvars The number of variables in the data set
	 * @param sbo A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should
	 *               be passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaDisplayFormats object
	 */
	DtaDisplayFormats setDisplayFmts(FileVersion<?> stData, Short nvars, ByteOrder sbo);

	/**
	 * Method used to initialize an object containing the value label names
	 * used in the .dta file
	 * @param stData The version specific file used to control reading/parsing
	 * @param nvars The number of variables in the data set
	 * @param sbo A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should
	 *               be passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaValueLabelNames object
	 */
	DtaValueLabelNames setValLabNames(FileVersion<?> stData, Short nvars, ByteOrder sbo);

	/**
	 * Method used to initialize an object containing the variable labels from
	 * the Stata dataset
	 * @param stData The version specific file used to control reading/parsing
	 * @param nvars The number of variables in the data set
	 * @param sbo A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should
	 *               be passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaVariableLabels object
	 */
	DtaVariableLabels setVarLabs(FileVersion<?> stData, Short nvars, ByteOrder sbo);

	/**
	 * Method used to initialize an object containing the .dta characteristic
	 * elements
	 * @param stData The version specific file used to control reading/parsing
	 * @param sbo A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should
	 *               be passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaCharacteristics object
	 */
	DtaCharacteristics setCharacteristics(FileVersion<?> stData, ByteOrder sbo);

	/**
	 * Method used to initialize an object containing the strLs (if any) in
	 * the dataset.
	 * @param stData The version specific file used to control reading/parsing
	 * @param sbo A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should
	 *               be passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaStrLs object
	 */
	DtaStrLs setStrLs(FileVersion<?> stData, ByteOrder sbo);

	/**
	 * Method used to initialize an object containing the value label tables
	 * from the .dta file
	 * @param stData The version specific file used to control reading/parsing
	 * @param sbo A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should
	 *               be passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaValueLabel object
	 */
	DtaValueLabel setValueLabels(FileVersion<?> stData, ByteOrder sbo);

	/**
	 * Method used to initialize an object containing the data from the .dta
	 * file
	 * @param stData The version specific file used to control reading/parsing
	 * @param nvars The number of variables in the data set
	 * @param nobs The number of observations in the data set
	 * @param sbo A ByteOrder class value used to specify the order in which
	 *               the byte values of the file should be read.  This should
	 *               be passed from the Load class and will be the value from
	 *               StataByteOrder.swapto.
	 * @return An initialized DtaData class object
	 */
	DtaData setData(FileVersion<?> stData, Short nvars, Long nobs, ByteOrder sbo);

	/**
	 * Method used to access the DtaMap object of the class
	 * @return A DtaMap object
	 */
	DtaMap getFileMap();

	/**
	 * Method used to access the DtaVarTypes object of the class
	 * @return A DtaVarTypes object
	 */
	DtaVarTypes getDtaTypes();

	/**
	 * Method used to access the DtaVariableNames object of the class
	 * @return A DtaVariableNames object
	 */
	DtaVariableNames getVariableNames();

	/**
	 * Method used to access the DtaSortOrder object of the class
	 * @return A DtaSortOrder object
	 */
	DtaSortOrder getSortOrder();

	/**
	 * Method used to access the DtaDisplayFormats object of the class
	 * @return A DtaDisplayFormats object
	 */
	DtaDisplayFormats getDisplayFmts();

	/**
	 * Method used to access the DtaValueLabelNames object of the class
	 * @return A DtaValueLabelNames object
	 */
	DtaValueLabelNames getValLabNames();

	/**
	 * Method used to access the DtaVariableLabels object of the class
	 * @return A DtaVariableLabels object
	 */
	DtaVariableLabels getVarLabs();

	/**
	 * Method used to access the DtaCharacteristics object of the class
	 * @return A DtaCharacteristics object
	 */
	DtaCharacteristics getCharacteristics();

	/**
	 * Method used to access the DtaStrLs object of the class
	 * @return A DtaStrLs object
	 */
	DtaStrLs getStrLs();

	/**
	 * Method used to access the DtaValueLabel object of the class
	 * @return A DtaValueLabel object
	 */
	DtaValueLabel getValueLabels();

	/**
	 * Method used to access the DtaData object of the class
	 * @return A DtaData object
	 */
	DtaData getData();

} // End of Interface declaration
