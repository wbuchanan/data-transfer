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

	private static final String ODTA = "<stata_dta>";
	private static final String CDTA = "</stata_dta>";
	private static final String OHEADER = "<header>";
	private static final String CHEADER = "</header>";
	private static final String ORELEASE = "<release>";
	private static final String CRELEASE = "</release>";
	private static final String OBYTEORDER = "<byteorder>";
	private static final String CBYTEORDER = "</byteorder>";
	private static final String OK = "<K>";
	private static final String CK = "</K>";
	private static final String ON = "<N>";
	private static final String CN = "</N>";
	private static final String OLABEL = "<label>";
	private static final String CLABEL = "</label>";
	private static final String OTIMESTAMP = "<timestamp>";
	private static final String CTIMESTAMP = "</timestamp>";
	private static final String OMAP = "<map>";
	private static final String CMAP = "</map>";
	private static final String OVARTYPES = "<variable_types>";
	private static final String CVARTYPES = "</variable_types>";
	private static final String OVARNAMES = "<varnames>";
	private static final String CVARNAMES = "</varnames>";
	private static final String OSORTLIST = "<sortlist>";
	private static final String CSORTLIST = "</sortlist>";
	private static final String OFORMATS = "<formats>";
	private static final String CFORMATS = "</formats>";
	private static final String OVALLABNAMES = "<value_label_names>";
	private static final String CVALLABNAMES = "</value_label_names>";
	private static final String OVARLABELS = "<variable_labels>";
	private static final String CVARLABELS = "</variable_labels>";
	private static final String OCHARACTERISTICS = "<characteristics>";
	private static final String CCHARACTERISTICS = "</characteristics>";
	private static final String OCHAR = "<ch>";
	private static final String CCHAR = "</ch>";
	private static final String ODATA = "<data>";
	private static final String CDATA = "</data>";
	private static final String OSTRLS = "<strls>";
	private static final String CSTRLS = "</strls>";
	private static final String OVALUELABELS = "<value_labels>";
	private static final String CVALUELABELS = "</value_labels>";
	private static final String OVALLAB = "<lbl>";
	private static final String CVALLAB = "</lbl>";

	private static final int LENODTA = ODTA.getBytes().length;
	private static final int LENCDTA = CDTA.getBytes().length;
	private static final int LENOHEADER = OHEADER.getBytes().length;
	private static final int LENCHEADER = CHEADER.getBytes().length;
	private static final int LENORELEASE = ORELEASE.getBytes().length;
	private static final int LENCRELEASE = CRELEASE.getBytes().length;
	private static final int LENOBYTEORDER = OBYTEORDER.getBytes().length;
	private static final int LENCBYTEORDER = CBYTEORDER.getBytes().length;
	private static final int LENOK = OK.getBytes().length;
	private static final int LENCK = CK.getBytes().length;
	private static final int LENON = ON.getBytes().length;
	private static final int LENCN = CN.getBytes().length;
	private static final int LENOLABEL = OLABEL.getBytes().length;
	private static final int LENCLABEL = CLABEL.getBytes().length;
	private static final int LENOTIMESTAMP = OTIMESTAMP.getBytes().length;
	private static final int LENCTIMESTAMP = CTIMESTAMP.getBytes().length;
	private static final int LENOMAP = OMAP.getBytes().length;
	private static final int LENCMAP = CMAP.getBytes().length;
	private static final int LENOVARTYPES = OVARTYPES.getBytes().length;
	private static final int LENCVARTYPES = CVARTYPES.getBytes().length;
	private static final int LENOVARNAMES = OVARNAMES.getBytes().length;
	private static final int LENCVARNAMES = CVARNAMES.getBytes().length;
	private static final int LENOSORTLIST = OSORTLIST.getBytes().length;
	private static final int LENCSORTLIST = CSORTLIST.getBytes().length;
	private static final int LENOFORMATS = OFORMATS.getBytes().length;
	private static final int LENCFORMATS = CFORMATS.getBytes().length;
	private static final int LENOVALLABNAMES = OVALLABNAMES.getBytes().length;
	private static final int LENCVALLABNAMES = CVALLABNAMES.getBytes().length;
	private static final int LENOVARLABELS = OVARLABELS.getBytes().length;
	private static final int LENCVARLABELS = CVARLABELS.getBytes().length;
	private static final int LENOCHARACTERISTICS = OCHARACTERISTICS.getBytes().length;
	private static final int LENCCHARACTERISTICS = CCHARACTERISTICS.getBytes().length;
	private static final int LENOCHAR = OCHAR.getBytes().length;
	private static final int LENCCHAR = CCHAR.getBytes().length;
	private static final int LENODATA = ODATA.getBytes().length;
	private static final int LENCDATA = CDATA.getBytes().length;
	private static final int LENOSTRLS = OSTRLS.getBytes().length;
	private static final int LENCSTRLS = CSTRLS.getBytes().length;
	private static final int LENOVALUELABELS = OVALUELABELS.getBytes().length;
	private static final int LENCVALUELABELS = CVALUELABELS.getBytes().length;
	private static final int LENOVALLAB = OVALLAB.getBytes().length;
	private static final int LENCVALLAB = CVALLAB.getBytes().length;
	protected static Map<String, Integer> tagmaps;

	public ElementTags() {
		tagmaps = getDtaTags();
	}

	public static Integer getTagValue(String tagName) {
		return tagmaps.get(tagName);
	}

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
