package org.paces.data.Stata.Readers.FileElements.Data;

import org.paces.data.Stata.Readers.StConvert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Record {

	List<Object> record = new ArrayList<>();

	public Record(ByteBuffer bytes, List<Integer> types, List<Method>
			parsers, List<Boolean> isString, List<Integer> lengths) {
		parseData(bytes, types, parsers, isString, lengths);
	}

	public void parseData(ByteBuffer bytes, List<Integer> types, List<Method>
			parsers, List<Boolean> isString, List<Integer> bytelengths) {
		byte[] forstrings;
		for(int i = 0; i < types.size(); i++) {
			if (isString.get(i)) {
				forstrings = new byte[bytelengths.get(i)];
				try {
					parsers.get(i).invoke(bytes, forstrings);
					record.add(i, StConvert.toStata(forstrings, bytes.order(), ""));
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			} else {
				try {
					record.add(i, parsers.get(i).invoke(bytes));
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Method used to access the data record
	 * @return A List of Object types containing the data for each of the
	 * variables
	 */
	public List<Object> getRecord() {
		return this.record;
	}


}
