package org.paces.data.Stata;

/**
 * Created by billy on 12/25/15.
 */
public class DtaStrLs {

	private final byte[] strlStart = new byte[3];
	private final byte[] strlVariable = new byte[4];
	private final byte[] strlObservation = new byte[8];
	private final byte[] strlType = new byte[1];
	private final byte[] strlLength = new byte[4];
	private byte[] strlContents;



}
