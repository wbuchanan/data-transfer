package org.paces.data.Stata.Readers.Blobs;

import java.util.Map;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public interface OldBlob {

	/**
	 * Method used to retrieve the observation index number
	 * @return An 4-byte long value containing the observation number for the
	 * strL datum
	 */
	Integer getO();

	/**
	 * Method called by containerize to construct a container with variable
	 * index precedent for version 117 files.
	 * @param v The variable index value of the GSO
	 * @param o The observation index value of the GSO
	 * @param contents The String datum contained in the GSO
	 * @return A container passed back to the containerize method and then
	 * passed back to the calling method.
	 */
	Map<Integer, Map<Integer, String>> varContainer(Integer v, Integer o, String contents);

	/**
	 * Method called by containerize to construct a container with observation
	 * index precedent for version 117 files.
	 * @param v The variable index value of the GSO
	 * @param o The observation index value of the GSO
	 * @param contents The String datum contained in the GSO
	 * @return A container passed back to the containerize method and then
	 * passed back to the calling method.
	 */
	Map<Integer, Map<Integer, String>> obContainer(Integer v, Integer o, String contents);

}
