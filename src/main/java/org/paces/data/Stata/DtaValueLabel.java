package org.paces.data.Stata;

/**
 * Created by billy on 12/25/15.
 */
public class DtaValueLabel {

	private final byte[] tableLength = new byte[4];

	private final byte[] labelName = new byte[129];

	private final byte[] padding = new byte[3];

	private final byte[] valueLabelTableEntries = new byte[4];

	private final byte[] valueLabelTableTextLength = new byte[4];

	private final byte[] valueLabelTableTextOffsets = new byte[4];

	private final byte[] valueLabelTableSortedValues = new byte[4];

}
