package org.paces.data.Stata.Version;

import org.paces.data.Stata.Readers.FileElements.DtaStrLs;
import org.paces.data.Stata.Readers.FileElements.ElementTags;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by billy on 12/27/15.
 */
public class NewFormats extends FileConstants {

	protected static final int RELEASE = 3;
	protected static final int BYTEORDER = 3;
	protected static final int NVARS = 2;
	public static final int DATASET_LABEL_LENGTH = 2;
	public static final int DATETIME = 1;
	public static final int DATETIME_ENTRY = 17;

	/**
	 * Member used to Parse/Store Blobs
	 */
	public DtaStrLs blobs = null;

	protected static final int RELEASE_OFFSET = ElementTags.getTagValue("odta") +
												ElementTags.getTagValue("oheader") +
												ElementTags.getTagValue("orelease");

	protected static final int BYTEORDER_OFFSET = RELEASE_OFFSET + RELEASE +
												  ElementTags.getTagValue("crelease") +
			   									  ElementTags.getTagValue("obyteorder");

	protected static final int NVARS_OFFSET = BYTEORDER_OFFSET + BYTEORDER +
			 								  ElementTags.getTagValue("cbyteorder") +
											  ElementTags.getTagValue("onvars");

	public ElementTags etags;

	protected NewFormats() {
		this.etags = new ElementTags();
	}

	/**
	 * Method to access the element tags
	 * @return
	 */
	public ElementTags getEtags() {
		return this.etags;

	}

	/***
	 * Method to return a Map object for type map after the introduction of
	 * Binary Large OBject types in Stata
	 * @return A map with integer keys and string values
	 */
	public Map<Integer, Integer> getDataTypes() {
		Map<Integer, Integer> stataTypes = new HashMap<>();
		for (int i = 1; i < 2045; i++) {
			stataTypes.put(i, i);
		}
		stataTypes.put(32768, 0);
		stataTypes.put(65526, 8);
		stataTypes.put(65527, 4);
		stataTypes.put(65528, 4);
		stataTypes.put(65529, 2);
		stataTypes.put(65530, 1);
		return stataTypes;
	}


}
