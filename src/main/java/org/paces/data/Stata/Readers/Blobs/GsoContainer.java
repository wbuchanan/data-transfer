package org.paces.data.Stata.Readers.Blobs;

import org.paces.data.Stata.Readers.DtaExceptions.StrlException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class GsoContainer {

	List<?> strlContainer = new ArrayList<>();


	/**
	 * Class to store strL data in a single class/object
	 * @param x A RandomAccessFile object used to read the bytes from the
	 *             dataset
	 * @param position The position in the file where the GSO datum should be
	 *                    read from
	 *
	 * @param bo The ByteOrder used to correctly interpret the bytes in the file
	 * @throws IOException An exception thrown if there are issues reading
	 * from the RandomAccessFile object.
	 * @throws StrlException An exception thrown if the datum does not begin
	 * with the string "GSO" as specified in the .dta file specification.
	 */
	public GsoContainer(RandomAccessFile x, Long position, ByteOrder bo) throws
			IOException, StrlException {


	}


}
