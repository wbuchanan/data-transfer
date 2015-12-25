package org.paces.data.Stata;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 * Class containing methods that return a Map object containing integer
 * values for Stata data types and their associated Stata data type names
 */
public class DataTypes {

	/***
	 * Method to return a Map object for type map prior to the introduction
	 * of Binary Large OBject types in Stata
	 * @return A map with integer keys and string values
	 */
	public static Map<Integer, String> getTypesNoStrLs() {
		Map<Integer, String> stataTypes = new HashMap<>();
		for (int i = 1; i < 244; i++) {
			stataTypes.put(i, "java.lang.String");
		}
		stataTypes.put(251, "java.lang.Byte");
		stataTypes.put(252, "java.lang.Short");
		stataTypes.put(253, "java.lang.Integer");
		stataTypes.put(254, "java.lang.Float");
		stataTypes.put(255, "java.lang.Double");
		return stataTypes;
	}

	/***
	 * Method to return a Map object for type map after the introduction of
	 * Binary Large OBject types in Stata
	 * @return A map with integer keys and string values
	 */
	public static Map<Integer, String> getTypesWStrLs() {
		Map<Integer, String> stataTypes = new HashMap<>();
		for (int i = 1; i < 2045; i++) {
			stataTypes.put(i, "java.lang.String");
		}
		stataTypes.put(32768, "java.lang.String");
		stataTypes.put(65526, "java.lang.Double");
		stataTypes.put(65527, "java.lang.Float");
		stataTypes.put(65528, "java.lang.Integer");
		stataTypes.put(65529, "java.lang.Short");
		stataTypes.put(65530, "java.lang.Byte");
		return stataTypes;
	}

} // End of Class declaration
