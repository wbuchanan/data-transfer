package org.paces.data.Stata.Readers.DtaExceptions;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class DtaCorrupt extends Exception {

	private static final String msg = "This file does not follow the format " +
			"of 113, 114, 115, 117, or 118 release formats.  Try reading the " +
			"file into Stata and saving it again.  If the problem persists " +
			"the file may have significant/unrecoverable corruption.";

	private static final long serialVersionUID = 7779311;

	public DtaCorrupt() {
		new DtaCorrupt(msg);
	}

	public DtaCorrupt(String msg) {
		super(msg);
	}

	public DtaCorrupt(Throwable cause) {
		super(cause);
	}

	public DtaCorrupt(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DtaCorrupt(String msg, Throwable cause, Boolean suppressed,
					  Boolean writeStackTrace) {
		super(msg, cause, suppressed, writeStackTrace);
	}

}
