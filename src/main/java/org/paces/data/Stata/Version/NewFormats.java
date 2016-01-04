package org.paces.data.Stata.Version;

import org.paces.data.Stata.Readers.FileElements.ElementTags;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by billy on 12/27/15.
 */
public class NewFormats extends FileConstants {

	protected static final int RELEASE = 3;
	protected static final int BYTEORDER = 3;

	public ElementTags etags;



	protected NewFormats() {
		etags = new ElementTags();
	}


	/***
	 * Method to return a Map object for type map after the introduction of
	 * Binary Large OBject types in Stata
	 * @return A map with integer keys and string values
	 */
	public Map<Integer, Integer> getDataTypes() {
		Map<Integer, Integer> stataTypes = new HashMap<>();
		for (int i = 1; i < 2045; i++) {
			stataTypes.put(i, i);
		}
		stataTypes.put(32768, 0);
		stataTypes.put(65526, 8);
		stataTypes.put(65527, 4);
		stataTypes.put(65528, 4);
		stataTypes.put(65529, 2);
		stataTypes.put(65530, 1);
		return stataTypes;
	}


}
