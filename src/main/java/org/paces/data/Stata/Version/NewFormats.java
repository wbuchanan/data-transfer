package org.paces.data.Stata.Version;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by billy on 12/27/15.
 */
public class NewFormats extends FileConstants {

	protected static final int RELEASE = 3;
	protected static final int BYTEORDER = 3;

	// File opening tag
	protected static final int DTA_OPEN = "<stata_dta>".getBytes().length;
	
	// File closing tag
	protected static final int DTA_CLOSE = "</stata_dta>".getBytes().length;
	
	// File header opening tag
	protected static final int HEADER_OPEN = "<header>".getBytes().length;
	
	// File release version opening tag
	protected static final int RELEASE_OPEN = "<release>".getBytes().length;
	
	// File release version closing tag
	protected static final int RELEASE_CLOSE = "</release>".getBytes().length;
	
	// File release endianness opening tag
	protected static final int BYTEORDER_OPEN = "<byteorder>".getBytes().length;
	
	// File endianness closinging tag
	protected static final int BYTEORDER_CLOSE = "</byteorder>".getBytes().length;
	
	// File number of variables opening tag
	protected static final int NVARS_OPEN = "<K>".getBytes().length;
	
	// File number of variables closing tag
	protected static final int NVARS_CLOSE = "</K>".getBytes().length;
	
	// File number of observations opening tag
	protected static final int NOBS_OPEN = "<N>".getBytes().length;
	
	// File number of observations closing tag
	protected static final int NOBS_CLOSE = "</N>".getBytes().length;
	
	// Opening tag for the datast label
	protected static final int DATASET_LABEL_OPEN = "<label>".getBytes().length;
	
	// Closing tag for the datast label
	protected static final int DATASET_LABEL_CLOSE = "</label>".getBytes().length;
	
	// Opening tag for the data set timestamp
	protected static final int DATASET_TIMESTAMP_OPEN = "<timestamp>".getBytes().length;
	
	// Length of the closing tag of the time stamp
	protected static final int DATASET_TIMESTAMP_CLOSE = "</timestamp>".getBytes().length;
	
	// Length of the header closing tag
	protected static final int HEADER_CLOSE = "</header>".getBytes().length;
	
	// Length of the file map object opening tag
	protected static final int MAP_OPEN = "<map>".getBytes().length;
	
	// Gets the length of the closing tag for the file map object
	protected static final int MAP_CLOSE = "</map>".getBytes().length;
	
	protected static final int VARIABLE_TYPES_OPEN = "<variable_types>".getBytes().length;
	protected static final int VARIABLE_TYPES_CLOSE = "</variable_types>".getBytes().length;
	
	protected static final int VARNAMES_OPEN = "<varnames>".getBytes().length;
	protected static final int VARNAMES_CLOSE = "</varnames>".getBytes().length;
	
	protected static final int SORTORDER_OPEN = "<sortlist>".getBytes().length;
	protected static final int SORTORDER_CLOSE = "</sortlist>".getBytes().length;
	
	protected static final int DISPLAY_FMT_OPEN = "<formats>".getBytes().length;
	protected static final int DISPLAY_FMT_CLOSE = "</formats>".getBytes().length;
	
	protected static final int VALUELAB_NAMES_OPEN = "<value_label_names>".getBytes().length;
	protected static final int VALUELAB_NAMES_CLOSE = "</value_label_names>".getBytes().length;
	
	protected static final int VARIABLE_LABELS_OPEN = "<variable_labels>".getBytes().length;
	protected static final int VARIABLE_LABELS_CLOSE = "</variable_labels>".getBytes().length;
	
	protected static final int CHARACTERISTICS_OPEN = "<characteristics>".getBytes().length;
	protected static final int CHARACTERISTICS_CLOSE = "</characteristics>".getBytes().length;
	
	protected static final int DATA_OPEN = "<data>".getBytes().length;
	protected static final int DATA_CLOSE = "</data>".getBytes().length;
	
	protected static final int STRLS_OPEN = "<strls>".getBytes().length;
	protected static final int STRLS_CLOSE = "</strls>".getBytes().length;
	
