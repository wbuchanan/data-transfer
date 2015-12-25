package org.paces.data.Stata;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by billy on 12/25/15.
 */
public class StataWithStrLs {

	private final int MAX_STRING_LENGTH = 2045;
	private final int MAX_STRL_LENGTH = 32768;
	private final int MAX_BYTE_LENGTH = 1;
	private final int MAX_INT_LENGTH = 2;
	private final int MAX_LONG_LENGTH = 4;
	private final int MAX_FLOAT_LENGTH = 4;
	private final int MAX_DOUBLE_LENGTH = 8;
	
	private long timestampPosition;
	
	// File opening tag
	private final int DTA_OPEN = "<stata_dta>".getBytes().length;
	
	// File header opening tag
	private final int HEADER_OPEN = "<header>".getBytes().length;
	
	// File release version opening tag
	private final int RELEASE_OPEN = "<release>".getBytes().length;
	
	// Release offset should be 28 bytes
	private final int RELEASE_OFFSET = DTA_OPEN + HEADER_OPEN + RELEASE_OPEN;
	
	// Length of the byte array to use for the release ID
	private final int RELEASE = 3;
	
	// File release version closing tag
	private final int RELEASE_CLOSE = "</release>".getBytes().length;
	
	// File release endianness opening tag
	private final int BYTEORDER_OPEN = "<byteorder>".getBytes().length;
	
	private final int BYTEORDER_OFFSET = RELEASE_OFFSET + RELEASE + RELEASE_CLOSE + BYTEORDER_OPEN;
	
	// File is a three character string that is either MSF or LSF Should start at byte 52
	private final int BYTEORDER = 3;
	
	// File endianness closinging tag
	private final int BYTEORDER_CLOSE = "</byteorder>".getBytes().length;
	
	// File number of variables opening tag
	private final int NVARS_OPEN = "<K>".getBytes().length;
	
	private final int NVARS_OFFSET = BYTEORDER_OFFSET + BYTEORDER + BYTEORDER_CLOSE + NVARS_OPEN;
	
	// File 2-byte unsigned integer
	private final int NVARS = 2;
	
	// File number of variables closing tag
	private final int NVARS_CLOSE = "<K>".getBytes().length;
	
	// File number of observations opening tag
	private final int NOBS_OPEN = "<N>".getBytes().length;
	
	private final int NOBS_OFFSET = NVARS_OFFSET + NVARS + NVARS_CLOSE + NOBS_OPEN;
	
	// Number of observations is recorded in an 8 byte unsigned integer
	private final int NOBS = 8;
	
	// File number of observations closing tag
	private final int NOBS_CLOSE = "<N>".getBytes().length;
	
	// Opening tag for the datast label
	private final int DATASET_LABEL_OPEN = "<label>".getBytes().length;
	
	private final int DATASET_LABEL_OFFSET = NOBS_OFFSET + NOBS + NOBS_CLOSE + DATASET_LABEL_OPEN;

	// Length of the closing tag of the time stamp
	private final int DATASET_TIMESTAMP_CLOSE = "</timestamp>".getBytes().length;

	// Length of the header closing tag
	private final int HEADER_CLOSE = "</header>".getBytes().length;

	// Length of the file map object opening tag
	private final int MAP_OPEN = "<map>".getBytes().length;

	// 2-byte unsigned int with length of the label string (up to 80 characters)
	// If field contains 2 bytes of 0 (0000) there is no label
	private final int DATASET_LAB_LENGTH = 2;

	// Closing tag for the datast label
	private final int DATASET_LABEL_CLOSE = "<label>".getBytes().length;

	// Opening tag for the data set timestamp
	private final int DATASET_TIMESTAMP_OPEN = "<timestamp>".getBytes().length;

	// Offset to get to the start of the dataset timestamp
	private final int DATASET_TIMESTAMP_OFFSET = DATASET_LABEL_OFFSET + DATASET_LAB_LENGTH + DATASET_LABEL_CLOSE + DATASET_TIMESTAMP_OPEN;

	// Used to store the bytes containing the release version number
	private byte[] release = new byte[RELEASE];

	// Used to store the byte order the file was written with
	private byte[] byteorder = new byte[BYTEORDER];

	// Used to store the bytes with the number of variables in the data set
	private byte[] nvars = new byte[NVARS];

	// Used to store the bytes with the number of observations in the dataset
	private byte[] nobs = new byte[NOBS];

	// Used to store the bytes with the number of bytes used for the dataset label
	private byte[] dataSetLabelLength = new byte[DATASET_LAB_LENGTH];

	// Used to store the byte that contains the length of the dataset timestamp or a value of 0
	private byte[] dataSetTimeStamp = new byte[1];

