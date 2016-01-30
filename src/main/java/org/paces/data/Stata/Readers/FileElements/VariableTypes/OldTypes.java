package org.paces.data.Stata.Readers.FileElements.VariableTypes;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class OldTypes implements DtaVarTypes {

	/**
	 * Member used to store the variable types from the dataset
	 */
	private List<Integer> types = new ArrayList<>();

	/**
	 * Member used to store method objects for parsing the data
	 */
	private static List<Method> parseMethods = new ArrayList<>();

	/**
	 * Member used to store booleans for string variable indicators
	 */
	private List<Boolean> stringType = new ArrayList<>();

	private List<Integer> bytelen = new ArrayList<>();

	/**
	 * Member used to identify the type casting of the variable
	 */
	protected static ConcurrentSkipListMap<Integer, Integer> map = new ConcurrentSkipListMap<>();

	/**
	 * Member used to dispatch parsing methods based on the type
	 */
	protected static ConcurrentSkipListMap<Integer, Method> methods = new ConcurrentSkipListMap<>();

	/**
	 * Member containing the missing value labels (masks)
	 */
	protected String[] missings = {".", ".a", ".b", ".c", ".d", ".e", ".f", ".g",
			".h", ".i", ".j", ".k", ".l", ".m", ".n", ".o", ".p", ".q", ".r",
			".s", ".t", ".u", ".v", ".w", ".x", ".y", ".z"};

	/**
	 * Byte Buffer used to initialize methods via reflections
	 */
	private ByteBuffer buffObject;

	private Long recordlength = 0L;

	public OldTypes(ByteBuffer bytes, Short nvars) {
		this.buffObject = bytes;
		setTypes(nvars);
	}

	/**
	 * Method to generate map from variable types to the number of bytes
	 * required to read each type into memory; Also populates the member
	 * containing the method strings used to dispatch the appropriate parsers
	 */
	@Override
	public void setTypeMaps() {
		try {
			Method getByte = buffObject.getClass().getMethod("get");
			Method getDouble = buffObject.getClass().getMethod("getDouble");
			Method getFloat = buffObject.getClass().getMethod("getFloat");
			Method getInt = buffObject.getClass().getMethod("getInt");
			Method getShort = buffObject.getClass().getMethod("getShort");
			Method getString = buffObject.getClass().getMethod("get", byte[].class);
			for (Integer i = 1; i < 245; i++) {
				map.put(i, i);
				methods.put(i, getString);
			}
			map.put(251, 1);
			map.put(252, 2);
			map.put(253, 4);
			map.put(254, 4);
			map.put(255, 8);
			methods.put(251, getByte);
			methods.put(252, getShort);
			methods.put(253, getInt);
			methods.put(254, getFloat);
			methods.put(255, getDouble);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets a List of Integers containing the data types
	 */
	@Override
	public void setTypes(Short nvars) {
		for(int i = 0; i < nvars; i++) {
			Integer theType = (int) this.buffObject.getShort();
			types.add(i, theType);
			parseMethods.add(i, methods.get(theType));
			if (theType <= 32768) this.stringType.add(i, true);
			else this.stringType.add(i, false);
			this.bytelen.add(i, map.get(theType));
		}
	}

	/**
	 * Method used to set the method object used for parsing strL elements in
	 * within the data tags of the file.
	 *
	 * @param filetype The release version number of the .dta file
	 */
	@Override
	public void setStrlType(Integer filetype) {
	}

	/**
	 * Retrieves the integer values of the data types
	 *
	 * @return A list of Integers corresponding to the type mappings specified
	 * in the .dta file specification
	 */
	@Override
	public List<Integer> getTypeMaps() {
		return this.types;
	}

	/**
	 * Retrieves a list of method objects that can be used to read the data
	 * using reflections
	 *
	 * @return A list of method objects that control the dispatch of the parse
	 * methods.  Note, for string datum, the byte arrays to store the datum must
	 * still be initialized;
	 */
	@Override
	public List<Method> getParseMethods() {
		return this.parseMethods;
	}

	/**
	 * Method to return a List of booleans indicating if the variables are
	 * strings or numeric types
	 *
	 * @return A list of booleans that are true if the variables are string or
	 * false if they are numeric
	 */
	@Override
	public List<Boolean> areStrings() {
		return this.stringType;
	}

	/**
	 * Method to test whether individual variable is of type String
	 *
	 * @param idx The variable type index to test
	 * @return A boolean indicating if variable is of type String
	 */
	@Override
	public Boolean isString(Integer idx) {
		return this.stringType.get(idx);
	}

	/**
	 * Method used to retrive the byte length required to store the datum
	 *
	 * @param type The .dta integer valued type
	 * @return An Integer containing the length of bytes needed to read this
	 * datum
	 */
	@Override
	public Integer getByteLength(Integer type) {
		return map.get(type);
	}

	/**
	 * Method to return a list object containing the byte lengths for each
	 * variable
	 *
	 * @return A list of byte lengths
	 */
	@Override
	public List<Integer> getLengths() {
		return this.bytelen;
	}

	/**
	 * Accesses a scalar containing the total number of bytes allocated for each
	 * record
	 *
	 * @return A long valued integer number of bytes
	 */
	@Override
	public Long getRecordLength() {
		return this.recordlength;
	}

	/**
	 * Computes the total byte length of the record based on the byte lengths of
	 * the individual variables
	 */
	@Override
	public void setRecordLength() {
		for(Integer i : this.bytelen) recordlength += this.bytelen.get(i);
	}


}
