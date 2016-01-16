package org.paces.data.Stata.Writers;

import org.paces.data.Stata.Version.V118;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by billy on 10/14/15.
 */
public class Write {

	public static Map<String, byte[]> getDtaBytes(V118 fileVersion) {
		Map<String, byte[]> bytelen = new HashMap<>();
		bytelen.put("release", new byte[3]);
		bytelen.put("byteOrder", new byte[3]);
		bytelen.put("nVars", new byte[2]);
		bytelen.put("nObs", new byte[8]);
		bytelen.put("datasetLabel", new byte[fileVersion.DATASET_LABEL_LENGTH]);
		bytelen.put("hasTimeStamp", new byte[1]);
		bytelen.put("timestamp", new byte[17]);
		bytelen.put("vartype", new byte[2]);
		bytelen.put("varname", new byte[129]);
		bytelen.put("sortorder", new byte[2]);
		bytelen.put("fmts", new byte[57]);
		bytelen.put("vallabName", new byte[129]);
		bytelen.put("varlabel", new byte[321]);
		bytelen.put("charlen", new byte[4]);
		bytelen.put("charVarName", new byte[129]);
		bytelen.put("charCharName", new byte[129]);
		bytelen.put("strlVarID", new byte[2]);
		bytelen.put("strlObsId", new byte[6]);
		bytelen.put("strlGSO", new byte[3]);
		bytelen.put("strlV", new byte[4]);
		bytelen.put("strlO", new byte[8]);
		bytelen.put("strlT", new byte[1]);
		bytelen.put("strlLEN", new byte[4]);
		bytelen.put("vallabLEN", new byte[4]);
		bytelen.put("vallabName", new byte[129]);
		bytelen.put("vallabPadding", new byte[3]);
		bytelen.put("vallabEntries", new byte[4]);
		bytelen.put("vallabTextLength", new byte[4]);
		bytelen.put("vallabTextOffset", new byte[4]);
		bytelen.put("vallabValueOffset", new byte[4]);
		return bytelen;
	}

}
