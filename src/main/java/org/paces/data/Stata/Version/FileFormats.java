package org.paces.data.Stata.Version;

/**
 * Created by billy on 12/27/15.
 */
public class FileFormats {

	public static FileVersion getVersion(String versionNumber) {
		switch(versionNumber) {

			case "113":
				return new V113(versionNumber);

			case "114":
				return new V114(versionNumber);

			case "115":
				return new V115(versionNumber);

			case "117":
				return new V117(versionNumber);

			default:
				return new V118(versionNumber);

		}

	}

}
