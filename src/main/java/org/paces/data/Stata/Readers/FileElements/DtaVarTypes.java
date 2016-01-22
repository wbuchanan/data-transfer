package org.paces.data.Stata.Readers.FileElements;

import org.paces.data.Stata.Version.FileVersion;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by billy on 12/25/15.
 */
public class DtaVarTypes {

	private List<Integer> stVariableTypes = new ArrayList<>();

	protected ConcurrentSkipListMap<Integer, Integer> stmap = new ConcurrentSkipListMap<>();
	protected ConcurrentSkipListMap<Integer, String> stDispatcher = new ConcurrentSkipListMap<>();

	protected String[] missings = {".", ".a", ".b", ".c", ".d", ".e", ".f", ".g",
			".h", ".i", ".j", ".k", ".l", ".m", ".n", ".o", ".p", ".q", ".r",
			".s", ".t", ".u", ".v", ".w", ".x", ".y", ".z"};

	DtaVarTypes(FileVersion<?> data, Integer offset) {
		if (data.getVersionNumber() >= 117) {
			setNewTypes();
			DtaVarTypesNew(data.getDtaFile(), offset, data.getNumVars());
		} else {
			setOldTypes();
			DtaVarTypesOld(data.getDtaFile(), offset, data.getNumVars());
		}
	}

	public void DtaVarTypesNew(ByteBuffer stdata, Integer offset, Short nvars) {
		stdata.position(offset);
		for (int i = 0; i < nvars; i++) {
			stVariableTypes.add(i, (int) stdata.getShort());
		}
	}

	public void DtaVarTypesOld(ByteBuffer stdata, Integer offset, Short nvars) {
		stdata.position(offset);
		for (int i = 0; i < nvars; i++) {
			stVariableTypes.add(i, (int) stdata.get());
		}
	}

	public List<Integer> getVariableTypes() {
		return this.stVariableTypes;
	}

	/**
	 * Method to generate map from variable types to the number of bytes
	 * required to read each type into memory
	 */
	public void setOldTypes() {
		for (Integer i = 1; i < 245; i++) {
			this.stmap.put(i, i);
			this.stDispatcher.put(i, "asCharBuffer().allocate(" + String.valueOf(i) + ").get");
		}
		this.stmap.put(251, 1);
		this.stmap.put(252, 2);
		this.stmap.put(253, 4);
		this.stmap.put(254, 4);
		this.stmap.put(255, 8);
		this.stDispatcher.put(251, "get");
		this.stDispatcher.put(252, "getShort");
		this.stDispatcher.put(253, "getInt");
		this.stDispatcher.put(254, "getFloat");
		this.stDispatcher.put(255, "getDouble");

	}

	/**
	 * Method to generate map from variable types to the number of bytes
	 * required to read each type into memory;
	 * Need to handle BLOBs/strLs that have a byte value of 0 in the map.
	 */
	public void setNewTypes() {
		for (Integer i = 1; i < 2046; i++) {
			this.stmap.put(i, i);
			this.stDispatcher.put(i, "asCharBuffer().allocate(" + String.valueOf(i) + ").get");
		}
		stmap.put(65526, 8);
		stmap.put(65527, 4);
		stmap.put(65528, 4);
		stmap.put(65529, 2);
		stmap.put(65530, 1);
		stmap.put(32768, 0);
		this.stDispatcher.put(65526, "getDouble");
		this.stDispatcher.put(65527, "getFloat");
		this.stDispatcher.put(65528, "getInt");
		this.stDispatcher.put(65529, "getShort");
		this.stDispatcher.put(65530, "get");
		this.stDispatcher.put(32768, "asCharBuffer");

	}


}
