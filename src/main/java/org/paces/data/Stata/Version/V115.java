package org.paces.data.Stata.Version;

/**
 * Created by billy on 12/27/15.
 */
public class V115 extends OldFormats implements FileVersion {

	private Integer versionNumber;

	V115(String versionID) {
		this.versionNumber = Integer.valueOf(versionID);
	}

	@Override
	public Integer getVersionNumber() {
		return this.versionNumber;
	}

}


