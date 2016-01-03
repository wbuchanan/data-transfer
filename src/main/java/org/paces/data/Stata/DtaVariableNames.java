package org.paces.data.Stata;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by billy on 12/25/15.
 */
public class DtaVariableNames {

	private List<String> varnames = new ArrayList<>();

	DtaVariableNames(RandomAccessFile x, List<Long> offsets, Integer
			numberVariables, StataByteOrder sbo) {
		try {
			x.seek(offsets.get(3));
			List<String> varnms = new ArrayList<String>();
			for (int i = 0; i < numberVariables; i++) {
				byte[] vnm = new byte[129];
				x.read(vnm);
				this.varnames.add(i, StConvert.toStata(vnm, StataByteOrder.swapto, ""));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<String> getVarNames() {
		return this.varnames;
	}

	public String getVarName(Integer index) {
		return this.varnames.get(index);
	}

}
