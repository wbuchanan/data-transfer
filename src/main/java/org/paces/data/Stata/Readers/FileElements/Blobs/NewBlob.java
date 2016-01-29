package org.paces.data.Stata.Readers.FileElements.Blobs;

import java.util.Map;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public interface NewBlob {

	/**
	 * Method used to retrieve the observation index number
	 * @return An 8-byte long value containing the observation number for the
	 * strL datum
	 */
	Long getO();

	/**
	 * Method called by containerize to construct a container with variable
	 * index precedent for version 118 files.
	 * @param v The variable index value of the GSO
	 * @param o The observation index value of the GSO
	 * @param contents The String datum contained in the GSO
	 * @return A container passed back to the containerize method and then
	 * passed back to the calling method.
	 */
	Map<Integer, Map<Long, String>> varContainer(Integer v, Long o, String contents);

	/**
	 * Method called by containerize to construct a container with observation
	 * index precedent for version 118 files.
	 * @param v The variable index value of the GSO
	 * @param o The observation index value of the GSO
	 * @param contents The String datum contained in the GSO
	 * @return A container passed back to the containerize method and then
	 * passed back to the calling method.
	 */
	Map<Long, Map<Integer, String>> obContainer(Integer v, Long o, String contents);



}
