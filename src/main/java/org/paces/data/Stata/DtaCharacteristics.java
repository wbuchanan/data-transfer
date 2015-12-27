package org.paces.data.Stata;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by billy on 12/25/15.
 */
public class DtaCharacteristics {


	private final ByteOrder swap;
	private final int open = "<ch>".getBytes().length;
	private final int between = "</ch><ch>".getBytes().length;
	private final int close = "</ch>".getBytes().length;

	private List<DtaCharacteristic> characteristics = new ArrayList<>();

	private long filepos;
	private RandomAccessFile thefile;

	DtaCharacteristics(RandomAccessFile stataFile, Long charOffset, ByteOrder
					   sbo)
			throws IOException {
		this.thefile = stataFile;
		this.swap = sbo;
		this.filepos = charOffset;
		this.thefile.seek(this.filepos + this.open);
		while (hasNext()) {
			characteristics.add(loadCharacteristics());
		}
	}

	public Boolean hasNext() throws IOException {
		byte[] test = new byte[4];
		this.thefile.read(test);
		if ("<ch>".equals(new String(test))) {
			return true;
		} else {
			this.thefile.seek(this.filepos);
			return false;
		}
	}

	public DtaCharacteristic loadCharacteristics() throws IOException {
		byte[] varName  = new byte[129];
		byte[] charName = new byte[129];
		byte[] characteristicLength = new byte[4];
		this.thefile.read(characteristicLength);
		List<byte[]> characteristic = new ArrayList<>();
		Integer charlength = StConvert.toStata(characteristicLength, this.swap, (int) 0);
		this.thefile.read(varName);
		this.thefile.read(charName);
		byte[] contents = new byte[charlength - 259];
		this.thefile.read(contents);
		characteristic.add(0, varName);
		characteristic.add(1, charName);
		characteristic.add(2, contents);
		this.filepos = this.filepos + this.close;
		return new DtaCharacteristic(characteristic, this.swap);
	}

	public List<String> getCharacteristic(int idx) {
		return this.characteristics.get(idx).getCharacteristic();
	}

	public List<List<String>> getCharacteristics() {
		List<List<String>> retval = new ArrayList<>();
		this.characteristics.parallelStream().map(x -> retval.add(x.getCharacteristic()));
		return retval;
	}

}
