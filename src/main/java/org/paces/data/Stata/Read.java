package org.paces.data.Stata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/***
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Read {

	// Array to store a single byte
	byte[] firstFileByte = new byte[1];

	RandomAccessFile stataFile;

	Read(String[] args) {

		try {
			this.stataFile = new RandomAccessFile(new File(args[0]), "r");
			checkVersion(this.stataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void checkVersion(RandomAccessFile stData) {
		try {
			stData.seek(0);
			stData.read(firstFileByte);
			if (firstFileByte[0] >= 113 && firstFileByte[0] <= 115) {

			} else if (firstFileByte[0] == 60) {

			} else {
				throw new DtaCorrupt();
			}
		} catch (IOException | DtaCorrupt e) {
			e.printStackTrace();
		}
	}


	/*

import java.io.*;
import java.nio.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import java.nio.ByteBuffer.*;
import java.nio.IntBuffer;
import java.nio.IntBuffer.*;
import java.io.RandomAccessFile;
import static java.nio.StataByteOrder.*;
import static java.nio.StataByteOrder.nativeOrder;
DTA_OPEN = "<stata_dta>".getBytes().length;
HEADER_OPEN = "<header>".getBytes().length;
RELEASE_OPEN = "<release>".getBytes().length;
RELEASE_OFFSET = DTA_OPEN + HEADER_OPEN + RELEASE_OPEN;
RELEASE = 3;
RELEASE_CLOSE = "</release>".getBytes().length;
BYTEORDER_OPEN = "<byteorder>".getBytes().length;
BYTEORDER_OFFSET = RELEASE_OFFSET + RELEASE + RELEASE_CLOSE + BYTEORDER_OPEN;
BYTEORDER = 3;
BYTEORDER_CLOSE = "</byteorder>".getBytes().length;
NVARS_OPEN = "<K>".getBytes().length;
NVARS_OFFSET = BYTEORDER_OFFSET + BYTEORDER + BYTEORDER_CLOSE + NVARS_OPEN;
NVARS = 2;
NVARS_CLOSE = "</K>".getBytes().length;
NOBS_OPEN = "<N>".getBytes().length;
NOBS_OFFSET = NVARS_OFFSET + NVARS + NVARS_CLOSE + NOBS_OPEN;
NOBS = 8;
NOBS_CLOSE = "</N>".getBytes().length;
DATASET_LABEL_OPEN = "<label>".getBytes().length;
DATASET_LABEL_OFFSET = NOBS_OFFSET + NOBS + NOBS_CLOSE + DATASET_LABEL_OPEN;
DATASET_LAB_LENGTH = 2;
DATASET_LABEL_CLOSE = "</label>".getBytes().length;
DATASET_TIMESTAMP_OPEN = "<timestamp>".getBytes().length;

x.getFilePointer()
byte[] release = new byte[RELEASE];
byte[] byteorder = new byte[BYTEORDER];
byte[] nvars = new byte[NVARS];
byte[] nobs = new byte[NOBS];
byte[] dataSetLabelLength = new byte[DATASET_LAB_LENGTH];
byte[] dataSetTimeStamp = new byte[DATASET_TIMESTAMP];

x.seek(RELEASE_OFFSET);
x.read(release);
Integer releaseVersion = Integer.valueOf(new String(release));

x.seek(BYTEORDER_OFFSET);
x.read(byteorder);
new String(byteorder)

x.seek(NVARS_OFFSET);
x.read(nvars);

Integer numberVariables = ByteBuffer.wrap(nvars).order(StataByteOrder.nativeOrder()).getShort() & 0xFFFF;

x.seek(NOBS_OFFSET);
x.read(nobs);

numberObservations = ByteBuffer.wrap(nobs).order(StataByteOrder.nativeOrder()).getLong() & 0xFFFFFFFF;


x.seek(DATASET_LABEL_OFFSET);
x.read(dataSetLabelLength);

datasetLabel = ByteBuffer.wrap(dataSetLabelLength).order(StataByteOrder.nativeOrder()).getShort() & 0xFFFF;

byte[] datasetTextLabel = new byte[datasetLabel];
x.read(datasetTextLabel)

DATASET_TIMESTAMP_OFFSET = DATASET_LABEL_OFFSET + DATASET_LAB_LENGTH + datasetLabel + DATASET_LABEL_CLOSE + DATASET_TIMESTAMP_OPEN;
DATASET_TIMESTAMP_CLOSE = "</timestamp>".getBytes().length;
HEADER_CLOSE = "</header>".getBytes().length;
MAP_OPEN = "<map>".getBytes().length;

DATASET_TIMESTAMP = 1;

x.seek(DATASET_TIMESTAMP_OFFSET)
byte[] hasTimeStamp = new byte[DATASET_TIMESTAMP];
x.read(hasTimeStamp);

int timeStampValue = (int) hasTimeStamp[0];

if (timeStampValue == 17) {
	byte[] timeStampText = new byte[17];
	x.read(timeStampText);
	int currentPosition = x.getFilePointer();
} else {
	int currentPosition = x.getFilePointer();
}

MAP_OFFSET = currentPosition + DATASET_TIMESTAMP_CLOSE + HEADER_CLOSE + MAP_OPEN;


MAP_CLOSE = "</map>".getBytes().length;




x.seek(DATASET_TIMESTAMP_OFFSET);
x.read(dataSetTimeStamp);


// File opening tag
public static final int DTA_OPEN = "<stata_dta>".getBytes().length;

// File header opening tag
public static final int HEADER_OPEN = "<header>".getBytes().length;

// File release version opening tag
public static final int RELEASE_OPEN = "<release>".getBytes().length;

// Release offset should be 28 bytes
public static final int RELEASE_OFFSET = DTA_OPEN + HEADER_OPEN + RELEASE_OPEN;

// Length of the byte array to use for the release ID
public static final int RELEASE = 3;

// File release version closing tag
public static final int RELEASE_CLOSE = "</release>".getBytes().length;

// File release endianness opening tag
public static final int BYTEORDER_OPEN = "<byteorder>".getBytes().length;

public static final int BYTEORDER_OFFSET = RELEASE_OFFSET + RELEASE + RELEASE_CLOSE + BYTEORDER_OPEN;

// File is a three character string that is either MSF or LSF
// Should start at byte 52
public static final int BYTEORDER = 3;

// File endianness closinging tag
public static final int BYTEORDER_CLOSE = "</byteorder>".getBytes().length;

// File number of variables opening tag
public static final int NVARS_OPEN = "<K>".getBytes().length;

public static final int NVARS_OFFSET = BYTEORDER_OFFSET + BYTEORDER + BYTEORDER_CLOSE + NVARS_OPEN;

// File 2-byte unsigned integer
public static final int NVARS = 2;

// File number of variables closing tag
public static final int NVARS_CLOSE = "<K>".getBytes().length;

// File number of observations opening tag
public static final int NOBS_OPEN = "<N>".getBytes().length;

public static final int NOBS_OFFSET = NVARS_OFFSET + NVARS + NVARS_CLOSE + NOBS_OPEN;

// Number of observations is recorded in an 8 byte unsigned integer
public static final int NOBS = 8;

// File number of observations closing tag
public static final int NOBS_CLOSE = "<N>".getBytes().length;

// Opening tag for the datast label
public static final int DATASET_LABEL_OPEN = "<label>".getBytes().length;

public static final int DATASET_LABEL_OFFSET = NOBS_OFFSET + NOBS + NOBS_CLOSE + DATASET_LABEL_OPEN;

// 2-byte unsigned int with length of the label string (up to 80 characters)
// If field contains 2 bytes of 0 (0000) there is no label
public static final int DATASET_LAB_LENGTH = 2;

// Closing tag for the datast label
public static final int DATASET_LABEL_CLOSE = "<label>".getBytes().length;

// Opening tag for the data set timestamp
public static final int DATASET_TIMESTAMP_OPEN = "<timestamp>".getBytes().length;

public static final int DATASET_TIMESTAMP_OFFSET = DATASET_LABEL_OFFSET + DATASET_LAB_LENGTH + DATASET_LABEL_CLOSE + DATASET_TIMESTAMP_OPEN;

// A 1 byte unsigned integer if the value is equal to decimal 17 the
// timestamp string is formatted like:
// dd Mon ccyy hh:mm
public static final int DATASET_TIMESTAMP = 18;

// Closing tag for the data set timestamp
public static final int DATASET_TIMESTAMP_CLOSE = "<timestamp>".getBytes().length;

// Closing tag for the file header
public static final int HEADER_CLOSE = "</header>".getBytes().length;

// Opening tag for the file map
public static final int MAP_OPEN = "<map>".getBytes().length;

public static final int MAP_OFFSET = DATASET_TIMESTAMP_OFFSET +
DATASET_TIMESTAMP + DATASET_TIMESTAMP_CLOSE + HEADER_CLOSE + MAP_OPEN;

// The byte length will be 112 = 14 * 8 for the 14 8 byte elements
public static final List<byte[8]> MAP = new ArrayList<byte[8]>();

// Closing tag for the file map
public static final int MAP_CLOSE = "</map>".getBytes().length;

// Opening tag for variable types
public static final int VARIABLE_TYPES_OPEN = "<variable_types>".getBytes().length;

// Closing tag for variable types
public static final int VARIABLE_TYPES_LENGTH = ;

// Opening tag for variable names
public static final int = "<varnames>".getBytes().length;

// The number of bits reserved for each variable name up to 32 UTF-8 Characters
// each variable name is terminated by a binary zero if it is < 32 characters
public static final int VARNAME_LENGTH = 129;

// Closing tag for variable names
public static final int VARNAMES_CLOSE = "</varnames>".getBytes().length;

// Opening tag for sort order list
public static final int SORTLIST_OPEN = "<sortlist>".getBytes().length;

// Closing tag for sort order list
public static final int SORTLIST_CLOSE= "</sortlist>".getBytes().length;

// Opening tag for the display formats
public static final int FORMATS_OPEN = "<formats>".getBytes().length;

// Opening tag for the display formats
public static final int FORMATS_CLOSE = "</formats>".getBytes().length;

// Opening tag for value label names
public static final int VALLABNAMES_OPEN= "<value_label_names>".getBytes().length;

// Closing tag for value label names
public static final int VALLABNAMES_CLOSE = "</value_label_names>".getBytes().length;

// Opening tag for variable labels
public static final int VARLABS_OPEN = "<variable_labels>".getBytes().length;

// Closing tag for variable labels
public static final int VARLABS_CLOSE = "</variable_labels>".getBytes().length;

// Opening tag for characteristics
public static final int CHARACTERISTICS_OPEN = "<characteristics>".getBytes().length;

// Opening tag for individual characteristic
public static final int CHARACTERISTC_INDIV_OPEN = "<ch>".getBytes().length;

// The number of bytes to reserve for a characteristic variable name
public static final int CHARACTERISTIC_VARNAME = 128;

// The number of bytes to reserve for a characteristic name
public static final int CHARACTERISTIC_CHARNAME = 128;

// The maximum number of bytes available for any characteristic content
public static final int CHARACTERISTIC_CONTENTS = 67784;

// Closing tag for individual characteristic
public static final int CHARACTERISTC_INDIV_CLOSE = "</ch>".getBytes().length;

// Closing tag for characteristics
public static final int CHARACTERISTICS_CLOSE = "</characteristics>".getBytes().length;

// Opening tag for data
public static final int = "<data>".getBytes().length;

// Closing tag for data
public static final int = "</data>".getBytes().length;

// Opening tag for binary large objects
public static final int = "<strls>".getBytes().length;

// Closing tag for binary large objects
public static final int = "</strls>".getBytes().length;

// Opening tag for value labels
public static final int = "<value_labels>".getBytes().length;

// Bytes for the value label definitinion
public static final int VALLAB_LEN = "".getBytes().length;

// Bytes for the value label name
public static final int VALLAB_LABEL_NAME = "".getBytes().length;

// Bytes for the value label padding
public static final int VALLAB_PADDING = "".getBytes().length;

// Bytes for the value label label table (e.g., multidimensional array)
public static final int VALLAB_VALUE_LABEL_TABLE = "".getBytes().length;

// Bytes for the value label n field
public static final int VALLAB_N = "".getBytes().length;

// Bytes for the value label text length
public static final int VALLAB_TXTLEN = "".getBytes().length;

// Bytes for the value label offsets array
public static final int VALLAB_OFFSET_ARRAY = "".getBytes().length;

// Bytes for the value label sorted numeric value array
public static final int VALLAB_VALUE_ARRAY = "".getBytes().length;

// Bytes for the value label text array/table
public static final int VALLAB_TXT_ARRAY = "".getBytes().length;

// Closing tag for value labels
public static final int = "</value_labels>".getBytes().length;

// Creates file object to use for reading/parsing
RandomAccessFile x = new RandomAccessFile(new File("/Users/billy/Desktop/stmissings.dta"), "r");

*/

}

