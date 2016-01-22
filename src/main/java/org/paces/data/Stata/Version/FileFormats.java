package org.paces.data.Stata.Version;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Class used to initialize readers for specific file formats
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class FileFormats {

	/**
	 * Method to construct the file type objects
	 * @param stdata The RandomAccessFile object with the connection to the
	 *                  Stata data file
	 * @param release The release version number
	 * @param endian The string containing the endianness underwhich the file
	 *                  was written
	 * @param K The number of variables in the dataset
	 * @param N The number of observations in the dataset (Integer)
	 * @param datasetLabel A label attached to the dataset
	 * @param datasetTimeStamp Time stamp when dataset was
	 *                            created/modified/written
	 * @param mapOffset The offset to the start of the file map
	 * @return A File version object of the type specified
	 */
	public static FileVersion<?> getVersion(ByteBuffer stdata,
											Integer release,
											ByteOrder endian,
											Short K,
											Integer N,
											String datasetLabel,
											String datasetTimeStamp,
											Integer mapOffset) {
		switch(release) {

			case 113:
				 return new V113(stdata, release, endian, K,
						 N, datasetLabel, datasetTimeStamp, mapOffset);

			case 114:
				return new V114(stdata, release, endian, K,
						N, datasetLabel, datasetTimeStamp, mapOffset);

			case 115:
				return new V115(stdata, release, endian, K,
						N, datasetLabel, datasetTimeStamp, mapOffset);

			default:
				return new V117(stdata, release, endian, K,
						N, datasetLabel, datasetTimeStamp, mapOffset);

		}

	}

	/**
	 * Method to construct the file type objects
	 * @param stdata The RandomAccessFile object with the connection to the
	 *                  Stata data file
	 * @param release The release version number
	 * @param endian The string containing the endianness underwhich the file
	 *                  was written
	 * @param K The number of variables in the dataset
	 * @param N The number of observations in the dataset (Long)
	 * @param datasetLabel A label attached to the dataset
	 * @param datasetTimeStamp Time stamp when dataset was
	 *                            created/modified/written
	 * @param mapOffset The offset to the start of the file map
	 * @return A File version object of the type specified
	 */
	public static V118 getVersion(ByteBuffer stdata,
								  Integer release,
								  ByteOrder endian,
								  Short K,
								  Long N,
								  String datasetLabel,
								  String datasetTimeStamp,
								  Integer mapOffset) {
		return new V118(stdata, release, endian, K,
				N, datasetLabel, datasetTimeStamp, mapOffset);

	}

}
