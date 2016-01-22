package org.paces.data.Stata.Readers.FileElements;

import org.paces.data.Stata.Version.*;

import java.io.IOException;
import java.nio.ByteBuffer;
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

	protected Map<Integer, Integer> stmap = new HashMap<>();
	private final ElementTags etags = new ElementTags();
	protected List<Integer> offsetMods = new ArrayList<>();
	protected List<Long> offsets = new ArrayList<>();

	DtaMap(V113 stata, Long mapOffset, Short nvars) {


	}

	DtaMap(V114 stata, Long mapOffset, Short nvars) {

	}

	DtaMap(V115 stata, Long mapOffset, Short nvars) {

	}

	DtaMap(V117 stata, Long mapOffset, Short nvars) {

	}

	DtaMap(V118 stata, Long mapOffset, Short nvars) {

	}

	DtaMap(FileVersion<?> file, Long mapPosition, Short nvars) {

	}

	public void setOffsets(V113 file, Long mapPosition, Short variables) throws IOException {

	}
	public void setOffsets(V114 file, Long mapPosition, Short variables) throws IOException {

	}
	public void setOffsets(V115 file, Long mapPosition, Short variables) throws IOException {

	}

	public void setOldestOffsets(V113 ftype, Long mapLocation, Short vars)
	throws IOException {

	}

	public void setOldOffsets(FileVersion<?> ftype, Long mapLocation, Short
			vars) throws IOException {
		// Opening of file
		offsets.add(0, (long) 0);
		// Start of file map
		offsets.add(1, mapLocation);
		// Start of variable types
		offsets.add(2, mapLocation);
		// Start of variable names
		offsets.add(3, mapLocation + vars);
		// Start of sort order
		offsets.add(4, offsets.get(3) + (33 * vars));
		// Start of display format
		offsets.add(5, offsets.get(4) + (2 * (vars + 1)));
		// Start of variable labels names
		offsets.add(6, offsets.get(5) + (49 * vars));
		// Start of variable labels
		offsets.add(7, offsets.get(6) + (33 * vars));
		// Start of characteristics/expansion fields
		offsets.add(8, offsets.get(7) + (81 * vars));
		// Need to read characteristics to determine the length of this field
		DtaCharacteristics tmp = new DtaCharacteristics(ftype, offsets.get(8));
		offsets.add(9, tmp.getEndPosition());
	}

	/**
	 * Method used to set the offset member of the class
	 * @param ftype The release specific file object
	 * @param mapLocation The location where the map element in the file begins
	 * @throws IOException An exception thrown if there are any issues
	 * reading the data from the file.
	 */
	public void setNewOffsets(FileVersion<?> ftype, Integer mapLocation) throws
			IOException {
		offsetMods.add(0, 0);
		offsetMods.add(1,  ElementTags.getTagValue("omap"));
		offsetMods.add(2,  ElementTags.getTagValue("ovartypes"));
		offsetMods.add(3,  ElementTags.getTagValue("ovarnames"));
		offsetMods.add(4,  ElementTags.getTagValue("osortorder"));
		offsetMods.add(5,  ElementTags.getTagValue("odifmt"));
		offsetMods.add(6,  ElementTags.getTagValue("ovallabname"));
		offsetMods.add(7,  ElementTags.getTagValue("ovarlab"));
		offsetMods.add(8,  ElementTags.getTagValue("ocharacteristics"));
		offsetMods.add(9,  ElementTags.getTagValue("odata"));
		offsetMods.add(10, ElementTags.getTagValue("ostrls"));
		offsetMods.add(11, ElementTags.getTagValue("ovallabels"));
		offsetMods.add(12, 0);
		offsetMods.add(13, 0);
		ByteBuffer x = ftype.getDtaFile();
		x.position(mapLocation);
		for (int i = 0; i < 14; i++) {
			offsets.add(i, (x.getLong() + offsetMods.get(i)));
		}
	}



}
