package org.paces.data.Stata.Version;

import org.paces.data.Stata.Readers.StataByteOrder;

import java.io.RandomAccessFile;

/**
 * Class used to initialize readers for specific file formats
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class FileFormats {

	/**
	 * Method to construct the file type objects
	 * @param versionNumber A version string identifying which class to
	 *                         initialize
	 * @param stdata The RandomAccessFile object with the connection to the
	 *                  Stata data file
	 * @param release The release version number
	 * @param endian The string containing the endianness underwhich the file
	 *                  was written
	 * @param K The number of variables in the dataset
	 * @param N The number of observations in the dataset
	 * @param datasetLabel A label attached to the dataset
	 * @param datasetTimeStamp Time stamp when dataset was
	 *                            created/modified/written
	 * @param mapOffset The offset to the start of the file map
	 * @param sbo A StataByteOrder object containing indicator used to swap
	 *               byte order if needed
	 * @return A File version object of the type specified
	 */
	public static FileVersion<?> getVersion(String versionNumber,
											RandomAccessFile stdata,
											Integer release,
											String endian,
											Short K,
											Long N,
											String datasetLabel,
											String datasetTimeStamp,
											Long mapOffset,
											StataByteOrder sbo) {
		switch(versionNumber) {

			case "113":
				 return new V113(stdata, release, endian, K,
						 N, datasetLabel, datasetTimeStamp, mapOffset, sbo);

			case "114":
				return new V114(stdata, release, endian, K,
						N, datasetLabel, datasetTimeStamp, mapOffset, sbo);

			case "115":
				return new V115(stdata, release, endian, K,
						N, datasetLabel, datasetTimeStamp, mapOffset, sbo);

			case "117":
				return new V117(stdata, release, endian, K,
						N, datasetLabel, datasetTimeStamp, mapOffset, sbo);

			default:
				return new V118(stdata, release, endian, K,
						N, datasetLabel, datasetTimeStamp, mapOffset, sbo);

		}

	}

}
