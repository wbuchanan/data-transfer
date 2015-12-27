package org.paces.data.Stata.Version;

/**
 * Created by billy on 12/27/15.
 */
public class V114 extends OldFormats implements FileVersion {

	private Integer versionNumber;

	V114(String versionID) {
		this.versionNumber = Integer.valueOf(versionID);
	}

	@Override
	public Integer getVersionNumber() {
		return this.versionNumber;
	}

}