	// byte order object containing endianness data and swap order
	private StataByteOrder byteOrder;

	// Creates object to store value with length of the time stamp
	byte[] hasTimeStamp = new byte[1];

	// Allocate a byte array of width 17
	byte[] timeStampText = new byte[17];

	byte[] variableTypes = new byte[2];

	byte[] variableLabels = new byte[129];

	byte[] sortList = new byte[2];

	byte[] displayFormats = new byte[57];

	byte[] valueLabelNames = new byte[129];

	byte[] variablelabels = new byte[321];

	byte[] characteristicLength = new byte[4];

	byte[] charPartVarName = new byte[128];

	byte[] charPartCharName = new byte[128];

	private final int MAX_CHARACTERISTIC_CONTENTS = 67784;

	// Gets the length of the closing tag for the file map object
	private final int MAP_CLOSE = "</map>".getBytes().length;

	StataWithStrLs(String[] args) throws IOException {

		// Creates the object that will be read from
		RandomAccessFile x = new RandomAccessFile(new File("/Users/billy/Desktop/stmissings.dta"), "r");
		
		// Move to the position containing the release offset version number
		x.seek(RELEASE_OFFSET);
		
		// Read the bytes into the release byte array
		x.read(release);
		
		// Store the integer value of the release version string
		Integer releaseVersion = Integer.valueOf(new String(release));
		
		// Move to the location where the byte offset bytes
		x.seek(BYTEORDER_OFFSET);
		
		// Read the three bytes containing the Stata endianness identifier
		x.read(byteorder);

		byteOrder = new StataByteOrder(new String(byteorder));

		// Move to the start of the bytes containing the number of variables in the dataset
		x.seek(NVARS_OFFSET);
		
		// Read the bytes into the byte array
		x.read(nvars);
		
		// Stores the number of variables in the file
		int numberVariables = StConvert.toStata(nvars, byteOrder.swapto, (short) 0);
		
		// Moves to location where the observation offset exists
		x.seek(NOBS_OFFSET);
		
		// Reads the number of observations byte array into an object
		x.read(nobs);
		
		// Decodes the number of observations into a Long integer
		long numberObservations = StConvert.toStata(nobs, byteOrder.swapto, (long) 0);

		// Moves to the location of the dataset label
		x.seek(DATASET_LABEL_OFFSET);
		
		// Reads the byte array containing the width of the dataset label
		x.read(dataSetLabelLength);
		
		// Gets the length of the dataset label
		int datasetLabel = StConvert.toStata(dataSetLabelLength, byteOrder.swapto, (short) 0);

		// Create a byte array with the dataset label with the width of the array specified by the datasetLabel object
		byte[] datasetTextLabel = new byte[datasetLabel];
			
		// Read the bytes into the byte array
		x.read(datasetTextLabel);
			
		// Get the dataset label
		String stataDataSetLabel = StConvert.toStata(datasetTextLabel, byteOrder.swapto, "");

		// Offset for the timestamp
		long datatsoffset = DATASET_LABEL_OFFSET + DATASET_LAB_LENGTH + datasetLabel + DATASET_LABEL_CLOSE + DATASET_TIMESTAMP_OPEN;
		
		// Move to the location in the file where the timestamp starts
		x.seek(DATASET_TIMESTAMP_OFFSET);
		
		// Reads the length of the timestamp into the byte array
		x.read(hasTimeStamp);
		
		// Converts value to an int
		int timeStampValue = (int) hasTimeStamp[0];
		
		// If the timestampValue is 17
		if (timeStampValue == 17) {
			
			// Read the bytes into the array
			x.read(timeStampText);
			
			// Get the current position in the file
			timestampPosition = x.getFilePointer();
			
			// If there is no timestamp
		} else {
			
			// Record the position in the file if there is no time stamp
			timestampPosition = x.getFilePointer();
			
		} // End ELSE Block for no timestamp 
		
		// Creates the offset for the start of the bytes for the file map object
		long MAP_OFFSET = timestampPosition + DATASET_TIMESTAMP_CLOSE + HEADER_CLOSE + MAP_OPEN;
		
		// Move to the start of the file map object
		x.seek(MAP_OFFSET);
		
		List<Long> filemap = new ArrayList<Long>();
		
		// Loop over the bytes in the filemap object and store them in the position object
		for (int i = 0; i < 14; i++) {
			
			// Create a byte array to store the offsets for the file
			byte[] position = new byte[8];
			
			// Stores an array of 8 bytes in sequential positions of the array
			x.read(position);

			// Add the numeric value to the list object
			filemap.add(StConvert.toStata(position, byteOrder.swapto, (long) 0));
			
		} // End Loop over values in the filemap object


	}
	
	
	
	
	
	
}
