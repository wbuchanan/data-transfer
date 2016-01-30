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
public class NewTypes implements DtaVarTypes {

	/**
	 * Member used to store the variable types from the dataset
	 */
	private List<Integer> types = new ArrayList<>();

	/**
	 * Member used to store method objects for parsing the data
	 */
	private List<Method> parseMethods = new ArrayList<>();

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
	 * Byte Buffer used to initialize methods via reflections
	 */
	private ByteBuffer buffObject;

	/**
	 * Method used to parse strL types in the data element of the .dta file
	 */
	private Method strlMethod;

	/**
	 * The class name from which to initialize the strl method
	 */
	private String classnm;

	private Long recordlength = 0L;

	public NewTypes(ByteBuffer bytes, Short nvars, Integer version) {
		this.buffObject = bytes;
		if (version == 117) {
			this.classnm = "org.paces.data.Stata.Readers.FileElements.Blobs.Strl117";
		} else {
			this.classnm = "org.paces.data.Stata.Readers.FileElements.Blobs.Strl118";
		}
		setTypeMaps();
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
			for (Integer i = 1; i < 2046; i++) {
				map.put(i, i);
				methods.put(i, getString);
			}
			map.put(65526, 8);
			map.put(65527, 4);
			map.put(65528, 4);
			map.put(65529, 2);
			map.put(65530, 1);
			map.put(32768, 0);
			methods.put(65526, getDouble);
			methods.put(65527, getFloat);
			methods.put(65528, getInt);
			methods.put(65529, getShort);
			methods.put(65530, getByte);
			methods.put(32768, this.strlMethod);
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
			this.types.add(i, theType);
			this.parseMethods.add(i, methods.get(theType));
			if (theType <= 32768) this.stringType.add(i, true);
			else this.stringType.add(i, false);
			this.bytelen.add(i, map.get(theType));
		}
	}

	/**
	 * Method used to set the method object used for parsing strL elements in
	 * within the data tags of the file.
	 */
	@Override
	public void setStrlType(Integer filetype) {
		try {
			Class strlClass = Class.forName(this.classnm);
			this.strlMethod = strlClass.getMethod("parseMembers", buffObject.getClass());
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			e.printStackTrace();
		}
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
