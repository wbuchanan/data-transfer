package org.paces.data.Stata.Readers.DtaExceptions;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class CharacteristicException extends Exception {
	
	private static final String msg = "Malformed characteristic.  If file " +
			"version is 117 or 118 this could indicate that the " +
			"characteristic did not begin with the characters '<ch>'";
	
	private static final long serialVersionUID = 7779311;
	
	public CharacteristicException() {
		new CharacteristicException(msg);
	}
	
	public CharacteristicException(String msg) {
		super(msg);
	}
	
	public CharacteristicException(Throwable cause) {
		super(cause);
	}
	
	public CharacteristicException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public CharacteristicException(String msg, Throwable cause, Boolean suppressed,
						 Boolean writeStackTrace) {
		super(msg, cause, suppressed, writeStackTrace);
	}
	
}
