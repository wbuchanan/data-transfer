package org.paces.data.Stata.Readers.FileElements;

import org.paces.data.Stata.Readers.StConvert;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by billy on 12/25/15.
 */
public class DtaCharacteristics {


	private ByteOrder sbo;
	private final Map<Integer, List<String>> characteristics =
							new HashMap<>();
	private Long off;
	private RandomAccessFile x;

	/**
	 * Class constructor for dataset characteristics
	 * @param x RandomAccessFile representing the .dta file
	 * @param charOffset The offset used to access the characteristics
	 * @param sbo The value use to swap byte order (when necessary)
	 * @throws IOException Exception thrown if there is an error reading the
	 * file
	 */
	DtaCharacteristics(RandomAccessFile x, Long charOffset, ByteOrder
					   sbo) throws IOException {
		setFile(x);
		setCharOffset(charOffset);
		setByteOrder(sbo);
		if (hasCharacteristics()) setCharacteristics();

	}

	public Map<Integer, List<String>> getCharacteristics() {
		return this.characteristics;
	}

	public List<String> getCharacteristic(Integer idx) {
		return this.characteristics.get(idx);
	}

	public void setByteOrder(ByteOrder s) {
		this.sbo = s;
	}

	public void setFile(RandomAccessFile x) {
		this.x = x;
	}

	public void setCharOffset(Long offset) {
		this.off = offset;
	}

	public Boolean hasCharacteristics() throws IOException {
		this.x.seek(this.off);
		byte[] noCharacteristics = new byte[18];
		this.x.read(noCharacteristics);
		return "</characteristics>".equals(new String(noCharacteristics));
	}

	/**
	 * Method used to read/parse the dataset characteristics from bytes
	 * @throws IOException
	 */
	public void setCharacteristics() throws IOException {
		this.x.seek(this.off);
		byte[] nxt = new byte[4];
		this.x.read(nxt);
		int mapid = 0;
		while ("<ch>".equals(new String(nxt))) {
			List<String> characteristic = new ArrayList<String>();
			byte[] charLen = new byte[4];
			this.x.read(charLen);
			byte[] varname = new byte[129];
			byte[] charname = new byte[129];
			Integer chContents = StConvert.toStata(charLen, this.sbo, (int) 0) - 258;
			byte[] character = new byte[chContents];
			this.x.read(varname);
			this.x.read(character);
			characteristic.add(0, StConvert.toStata(varname, this.sbo, ""));
			characteristic.add(1, StConvert.toStata(charname, this.sbo, ""));
			characteristic.add(2, StConvert.toStata(character, this.sbo, ""));
			characteristics.put(mapid, characteristic);
			this.x.seek(this.x.getFilePointer() + 5);
			this.x.read(nxt);
			mapid++;
		}
	}

}
