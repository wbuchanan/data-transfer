package org.paces.data.Stata.Readers.FileElements;

import org.paces.data.Stata.Version.*;

import java.io.IOException;
import java.io.RandomAccessFile;
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

	protected Map<Integer, Long> stmap = new HashMap<>();
	private final ElementTags etags = new ElementTags();
	protected List<Long> offsetMods = new ArrayList<>();
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

	DtaMap(FileVersion<?> file, Long mapPosition) throws IOException {
		setNewOffsets(file, mapPosition);
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
	public void setNewOffsets(FileVersion<?> ftype, Long mapLocation) throws
			IOException {
		offsetMods.add(0, (long) 0);
		offsetMods.add(1, (long) ElementTags.getTagValue("omap"));
		offsetMods.add(2, (long) ElementTags.getTagValue("ovartypes"));
		offsetMods.add(3, (long) ElementTags.getTagValue("ovarnames"));
		offsetMods.add(4, (long) ElementTags.getTagValue("osortorder"));
		offsetMods.add(5, (long) ElementTags.getTagValue("odifmt"));
		offsetMods.add(6, (long) ElementTags.getTagValue("ovallabname"));
		offsetMods.add(7, (long) ElementTags.getTagValue("ovarlab"));
		offsetMods.add(8, (long) ElementTags.getTagValue("ocharacteristics"));
		offsetMods.add(9, (long) ElementTags.getTagValue("odata"));
		offsetMods.add(10, (long) ElementTags.getTagValue("ostrls"));
		offsetMods.add(11, (long) ElementTags.getTagValue("ovallabels"));
		offsetMods.add(12, (long) 0);
		offsetMods.add(13, (long) 0);
		RandomAccessFile x = ftype.getDtaFile();
		x.seek(mapLocation);
		for (int i = 0; i < 14; i++) {
			byte[] position = new byte[8];
			x.read(position);
			Long offsetRaw = ByteBuffer.wrap(position).order(ftype.getByteSwap()).getLong();
			offsets.add(i, (offsetRaw + offsetMods.get(i)));
		}
		x.close();
	}

	/**
	 * Method to return all of the file position offsets
	 * @return A map of integer indexed Long valued byte offsets
	 */
	public Map<Integer, Long> getOffsets() {
		return this.stmap;
	}

	/**
	 * The offset at the index defined by the method parameter
	 * @param element An integer value identifying the offset to retrieve
	 * @return A Long valued byte offset for a specific element of the .dta file
	 */
	public Long getOffset(Integer element) {
		return this.stmap.get(element);
	}


}
