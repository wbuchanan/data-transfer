package org.paces.data.Stata.Readers;

import org.paces.data.Stata.Readers.DtaExceptions.DtaCorrupt;
import org.paces.data.Stata.Version.FileFormats;
import org.paces.data.Stata.Version.FileVersion;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/***
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Load {

	// Array to store a single byte
	byte[] firstFileByte = new byte[1];

	RandomAccessFile stataFile;

	FileVersion<?> filetype;

	/**
	 * Class constructor.  Used to read file into memory and create the
	 * appropriate version object that will handle offsets/construction of
	 * other objects.
	 * @param args Name of the Stata file to load into memory
	 */
	Load(String[] args) {

		try {
			this.stataFile = new RandomAccessFile(new File(args[0]), "r");
			checkVersion(this.stataFile);
		} catch (IOException | DtaCorrupt e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method used to check the release version of the file
	 * @param stData The object containing the .dta data set in memory
	 */
	public void checkVersion(RandomAccessFile stData) throws IOException, DtaCorrupt {
		stData.seek(0);
		stData.read(firstFileByte);
		if (firstFileByte[0] >= 113 && firstFileByte[0] <= 115) {
			filetype = FileFormats.getVersion(String.valueOf(firstFileByte[0]));
		} else if (firstFileByte[0] == 60) {
			stData.seek(28);
			byte[] newfmt = new byte[3];
			stData.read(newfmt);
			filetype = FileFormats.getVersion(new String(newfmt));
		} else {
			throw new DtaCorrupt();
		}
	}


}

