package org.paces.data.Stata.Readers;

import org.paces.data.Stata.Version.NewFormats;

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
	
	// Release offset should be 28 bytes
	private final int RELEASE_OFFSET = NewFormats.getTagValue("odta") + NewFormats.getTagValue("oheader") + NewFormats.getTagValue("orelease");

	// Length of the byte array to use for the release ID
	private final int RELEASE = 3;
	
	// File release version closing tag
	private final int RELEASE_CLOSE = "</release>".getBytes().length;

	// File release endianness opening tag
	private final int BYTEORDER_OPEN = "<byteorder>".getBytes().length;

	private final int BYTEORDER_OFFSET = RELEASE_OFFSET + RELEASE +
			NewFormats.getTagValue("crelease") + NewFormats.getTagValue("obyteorder");
	
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
	private final int NVARS_CLOSE = "</K>".getBytes().length;
	
	// File number of observations opening tag
	private final int NOBS_OPEN = "<N>".getBytes().length;
	
	private final int NOBS_OFFSET = NVARS_OFFSET + NVARS + NVARS_CLOSE + NOBS_OPEN;
	
	// Number of observations is recorded in an 8 byte unsigned integer
	private final int NOBS = 8;

	// File number of observations closing tag
	private final int NOBS_CLOSE = "</N>".getBytes().length;
	
	// Opening tag for the datast label
	private final int DATASET_LABEL_OPEN = "<label>".getBytes().length;
	
	private final int DATASET_LABEL_OFFSET = NOBS_OFFSET + NOBS + NOBS_CLOSE + DATASET_LABEL_OPEN;

	// Closing tag for the datast label
	private final int DATASET_LABEL_CLOSE = "</label>".getBytes().length;

	// Opening tag for the data set timestamp
	private final int DATASET_TIMESTAMP_OPEN = "<timestamp>".getBytes().length;

	// Length of the closing tag of the time stamp
	private final int DATASET_TIMESTAMP_CLOSE = "</timestamp>".getBytes().length;

	// Length of the header closing tag
	private final int HEADER_CLOSE = "</header>".getBytes().length;

	// Length of the file map object opening tag
	private final int MAP_OPEN = "<map>".getBytes().length;

	// 2-byte unsigned int with length of the label string (up to 80 characters)
	// If field contains 2 bytes of 0 (0000) there is no label
	private final int DATASET_LAB_LENGTH = 2;

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

	// Gets the length of the closing tag for the file map object
	private final int MAP_CLOSE = "</map>".getBytes().length;

	private final int VARIABLE_TYPES_OPEN = "<variable_types>".getBytes().length;
	private final int VARIABLE_TYPES_CLOSE = "</variable_types>".getBytes().length;

	private final int VARNAMES_OPEN = "<varnames>".getBytes().length;
	private final int VARNAMES_CLOSE = "</varnames>".getBytes().length;

	private final int SORTORDER_OPEN = "<sortlist>".getBytes().length;
	private final int SORTORDER_CLOSE = "</sortlist>".getBytes().length;

	private final int DISPLAY_FMT_OPEN = "<formats>".getBytes().length;
	private final int DISPLAY_FMT_CLOSE = "</formats>".getBytes().length;

	private final int VALUELAB_NAMES_OPEN = "<value_label_names>".getBytes().length;
	private final int VALUELAB_NAMES_CLOSE = "</value_label_names>".getBytes().length;

	private final int VARIABLE_LABELS_OPEN = "<variable_labels>".getBytes().length;
	private final int VARIABLE_LABELS_CLOSE = "</variable_labels>".getBytes().length;

	private final int CHARACTERISTICS_OPEN = "<characteristics>".getBytes().length;
	private final int CHARACTERISTICS_CLOSE = "</characteristics>".getBytes().length;

	private final int DATA_OPEN = "<data>".getBytes().length;
	private final int DATA_CLOSE = "</data>".getBytes().length;

	private final int STRLS_OPEN = "<strls>".getBytes().length;
	private final int STRLS_CLOSE = "</strls>".getBytes().length;

	private final int VALUE_LABELS_OPEN = "<value_labels>".getBytes().length;
	private final int VALUE_LABELS_CLOSE = "</value_labels>".getBytes().length;

	private final int VALLAB_OPEN = "<lbl>".getBytes().length;
	private final int VALLAB_CLOSE = "</lbl>".getBytes().length;

	private final int CHAR_OPEN = "<ch>".getBytes().length;
	private final int CHAR_CLOSE = "</ch>".getBytes().length;

	private final List<Long> offsetMods = new ArrayList<Long>();


	/**
	 * Class constructor method.  Currently will only require a single
	 * argument in the string array that will point towards the file to consume.
	 * @param args Location of Stata data set stored in .dta format
	 * @throws IOException Exception thrown if there is an error reading the
	 * file from disk.
	 */
	StataWithStrLs(String[] args) throws IOException {

		setOffsetModifiers();

		// Creates the object that will be read from
		RandomAccessFile x = new RandomAccessFile(new File(args[0]), "r");
		
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

			Long bytePosition = StConvert.toStata(position, byteOrder.swapto, (long) 0);

			// Add the numeric value to the list object
			filemap.add(bytePosition + this.offsetMods.get(i));
			
		} // End Loop over values in the filemap object


	} // Ends class constructor

	/***
	 * Method creates a List of Integer offset values to adjust the values
	 * returned by the filemap object
	 */
	public void setOffsetModifiers() {
		this.offsetMods.add(0, (long) 0);
		this.offsetMods.add(1, (long) MAP_OPEN);
		this.offsetMods.add(2, (long) VARIABLE_TYPES_OPEN);
		this.offsetMods.add(3, (long) VARNAMES_OPEN);
		this.offsetMods.add(4, (long) SORTORDER_OPEN);
		this.offsetMods.add(5, (long) DISPLAY_FMT_OPEN);
		this.offsetMods.add(6, (long) VALUELAB_NAMES_OPEN);
		this.offsetMods.add(7, (long) VARIABLE_LABELS_OPEN);
		this.offsetMods.add(8, (long) CHARACTERISTICS_OPEN);
		this.offsetMods.add(9, (long) DATA_OPEN);
		this.offsetMods.add(10, (long) STRLS_OPEN);
		this.offsetMods.add(11, (long) VALUE_LABELS_OPEN);
		this.offsetMods.add(12, (long) 0);
		this.offsetMods.add(13, (long) 0);
	}


	
	
}
