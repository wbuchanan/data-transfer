package org.paces.data.Stata.Readers.DtaExceptions;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class StrlException extends Exception {

	private static final String msg = "The strL datum did not begin with the " +
			"string GSO.  This may be a data corruption issue or could be a " +
			"bug in the Java library.  Please include the code you were " +
			"attempting to excute as well as the data set (if possible) when " +
			"submitting an issue ticket to http://github" +
			".com/wbuchanan/data-transfer";

	private static final long serialVersionUID = 7779311;

	public StrlException() {
		new StrlException(msg);
	}

	public StrlException(String msg) {
		super(msg);
	}

	public StrlException(Throwable cause) {
		super(cause);
	}

	public StrlException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public StrlException(String msg, Throwable cause, Boolean suppressed,
					  Boolean writeStackTrace) {
		super(msg, cause, suppressed, writeStackTrace);
	}

}
