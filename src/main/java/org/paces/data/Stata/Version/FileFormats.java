package org.paces.data.Stata.Version;

import java.io.RandomAccessFile;

/**
 * Created by billy on 12/27/15.
 */
public class FileFormats {

	/**
	 * Method to construct the file type objects
	 * @param versionNumber A version string identifying which class to
	 *                         initialize
	 * @param stdata The RandomAccessFile object with the connection to the
	 *                  Stata data file
	 * @return A File version object of the type specified
	 */
	public static FileVersion<?> getVersion(String versionNumber, RandomAccessFile stdata) {
		switch(versionNumber) {

			case "113":
				 return new V113(versionNumber, stdata);

			case "114":
				return new V114(versionNumber, stdata);

			case "115":
				return new V115(versionNumber, stdata);

			case "117":
				return new V117(versionNumber, stdata);

			default:
				return new V118(versionNumber, stdata);

		}

	}

}
