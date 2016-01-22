package org.paces.data.Stata.Readers;

import org.paces.data.Stata.Readers.DtaExceptions.DtaCorrupt;
import org.paces.data.Stata.Version.FileFormats;
import org.paces.data.Stata.Version.FileVersion;
import org.paces.data.Stata.Version.NewFormats;
import org.paces.data.Stata.Version.OldFormats;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/***
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Load {


	protected MappedByteBuffer stataFile;
	protected List<byte[]> fileHeader;
	protected List<Object> headerData;
	protected FileVersion<?> versionedFile;
	private ByteOrder endian;
	private Integer release;
	private Short K;
	private Long N;
	private String datasetLabel;
	private String datasetTimeStamp;
	private Integer mapOffset;

	/**
	 * Class constructor.  Used to read file into memory and create the
	 * appropriate version object that will handle offsets/construction of
	 * other objects.
	 * @param args Name of the Stata file to load into memory
	 */
	public Load(String[] args) {

		try {
			RandomAccessFile raf = new RandomAccessFile(new File(args[0]), "rw");
			FileChannel fc = raf.getChannel();
			this.stataFile = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size());
			this.fileHeader = checkVersion(this.stataFile);
			if (this.release >= 113 && this.release <= 115) {
				this.headerData = OldFormats.readHeader(stataFile, fileHeader);
			} else  {
				this.headerData = NewFormats.readHeader(stataFile, fileHeader);
			}
			parseHeader();
			this.versionedFile = FileFormats.getVersion(this.stataFile, this.release,
								 this.endian, this.K, this.N, this.datasetLabel,
								 this.datasetTimeStamp, this.mapOffset);
		} catch (IOException | DtaCorrupt e) {
			System.out.println(String.valueOf(e));
		}

	}

	/**
	 * Method used to parse and recast the data from the file header into a
	 * consistent format.  To do this, the 4 byte integer values for the
	 * number of observations are stored in a Long variable.
	 */
	public void parseHeader() {
		this.release = (Integer) this.headerData.get(0);
		this.endian = (ByteOrder) this.headerData.get(1);
		this.K = (Short) this.headerData.get(2);
		this.N = (Long) this.headerData.get(3);
		this.datasetLabel = (String) this.headerData.get(4);
		this.datasetTimeStamp = (String) this.headerData.get(5);
		this.mapOffset = (Integer) this.headerData.get(6);
	}

	/**
	 * Method used to check the release version of the file
	 * @param stData The object containing the .dta data set in memory
	 */
	public List<byte[]> checkVersion(ByteBuffer stData) throws IOException,
			DtaCorrupt {
		byte firstFileByte = stData.get();
		if (firstFileByte >= 113 && firstFileByte <= 115) {
			this.release = (int) firstFileByte;
			stData.position(0);
			return getByteReader((int) firstFileByte);
		} else if (firstFileByte == 60) {
			stData.position(28);
			byte[] newfmt = new byte[3];
			stData.get(newfmt);
			this.release = Integer.valueOf(new String(newfmt));
			stData.position(0);
			return getByteReader(Integer.valueOf(new String(newfmt)));
		} else {
			throw new DtaCorrupt();
		}
	}



	/**
	 * Method used to create a List of byte array objects used to parse the
	 * file header
	 * @param version The version of Stata from which the file was generated
	 * @return A list of byte arrays used to read the binary data from the file
	 */
	public List<byte[]> getByteReader(Integer version) {

		// Switch statement used for different file configurations
		switch (version) {

			case 113:
			case 114:
			case 115: {

				// Initialize object for files from Stata 8, 10, and 12
				List<byte[]> oldfmt = new ArrayList<byte[]>();

				// Release version
				oldfmt.add(new byte[1]);

				// Byte order a value of 1 is MSF 2 is LSF
				oldfmt.add(new byte[1]);

				// File type 0x01
				oldfmt.add(new byte[1]);

				// Unused 0x00
				oldfmt.add(new byte[1]);

				// Short valued number of variables
				oldfmt.add(new byte[2]);

				// Integer valued number of observations
				oldfmt.add(new byte[4]);

				// \0 Terminated dataset label
				oldfmt.add(new byte[81]);

				// Dataset time stamp
				oldfmt.add(new byte[18]);

				// Returns the object
				return oldfmt;

			} // End case for Stata 8, 10, and 12 files

			// For Stata 13 files
			case 117:

				// Initialize the list object to store the data
				List<byte[]> stata13 = new ArrayList<byte[]>();

				// Release version
				stata13.add(new byte[3]);

				// Byte order of the file
				stata13.add(new byte[2]);

				// Short value number of variables
				stata13.add(new byte[2]);

				// Integer number of observations
				stata13.add(new byte[4]);

				// For the length of the dataset label
				stata13.add(new byte[1]);

				// For the dataset timestamp
				stata13.add(new byte[1]);

				// Return the object
				return stata13;

			// For Stata Files >= Version 14
			default:

				// Initialize the list object for Stata 14 > files
				List<byte[]> stata14 = new ArrayList<byte[]>();

				// Release version
				stata14.add(new byte[3]);

				// Byte order of the file
				stata14.add(new byte[2]);

				// Short value number of variables
				stata14.add(new byte[2]);

				// Integer number of observations
				stata14.add(new byte[8]);

				// For the length of the dataset label
				stata14.add(new byte[2]);

				// For the dataset timestamp
				stata14.add(new byte[1]);

				// Return the object
				return stata14;

		} // End of switch statement

	}

	/**
	 * Method to access the file header list of byte arrays
	 * @return A list of byte arrays based on the file specification for the
	 * release.  These are empty byte arrays that are used to read the bytes
	 * in the file that set the header properties.
	 */
	public List<byte[]> getHeader() {
		return this.fileHeader;
	}

}