	protected static final int VALUE_LABELS_OPEN = "<value_labels>".getBytes().length;
	protected static final int VALUE_LABELS_CLOSE = "</value_labels>".getBytes().length;
	
	protected static final int VALLAB_OPEN = "<lbl>".getBytes().length;
	protected static final int VALLAB_CLOSE = "</lbl>".getBytes().length;
	
	protected static final int CHAR_OPEN = "<ch>".getBytes().length;
	protected static final int CHAR_CLOSE = "</ch>".getBytes().length;

	public static Map<String, Integer> getDtaTags() {
		Map<String, Integer> tagMap = new HashMap<>();
		tagMap.put("odta", DTA_OPEN);
		tagMap.put("cdta", DTA_CLOSE);
		tagMap.put("oheader", HEADER_OPEN);
		tagMap.put("cheader", HEADER_CLOSE);
		tagMap.put("orelease", RELEASE_OPEN);
		tagMap.put("crelease", RELEASE_CLOSE);
		tagMap.put("obyteorder", BYTEORDER_OPEN);
		tagMap.put("cbyteorder", BYTEORDER_CLOSE);
		tagMap.put("onvars", NVARS_OPEN);
		tagMap.put("cnvars", NVARS_CLOSE);
		tagMap.put("onobs", NOBS_OPEN);
		tagMap.put("cnobs", NOBS_CLOSE);
		tagMap.put("odatalabel", DATASET_LABEL_OPEN);
		tagMap.put("cdatalabel", DATASET_LABEL_CLOSE);
		tagMap.put("otimestamp", DATASET_TIMESTAMP_OPEN);
		tagMap.put("ctimestamp", DATASET_TIMESTAMP_CLOSE);
		tagMap.put("omap", MAP_OPEN);
		tagMap.put("cmap", MAP_CLOSE);
		tagMap.put("ovartypes", VARIABLE_TYPES_OPEN);
		tagMap.put("cvartypes", VARIABLE_TYPES_CLOSE);
		tagMap.put("ovarnames", VARNAMES_OPEN);
		tagMap.put("cvarnames", VARNAMES_CLOSE);
		tagMap.put("osortorder", SORTORDER_OPEN);
		tagMap.put("csortorder", SORTORDER_CLOSE);
		tagMap.put("odifmt", DISPLAY_FMT_OPEN);
		tagMap.put("cdifmt", DISPLAY_FMT_CLOSE);
		tagMap.put("ovallabname", VALUELAB_NAMES_OPEN);
		tagMap.put("cvallabname", VALUELAB_NAMES_CLOSE);
		tagMap.put("ovarlab", VARIABLE_LABELS_OPEN);
		tagMap.put("cvarlab", VARIABLE_LABELS_CLOSE);
		tagMap.put("ocharacteristics", CHARACTERISTICS_OPEN);
		tagMap.put("ccharacteristics", CHARACTERISTICS_CLOSE);
		tagMap.put("odata", DATA_OPEN);
		tagMap.put("cdata", DATA_CLOSE);
		tagMap.put("ostrls", STRLS_OPEN);
		tagMap.put("cstrls", STRLS_CLOSE);
		tagMap.put("ovallabels", VALUE_LABELS_OPEN);
		tagMap.put("cvallabels", VALUE_LABELS_CLOSE);
		tagMap.put("ovallab", VALLAB_OPEN);
		tagMap.put("cvallab", VALLAB_CLOSE);
		tagMap.put("ochar", CHAR_OPEN);
		tagMap.put("cchar", CHAR_CLOSE);
		return tagMap;
	}

	protected static Map<String, Integer> tagmaps;

	protected NewFormats() {
		tagmaps = getDtaTags();
	}

	public static Integer getTagValue(String tagName) {
		return tagmaps.get(tagName);
	}

	/***
	 * Method to return a Map object for type map after the introduction of
	 * Binary Large OBject types in Stata
	 * @return A map with integer keys and string values
	 */
	public static Map<Integer, String> getDataTypes() {
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


}
