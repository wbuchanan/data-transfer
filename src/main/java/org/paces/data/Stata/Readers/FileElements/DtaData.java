package org.paces.data.Stata.Readers.FileElements;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by billy on 12/25/15.
 */
public class DtaData {

	DtaVarTypes stVariableTypes;

	DtaData(RandomAccessFile x, Integer numberVariables, Long numberVars) {
		x.seek(offsets.get(9));
		Map<Integer, Map<String, Object>> dataset = new HashMap<Integer, Map<String, Object>>();
		for (long i = 0L; i < numberVars; i++) {
			Map<String, Object> record = new HashMap<String, Object>();
			for (int j = 0; j < numberVariables; j++) {
				byte[] value;
				int sttype = stVariableTypes.get(j);
				Object mapValue = null;
				if (sttype != 0 || sttype != 32768) {
					int resbytes = typeMap.get(sttype);
					value = new byte[resbytes];
					x.read(value);
					if (sttype == 65526) {
						mapValue = ByteBuffer.wrap(value).order(ByteOrder.nativeOrder()).getDouble();
					} else if (sttype == 65527) {
						mapValue = ByteBuffer.wrap(value).order(ByteOrder.nativeOrder()).getFloat();
					} else if (sttype == 65528) {
						mapValue = ByteBuffer.wrap(value).order(ByteOrder.nativeOrder()).getInt();
					} else if (sttype == 65529) {
						mapValue = ByteBuffer.wrap(value).order(ByteOrder.nativeOrder()).getShort();
					} else if (sttype == 65530) {
						mapValue = ByteBuffer.wrap(value).order(ByteOrder.nativeOrder()).get();
					} else {
						mapValue = new String(value);
					}
				}
				System.out.println("The value of " + varnms.get(j) + " for observation " + String.valueOf(i) + " is " + String.valueOf(mapValue));
				record.put(varnms.get(j), mapValue);
			}
			dataset.put(i, record);
		}

	}

}
