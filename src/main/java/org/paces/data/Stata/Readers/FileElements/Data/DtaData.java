package org.paces.data.Stata.Readers.FileElements.Data;

import org.paces.data.Stata.Readers.FileElements.VariableTypes.DtaVarTypes;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class DtaData {

	List<List<Object>> dataset = new ArrayList<>();
	List<Integer> types;
	List<Method> parsers;
	List<Boolean> isString;
	List<Integer> bytelengths;

	DtaData(ByteBuffer bytes, Integer obs, DtaVarTypes datatypes) {
		this.types = datatypes.getTypeMaps();
		this.parsers = datatypes.getParseMethods();
		this.isString = datatypes.areStrings();
		this.bytelengths = datatypes.getLengths();
		parseData(bytes, obs);
	}

	void parseData(ByteBuffer bytes, Integer obs) {

		// Loop over observations
		for(int i = 0; i < obs; i++) {
			// Initializes a new Record object
			Record ob = new Record(bytes, this.types, this.parsers,
										  this.isString, this.bytelengths);

			// Adds the list of objects from the record to the dataset member
			dataset.add(i, ob.getRecord());

		} // End Loop over observations

	}

	public List<Object> getVariable(Integer idx) {
		List<Object> retval = new ArrayList<>();
		for(int i = 0; i < dataset.size(); i++) {
			retval.add(i, dataset.get(i).get(idx));
		}
		return retval;
	}

	public List<Object> getRecord(Integer idx) {
		return this.dataset.get(idx);
	}

	public List<List<Object>> getDataSet() {
		return this.dataset;
	}

}
