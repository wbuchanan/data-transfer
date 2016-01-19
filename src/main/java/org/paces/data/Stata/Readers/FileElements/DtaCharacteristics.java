package org.paces.data.Stata.Readers.FileElements;

import org.paces.data.Stata.Readers.StConvert;
import org.paces.data.Stata.Version.FileVersion;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by billy on 12/25/15.
 */
public class DtaCharacteristics {


	private final Map<Integer, List<String>> characteristics = new HashMap<>();
	private final RandomAccessFile x;
	private final FileVersion<?> stdata;
	private Long endChar;

	/**
	 * Class constructor for dataset characteristics
	 * @param stdata A release version specific object representing the .dta
	 *                  file
	 * @param charOffset The offset used to access the characteristics
	 * @throws IOException Exception thrown if there is an error reading the
	 * file
	 */
	DtaCharacteristics(FileVersion<?> stdata, Long charOffset) throws IOException {
		this.stdata = stdata;
		this.x = stdata.getDtaFile();
		this.x.seek(charOffset);
		setCharacteristics(stdata.getVersionNumber());
	}

	public Map<Integer, List<String>> getCharacteristics() {
		return this.characteristics;
	}

	public List<String> getCharacteristic(Integer idx) {
		return this.characteristics.get(idx);
	}


	public Long getEndPosition() {
		return this.endChar;
	}

	/**
	 * Method used to dispatch the appropriate method for reading the dataset
	 * characteristics/expansion fields
	 * @param release The release version of the .dta file
	 * @throws IOException Exception thrown if there are issues reading the
	 * data from the file.
	 */
	public void setCharacteristics(Integer release) throws IOException {
		switch(release) {
			case 117:
			case 118: {
				setNewChars();
			}
			default:
				setOldChars();
		}
	}

	/**
	 * Method used to read characteristics from files created by Stata
	 * version <= 12
	 * @throws IOException Throws error if there is an issue reading the data
	 * from the file
	 */
	public void setOldChars() throws IOException {

		// 1 byte byte array used to read the type slot
		byte[] dtype = new byte[1];

		// 4 byte array used to interpret the integer characteristic length
		byte[] dlen = new byte[4];

		// Reads the type slot
		x.read(dtype);

		// Reads the length slot
		x.read(dlen);

		// Convert the type slot to a byte value
		Byte type = StConvert.toStata(dtype, this.stdata.getByteSwap(), (byte) 0);

		// Convert the length slot to a 4 byte Integer
		Integer len = StConvert.toStata(dlen, this.stdata.getByteSwap(), 0);

		// Initialize an index variable to index data in the map object
		int idx = 0;

		// As long as there aren't five bytes of binary zeros
		while (len != 0 && type != 0) {

			// The length of the contents are determind by the 4 byte int
			byte[] contents = new byte[len];

			// Read the contents
			x.read(contents);

			// List object to store the contents (for consistency)
			List<String> chardata = new ArrayList<>();

			// Empty string for the variable name slot
			chardata.add(0, "");

			// Empty string for the characteristic name slot
			chardata.add(1, "");

			// String with the contents of the characteristic
			chardata.add(2, StConvert.toStata(contents, this.stdata.getByteSwap(), ""));

			// Adds the characteristic to the member variable used to store
			// all of them
			characteristics.put(idx, chardata);

			// Increment the index variable
			idx++;

			// Reinitialize the byte array variable for the type slot
			dtype = new byte[1];

			// Reinitialize the byte array variable for the characteristic
			// length slot
			dlen = new byte[4];

			// Read the next type variable
			x.read(dtype);

			// Read the next length variable
			x.read(dlen);

		} // End While loop for binary zero type and length

		// Sets the end position of the characteristics
		this.endChar = x.getFilePointer();

	} // End Method declaration for files created with Stata <= version 12

	/**
	 * Method used to read/parse the dataset characteristics from bytes
	 * @throws IOException
	 */
	public void setNewChars() throws IOException {
		byte[] nxt = new byte[4];
		x.read(nxt);
		int mapid = 0;
		while ("<ch>".equals(new String(nxt))) {
			List<String> characteristic = new ArrayList<String>();
			byte[] charLen = new byte[4];
			this.x.read(charLen);
			byte[] varname = new byte[129];
			byte[] charname = new byte[129];
			Integer chContents = StConvert.toStata(charLen, this.stdata.getByteSwap(), (int) 0)	- 258;
			byte[] character = new byte[chContents];
			this.x.read(varname);
			this.x.read(character);
			characteristic.add(0, StConvert.toStata(varname, this.stdata.getByteSwap(), ""));
			characteristic.add(1, StConvert.toStata(charname, this.stdata.getByteSwap(), ""));
			characteristic.add(2, StConvert.toStata(character, this.stdata.getByteSwap(), ""));
			characteristics.put(mapid, characteristic);
			this.x.seek(this.x.getFilePointer() + 5);
			this.x.read(nxt);
			mapid++;
		}
		this.endChar = x.getFilePointer() + ElementTags.getTagValue("ccharacteristics");
	}

}
