package org.paces.data.Stata.Readers.FileElements.Map;

import org.paces.data.Stata.Readers.FileElements.Characteristics.DtaCharacteristics;
import org.paces.data.Stata.Readers.FileElements.ElementTags;
import org.paces.data.Stata.Version.FileVersion;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used to store the file map object of the .dta file.
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class DtaMap {

	protected List<Integer> offsetMods = new ArrayList<>();
	protected List<Long> offsets = new ArrayList<>();
	private Map<String, Long> osmap = new HashMap<>();

	/**
	 * A string array containing the text of the file tags used in versions
	 * 117 and 118 files.  These tags are used to identify the map elements
	 * for older file versions, but some of the elements (e.g., strls) do not
	 * exist.  In these cases, the values for those tags will point to the
	 * same location as the previous tag.  The table below shows the array
	 * position and tag name corresponding to that position:
	 *
	 * <table>
	 *     <th>
	 *         <td>Position</td><td>Element Name</td>
	 *     </th>
	 *     <tr><td>0</td><td>stata_dta</td></tr>
	 *     <tr><td>1</td><td>map</td></tr>
	 *     <tr><td>2</td><td>variable_types</td></tr>
	 *     <tr><td>3</td><td>varnames</td></tr>
	 *     <tr><td>4</td><td>sortlist</td></tr>
	 *     <tr><td>5</td><td>formats</td></tr>
	 *     <tr><td>6</td><td>value_label_names</td></tr>
	 *     <tr><td>7</td><td>variable_labels</td></tr>
	 *     <tr><td>8</td><td>characteristics</td></tr>
	 *     <tr><td>9</td><td>data</td></tr>
	 *     <tr><td>10</td><td>strls</td></tr>
	 *     <tr><td>11</td><td>value_labels</td></tr>
	 *     <tr><td>12</td><td>/stata_dta</td></tr>
	 *     <tr><td>13</td><td>eof</td></tr>
	 * </table>
	 *
	 * The map references the first byte following the tags referenced here
	 * (accounting for the opening and closing {@literal <>} characters.  The
	 * string values here are simply to provide a consistent interface with
	 * which to access the byte positions for the corresponding elements when
	 * parsing the other componenets of the file.
	 */
	private final String[] mapElems = { "stata_dta", "map", "variable_types",
			"varnames", "sortlist", "formats", "value_label_names",
			"variable_labels", "characteristics", "data", "strls",
			"value_labels", "/stata_data", "eof" };

	public void setOldOffsets(FileVersion<?> ftype, Long mapLocation, Short vars) throws IOException {

		// Opening of file
		offsets.add(0, 0L);

		// Position of what would be the <stata_dta> tag (always 0)
		osmap.put(mapElems[0], 0L);

		// Start of file map
		offsets.add(1, mapLocation);

		// Position where the <map> element would start (should be 110)
		osmap.put(mapElems[1], mapLocation);

		// Start of variable types
		offsets.add(2, mapLocation);

		// Position where the <variable_types> element would start.
		// The types are defined as 1-byte integer values for files 113-115
		osmap.put(mapElems[2], mapLocation);

		// Start of variable names
		offsets.add(3, mapLocation + vars);

		// Position where the <varnames> element would begin
		osmap.put(mapElems[3], mapLocation + vars);

		// Start of sort order
		offsets.add(4, offsets.get(3) + (33 * vars));
		osmap.put(mapElems[4], offsets.get(3) + (33 * vars));

		// Start of display format
		offsets.add(5, offsets.get(4) + (2 * (vars + 1)));
		osmap.put(mapElems[5], offsets.get(4) + (2 * (vars + 1)));

		// Start of variable labels names
		offsets.add(6, offsets.get(5) + (49 * vars));
		osmap.put(mapElems[6], offsets.get(5) + (49 * vars));

		// Start of variable labels
		offsets.add(7, offsets.get(6) + (33 * vars));
		osmap.put(mapElems[7], offsets.get(6) + (33 * vars));

		// Start of characteristics/expansion fields
		offsets.add(8, offsets.get(7) + (81 * vars));
		osmap.put(mapElems[8], offsets.get(7) + (81 * vars));

		// Need to read characteristics to determine the length of this field
		DtaCharacteristics tmp = new DtaCharacteristics(ftype, offsets.get(8));
		Long charlength = tmp.getCharLength();
		offsets.add(9, offsets.get(8) + charlength);
		osmap.put(mapElems[9], offsets.get(8) + charlength);

	}




	DtaMap(FileVersion<?> stata, Long mapOffset, Short nvars) {

	}

	DtaMap(FileVersion<?> stata, Long mapOffset) throws IOException {
		setOffsetMods();
		setNewOffsets(stata.getDtaFile().map(FileChannel.MapMode.READ_ONLY,
				mapOffset, stata.getDtaFile().size()));
	}

	private void setOffsetMods() {
		this.offsetMods.add(0, 0);
		this.offsetMods.add(1,  ElementTags.getTagValue("omap"));
		this.offsetMods.add(2,  ElementTags.getTagValue("ovartypes"));
		this.offsetMods.add(3,  ElementTags.getTagValue("ovarnames"));
		this.offsetMods.add(4,  ElementTags.getTagValue("osortorder"));
		this.offsetMods.add(5,  ElementTags.getTagValue("odifmt"));
		this.offsetMods.add(6,  ElementTags.getTagValue("ovallabname"));
		this.offsetMods.add(7,  ElementTags.getTagValue("ovarlab"));
		this.offsetMods.add(8,  ElementTags.getTagValue("ocharacteristics"));
		this.offsetMods.add(9,  ElementTags.getTagValue("odata"));
		this.offsetMods.add(10, ElementTags.getTagValue("ostrls"));
		this.offsetMods.add(11, ElementTags.getTagValue("ovallabels"));
		this.offsetMods.add(12, 0);
		this.offsetMods.add(13, 0);
	}

	/**
	 * Method used to set the offset member of the class
	 * @param x Byte buffer set to the position defined by mapLocation
	 * @throws IOException An exception thrown if there are any issues
	 * reading the data from the file.
	 */
	public void setNewOffsets(ByteBuffer x) throws IOException {
		for (int i = 0; i < 14; i++) {
			offsets.add(i, (x.getLong() + offsetMods.get(i)));
			osmap.put(mapElems[i], (x.getLong() + offsetMods.get(i)));
		}
	}



}
