package org.paces.data.Stata.Version;

import org.paces.data.Stata.Readers.FileElements.*;

/**
 * Created by billy on 12/27/15.
 */
public class FileConstants {

	/**
	 * Member used to contain the number of bytes required to store the
	 * number of variables in the file
	 */
	protected static final int NVARS = 2;

	/**
	 * Member used to store the data set characteristics
	 */
	DtaCharacteristics characteristics = null;

	/**
	 * Member used to store the data from the dataset
	 */
	DtaData stataData = null;

	/**
	 * Member used to store the file map
	 */
	DtaMap dtaMap = null;

	/**
	 * Member used to store the display formats elements of the file
	 */
	DtaDisplayFormats displayFormats = null;

	/**
	 * Member used to store the value label names
	 */
	DtaValueLabel valueLabels = null;

	/**
	 * Member used to store variable labels
	 */
	DtaVariableLabels varLabels = null;

	/**
	 * Member used to store the types of the data elements
	 */
	DtaVarTypes stDataTypes = null;

	/**
	 * Member used to store the sort order element of the file
	 */
	DtaSortOrder sortOrder = null;

	/**
	 * Member used to store the value label names elements
	 */
	DtaValueLabelNames vallabNames = null;

	/**
	 * Member used to store variable names
	 */
	DtaVariableNames varnames = null;

	/**
	 * Member used to store the number of observations in the file
	 */
	Long nobs = null;

	/**
	 * Member used to store the number of variables in the file
	 */
	Integer nvars = null;

	/**
	 * Member used to store the dataset label
	 */
	String datasetLabel = "";

	/**
	 * Member used to store the string formatted timestamp from the file
	 */
	String timestamp = "";

}
