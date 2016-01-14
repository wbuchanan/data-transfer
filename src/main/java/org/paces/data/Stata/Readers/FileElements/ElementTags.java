package org.paces.data.Stata.Readers.FileElements;

import java.util.HashMap;
import java.util.Map;

/**
 * Class with static members defining opening 'O*' and closing 'C*' tags used
 * in Stata files for versions 13 and 14
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class ElementTags {

	/**
	 * Static opening file tag
	 */
	public static final String ODTA = "<stata_dta>";

	/**
	 * Static closing file tag
	 */
	public static final String CDTA = "</stata_dta>";

	/**
	 * Opening header tag
	 */
	public static final String OHEADER = "<header>";

	/**
	 * Closing header tag
	 */
	public static final String CHEADER = "</header>";

	/**
	 * Opening release version tag
	 */
	public static final String ORELEASE = "<release>";

	/**
	 * Closing release version tag
	 */
	public static final String CRELEASE = "</release>";

	/**
	 * Opening byte order tag
	 */
	public static final String OBYTEORDER = "<byteorder>";

	/**
	 * Closing byte order tag
	 */
	public static final String CBYTEORDER = "</byteorder>";

	/**
	 * Opening number of variables tag
	 */
	public static final String OK = "<K>";

	/**
	 * Closing number of variables tag
	 */
	public static final String CK = "</K>";

	/**
	 * Opening number of observations
	 */
	public static final String ON = "<N>";

	/**
	 * Closing number of observations
	 */
	public static final String CN = "</N>";

	/**
	 * Opening data set label tag
	 */
	public static final String OLABEL = "<label>";

	/**
	 * Closing data set label tag
	 */
	public static final String CLABEL = "</label>";

	/**
	 * Opening dataset timestamp tag
	 */
	public static final String OTIMESTAMP = "<timestamp>";

	/**
	 * Closing dataset timestamp tag
	 */
	public static final String CTIMESTAMP = "</timestamp>";

	/**
	 * Opening file byte map tag
	 */
	public static final String OMAP = "<map>";

	/**
	 * Closing file byte map tag
	 */
	public static final String CMAP = "</map>";

	/**
	 * Opening variable type tag
	 */
	public static final String OVARTYPES = "<variable_types>";

	/**
	 * Closing variable type tag
	 */
	public static final String CVARTYPES = "</variable_types>";

	/**
	 * Opening variable names type tag
	 */
	public static final String OVARNAMES = "<varnames>";

	/**
	 * Closing variable names type tag
	 */
	public static final String CVARNAMES = "</varnames>";

	/**
	 * Opening tag for the data sort order based on variable indices
	 */
	public static final String OSORTLIST = "<sortlist>";

	/**
	 * Closing tag for the data sort order based on variable indices
	 */
	public static final String CSORTLIST = "</sortlist>";

	/**
	 * Opening variable display format tag
	 */
	public static final String OFORMATS = "<formats>";

	/**
	 * Closing variable display format tag
	 */
	public static final String CFORMATS = "</formats>";

	/**
	 * Opening value label names tag
	 */
	public static final String OVALLABNAMES = "<value_label_names>";

	/**
	 * Closing value label names tag
	 */
	public static final String CVALLABNAMES = "</value_label_names>";

	/**
	 * Opening variable label tag
	 */
	public static final String OVARLABELS = "<variable_labels>";

	/**
	 * Closing variable label tag
	 */
	public static final String CVARLABELS = "</variable_labels>";

	/**
	 * Opening characteristics tag
	 */
	public static final String OCHARACTERISTICS = "<characteristics>";

	/**
	 * Closing characteristics tag
	 */
	public static final String CCHARACTERISTICS = "</characteristics>";

	/**
	 * Opening tag for individual characteristic (nested within
	 * characteristics tags)
	 */
	public static final String OCHAR = "<ch>";

	/**
	 * Closing tag for individual characteristic (nested within
	 * characteristics tags)
	 */
	public static final String CCHAR = "</ch>";

	/**
	 * Opening data tag
	 */
	public static final String ODATA = "<data>";

	/**
	 * Closing data tag
	 */
	public static final String CDATA = "</data>";

	/**
	 * Opening tag for binary large objects (BLOBS in RDBMS or strLs in Stata
	 * speak)
	 */
	public static final String OSTRLS = "<strls>";

	/**
	 * Closing tag for binary large objects (BLOBS in RDBMS or strLs in Stata
	 * speak)
	 */
	public static final String CSTRLS = "</strls>";

	/**
	 * Opening tag for value labels table
	 */
	public static final String OVALUELABELS = "<value_labels>";

	/**
	 * Closing tag for value labels table
	 */
	public static final String CVALUELABELS = "</value_labels>";

	/**
	 * Opening tag for individual value label map
	 */
	public static final String OVALLAB = "<lbl>";

	/**
	 * Closing tag for individual value label map
	 */
	public static final String CVALLAB = "</lbl>";

	/**
	 * Opening file tag byte length
	 */
	public static final int LENODTA = ODTA.getBytes().length;

	/**
	 * Closing flie tag byte length
	 */
	public static final int LENCDTA = CDTA.getBytes().length;

	/**
	 * Opening header tag byte length
	 */
	public static final int LENOHEADER = OHEADER.getBytes().length;

	/**
	 * Closing header tag byte length
	 */
	public static final int LENCHEADER = CHEADER.getBytes().length;

	/**
	 * Opening release version tag byte length
	 */
	public static final int LENORELEASE = ORELEASE.getBytes().length;

	/**
	 * Closing release version tag byte length
	 */
	public static final int LENCRELEASE = CRELEASE.getBytes().length;

	/**
	 * Opening byte order tag byte length
	 */
	public static final int LENOBYTEORDER = OBYTEORDER.getBytes().length;

	/**
	 * Closing byte order tag byte length
	 */
	public static final int LENCBYTEORDER = CBYTEORDER.getBytes().length;

	/**
	 *
	 */
	public static final int LENOK = OK.getBytes().length;

	/**
	 *
	 */
	public static final int LENCK = CK.getBytes().length;

	/**
	 *
	 */
	public static final int LENON = ON.getBytes().length;

	/**
	 *
	 */
	public static final int LENCN = CN.getBytes().length;

	/**
	 *
	 */
	public static final int LENOLABEL = OLABEL.getBytes().length;

	/**
	 *
	 */
	public static final int LENCLABEL = CLABEL.getBytes().length;

	/**
	 *
	 */
	public static final int LENOTIMESTAMP = OTIMESTAMP.getBytes().length;

	/**
	 *
	 */
	public static final int LENCTIMESTAMP = CTIMESTAMP.getBytes().length;

	/**
	 *
	 */
	public static final int LENOMAP = OMAP.getBytes().length;

	/**
	 *
	 */
	public static final int LENCMAP = CMAP.getBytes().length;

	/**
	 *
	 */
	public static final int LENOVARTYPES = OVARTYPES.getBytes().length;

	/**
	 *
	 */
	public static final int LENCVARTYPES = CVARTYPES.getBytes().length;

	/**
	 *
	 */
	public static final int LENOVARNAMES = OVARNAMES.getBytes().length;

	/**
	 *
	 */
	public static final int LENCVARNAMES = CVARNAMES.getBytes().length;

	/**
	 *
	 */
	public static final int LENOSORTLIST = OSORTLIST.getBytes().length;

	/**
	 *
	 */
	public static final int LENCSORTLIST = CSORTLIST.getBytes().length;

	/**
	 *
	 */
	public static final int LENOFORMATS = OFORMATS.getBytes().length;

	/**
	 *
	 */
	public static final int LENCFORMATS = CFORMATS.getBytes().length;

	/**
	 *
	 */
	public static final int LENOVALLABNAMES = OVALLABNAMES.getBytes().length;

	/**
	 *
	 */
	public static final int LENCVALLABNAMES = CVALLABNAMES.getBytes().length;

	/**
	 *
	 */
	public static final int LENOVARLABELS = OVARLABELS.getBytes().length;

	/**
	 *
	 */
	public static final int LENCVARLABELS = CVARLABELS.getBytes().length;

	/**
	 *
	 */
	public static final int LENOCHARACTERISTICS = OCHARACTERISTICS.getBytes()
			.length;

	/**
	 *
	 */
	public static final int LENCCHARACTERISTICS = CCHARACTERISTICS.getBytes()
			.length;

	/**
	 *
	 */
	public static final int LENOCHAR = OCHAR.getBytes().length;

	/**
	 *
	 */
	public static final int LENCCHAR = CCHAR.getBytes().length;

	/**
	 *
	 */
	public static final int LENODATA = ODATA.getBytes().length;

	/**
	 *
	 */
	public static final int LENCDATA = CDATA.getBytes().length;

	/**
	 *
	 */
	public static final int LENOSTRLS = OSTRLS.getBytes().length;

	/**
	 *
	 */
	public static final int LENCSTRLS = CSTRLS.getBytes().length;

	/**
	 *
	 */
	public static final int LENOVALUELABELS = OVALUELABELS.getBytes().length;

	/**
	 *
	 */
	public static final int LENCVALUELABELS = CVALUELABELS.getBytes().length;

	/**
	 *
	 */
	public static final int LENOVALLAB = OVALLAB.getBytes().length;

	/**
	 *
	 */
	public static final int LENCVALLAB = CVALLAB.getBytes().length;

	/**
	 *
	 */
	protected static Map<String, Integer> tagmaps;

	/**
	 * Default class constructor Creates a Map element containing the length
	 * of the tag elements used when writing/reading files
	 */
	public ElementTags() {
		tagmaps = getDtaTags();
	}

	/**
	 * Method to retrieve the byte length of the tag that is in/to be placed
	 * in the file
	 * @param tagName The string name of the tag reference (opening tags
	 *                   start with 'o' and closing tags begin with 'c')
	 * @return The Integer valued byte length of the element tag
	 */
	public static Integer getTagValue(String tagName) {
		return tagmaps.get(tagName);
	}

	/**
	 * Method used to construct a Map object with the byte lengths of the
	 * opening/closing tags used in the file
	 * @return A Map object with string keys and Integer values
	 */
	public static Map<String, Integer> getDtaTags() {
		Map<String, Integer> tagMap = new HashMap<>();
		tagMap.put("odta", LENODTA);
		tagMap.put("cdta", LENCDTA);
		tagMap.put("oheader", LENOHEADER);
		tagMap.put("cheader", LENCHEADER);
		tagMap.put("orelease", LENORELEASE);
		tagMap.put("crelease", LENCRELEASE);
		tagMap.put("obyteorder", LENOBYTEORDER);
		tagMap.put("cbyteorder", LENCBYTEORDER);
		tagMap.put("onvars", LENON);
		tagMap.put("cnvars", LENCN);
		tagMap.put("onobs", LENOK);
		tagMap.put("cnobs", LENCK);
		tagMap.put("odatalabel", LENOLABEL);
		tagMap.put("cdatalabel", LENCLABEL);
		tagMap.put("otimestamp", LENOTIMESTAMP);
		tagMap.put("ctimestamp", LENCTIMESTAMP);
		tagMap.put("omap", LENOMAP);
		tagMap.put("cmap", LENCMAP);
		tagMap.put("ovartypes", LENOVARTYPES);
		tagMap.put("cvartypes", LENCVARTYPES);
		tagMap.put("ovarnames", LENOVARNAMES);
		tagMap.put("cvarnames", LENCVARNAMES);
		tagMap.put("osortorder", LENOSORTLIST);
		tagMap.put("csortorder", LENCSORTLIST);
		tagMap.put("odifmt", LENOFORMATS);
		tagMap.put("cdifmt", LENCFORMATS);
		tagMap.put("ovallabname", LENOVALLABNAMES);
		tagMap.put("cvallabname", LENCVALLABNAMES);
		tagMap.put("ovarlab", LENOVARLABELS);
		tagMap.put("cvarlab", LENCVARLABELS);
		tagMap.put("ocharacteristics", LENOCHARACTERISTICS);
		tagMap.put("ccharacteristics", LENCCHARACTERISTICS);
		tagMap.put("odata", LENODATA);
		tagMap.put("cdata", LENCDATA);
		tagMap.put("ostrls", LENOSTRLS);
		tagMap.put("cstrls", LENCSTRLS);
		tagMap.put("ovallabels", LENOVALUELABELS);
		tagMap.put("cvallabels", LENCVALUELABELS);
		tagMap.put("ovallab", LENOVALLAB);
		tagMap.put("cvallab", LENCVALLAB);
		tagMap.put("ochar", LENOCHAR);
		tagMap.put("cchar", LENCCHAR);
		return tagMap;
	}
}
