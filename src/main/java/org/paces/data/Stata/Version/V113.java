package org.paces.data.Stata.Version;

import java.util.Map;

/**
 * Created by billy on 12/27/15.
 */
public class V113 extends OldFormats implements FileVersion {

	private Integer versionNumber;

	public V113(String versionID) {
		this.versionNumber = Integer.valueOf(versionID);
	}

	/***
	 * The version 113 getFormatListContainer method overrides the inherited
	 * method from the OldFormats class.
	 * @param nvars An integer containing the number of variables in the data
	 *                 set
	 * @return A byte array with # variables elements each 12 bytes wide used
	 * to store the display format of the data.
	 */
	@Override
	public byte[][] getFormatListContainer(Integer nvars) {
		return new byte[nvars][12];
	}

	@Override
	public Integer getVersionNumber() {
		return this.versionNumber;
	}

	@Override
	public Map<Integer, Integer> getDataTypes() {
		return super.getDataTypes();
	}

}

