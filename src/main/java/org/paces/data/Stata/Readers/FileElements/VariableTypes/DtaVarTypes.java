package org.paces.data.Stata.Readers.FileElements.VariableTypes;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public interface DtaVarTypes {

	/**
	 * Method to generate map from variable types to the number of bytes
	 * required to read each type into memory; Also populates the member
	 * containing the method strings used to dispatch the appropriate parsers
	 */
	void setTypeMaps() throws NoSuchMethodException;

	/**
	 * Sets a List of Integers containing the data types
	 * @param nvars The number of variables in the dataset
	 */
	void setTypes(Short nvars);

	/**
	 * Method used to set the method object used for parsing strL elements in
	 * within the data tags of the file.
	 * @param filetype The release version number of the .dta file
	 */
	void setStrlType(Integer filetype);

	/**
	 * Retrieves the integer values of the data types
	 * @return A list of Integers corresponding to the type mappings
	 * specified in the .dta file specification
	 */
	List<Integer> getTypeMaps();

	/**
	 * Retrieves a list of method objects that can be used to read the data
	 * using reflections
	 * @return A list of method objects that control the dispatch of the
	 * parse methods.  Note, for string datum, the byte arrays to store the
	 * datum must still be initialized;
	 */
	List<Method> getParseMethods();

	/**
	 * Method to return a List of booleans indicating if the variables are
	 * strings or numeric types
	 * @return A list of booleans that are true if the variables are string
	 * or false if they are numeric
	 */
	List<Boolean> areStrings();

	/**
	 * Method to test whether individual variable is of type String
	 * @param idx The variable type index to test
	 * @return A boolean indicating if variable is of type String
	 */
	Boolean isString(Integer idx);

	/**
	 * Method used to retrive the byte length required to store the datum
	 * @param type The .dta integer valued type
	 * @return An Integer containing the length of bytes needed to read this
	 * datum
	 */
	Integer getByteLength(Integer type);

	/**
	 * Method to return a list object containing the byte lengths for each
	 * variable
	 * @return A list of byte lengths
	 */
	List<Integer> getLengths();

	/**
	 * Accesses a scalar containing the total number of bytes allocated for
	 * each record
	 * @return A long valued integer number of bytes
	 */
	Long getRecordLength();

	/**
	 * Computes the total byte length of the record based on the byte lengths
	 * of the individual variables
	 */
	void setRecordLength();

}
