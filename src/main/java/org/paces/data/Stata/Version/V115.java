package org.paces.data.Stata.Version;

import java.util.Map;

/**
 * Created by billy on 12/27/15.
 */
public class V115 extends OldFormats implements FileVersion {

	private Integer versionNumber;

	public V115(String versionID) {
		this.versionNumber = Integer.valueOf(versionID);
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


