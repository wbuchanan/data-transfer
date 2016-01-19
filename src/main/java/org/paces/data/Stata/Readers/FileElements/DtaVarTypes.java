package org.paces.data.Stata.Readers.FileElements;

import org.paces.data.Stata.Readers.StConvert;
import org.paces.data.Stata.Version.FileVersion;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by billy on 12/25/15.
 */
public class DtaVarTypes {

	private List<Integer> stVariableTypes = new ArrayList<>();

	private FileVersion version = null;

	private List<Integer> reserveBytes = new ArrayList<>();

	protected ConcurrentSkipListMap<Integer, Integer> stmap = new
			ConcurrentSkipListMap<Integer, Integer>();

	private RandomAccessFile x;

	protected String[] missings = {".", ".a", ".b", ".c", ".d", ".e", ".f", ".g",
			".h", ".i", ".j", ".k", ".l", ".m", ".n", ".o", ".p", ".q", ".r",
			".s", ".t", ".u", ".v", ".w", ".x", ".y", ".z"};


	DtaVarTypes(FileVersion<?> stdata, Long offset, Integer nvars) {
		RandomAccessFile x = stdata.getDtaFile();

		try {

			x.seek(offset);

			for (int i = 0; i < nvars; i++) {

				byte[] stvartypes = getContainer(stdata.getVersionNumber());

				x.read(stvartypes);

				stVariableTypes.add(i, StConvert.toStata(stvartypes, stdata.getByteSwap(), 0));

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public List<Integer> getVariableTypes() {
		return this.stVariableTypes;
	}

	public byte[] getContainer(Integer version) {
		switch (version) {
			case 117:
			case 118: {
				return new byte[2];
			}
			default:
				return new byte[1];
		}
	}

	/**
	 * Method to handle dispatch of constructing the variable type map object
	 * @param version The .dta file release version number
	 */
	public void setTypes(Integer version) {

		// Switch statement on version number
		switch (version) {

			// For files created by Stata 13-14
			case 117:
			case 118: {

				// Call the setNewType method which includes support for
				// strings up to 2045 bytes in length and strLs
				setNewTypes();

				// Break out of the statement
				break;

			} // End Cases for files created by Stata 13/14

			// For files created by earlier version of Stata
			default:

				// Call the setOldTypes method which supports strings up to
				// 244 bytes in length
				setOldTypes();

				// Break out of the statement
				break;

		} // End Switch block

	} // End method declaration

	/**
	 * Method to generate map from variable types to the number of bytes
	 * required to read each type into memory
	 */
	public void setOldTypes() {
		for (Integer i = 1; i < 245; i++) {
			this.stmap.put(i, i);
		}
		this.stmap.put(251, 1);
		this.stmap.put(252, 2);
		this.stmap.put(253, 4);
		this.stmap.put(254, 4);
		this.stmap.put(255, 8);

	}

	/**
	 * Method to generate map from variable types to the number of bytes
	 * required to read each type into memory;
	 * Need to handle BLOBs/strLs that have a byte value of 0 in the map.
	 */
	public void setNewTypes() {
		for (Integer i = 1; i < 2046; i++) {
			this.stmap.put(i, i);
		}
		stmap.put(65526, 8);
		stmap.put(65527, 4);
		stmap.put(65528, 4);
		stmap.put(65529, 2);
		stmap.put(65530, 1);
		stmap.put(32768, 0);

	}


}
