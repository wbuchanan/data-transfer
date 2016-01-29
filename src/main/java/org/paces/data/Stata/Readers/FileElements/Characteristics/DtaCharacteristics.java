package org.paces.data.Stata.Readers.FileElements.Characteristics;

import org.paces.data.Stata.Readers.DtaExceptions.CharacteristicException;
import org.paces.data.Stata.Version.FileVersion;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class DtaCharacteristics {

	/**
	 * Member used to store a container of data set characteristics
	 */
	private Map<Integer, Characteristic> characteristics = new HashMap<>();

	/**
	 * Member containing the total byte length of all characteristics
	 */
	private Long charLength = 0L;

	/**
	 * Class constructor for dataset characteristics
	 * @param stdata A release version specific object representing the .dta
	 *                  file
	 * @param charOffset The offset used to access the characteristics
	 * @throws IOException Exception thrown if there is an error reading the
	 * file
	 */
	public DtaCharacteristics(FileVersion<?> stdata, Long charOffset) throws IOException {
		FileChannel fc = stdata.getDtaFile();
		ByteBuffer x = fc.map(FileChannel.MapMode.READ_ONLY, charOffset, fc.size() - charOffset);
		x.order(stdata.getByteSwap());
		if (stdata.getVersionNumber() >= 117) setNewCharacteristics(x);
		else setOldCharacteristics(x);
	}

	/**
	 * Method used to access the container of all characteristics
	 * @return An Integer keyed map of characteristic objects
	 */
	public Map<Integer, Characteristic> getCharacteristics() {
		return this.characteristics;
	}

	/**
	 * Method to access a single characteristic
	 * @param idx The index value to access (begins at 0)
	 * @return A list of string values :
	 * <table>
	 *     <th>
	 *         <td>Position</td><td>Contents</td>
	 *     </th>
	 *     <tr>
	 *         <td>0</td><td>The type of characteristic (119 = Binary </td>
	 *     </tr>
	 *     <tr>
	 *         <td>1</td><td></td>
	 *     </tr>
	 *     <tr>
	 *         <td>2</td><td></td>
	 *     </tr>
	 *     <tr>
	 *         <td>3</td><td></td>
	 *     </tr>
	 *     <tr>
	 *         <td>4</td><td>String contents of the characteristic</td>
	 *     </tr>
	 *     <tr>
	 *         <td>5</td><td>Length of this characteristic in bytes</td>
	 *     </tr>
	 * </table>
	 */
	public List<String> getCharacteristic(Integer idx) {
		return this.characteristics.get(idx).getCharacteristic();
	}

	/**
	 * Method to access the total number of bytes for all characteristics
	 * @return A long valued integer with the total byte length of all
	 * characteristics
	 */
	public Long getCharLength() {
		return this.charLength;
	}

	/**
	 * Method used to parse characteristics from files with release # >= 117
	 * @param x A bytebuffer from which to read the datum
	 */
	public void setNewCharacteristics(ByteBuffer x) {
		int counter = 0;
		try {
			Characteristic newchar = new NewCharacteristic(x);
			this.charLength += newchar.getTotalLength();
			while (newchar.hasNext()) {
				characteristics.put(counter, newchar);
				newchar = new NewCharacteristic(x);
				this.charLength += newchar.getTotalLength();
				counter++;
			}
		} catch (CharacteristicException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used to parse characteristics from files with release # < 117
	 * @param x A bytebuffer from which to read the datum
	 */
	public void setOldCharacteristics(ByteBuffer x) {
		int counter = 0;
		Characteristic oldchar = new OldCharacteristic(x);
		this.charLength += oldchar.getTotalLength();
		while (oldchar.hasNext()) {
			characteristics.put(counter, oldchar);
			oldchar = new OldCharacteristic(x);
			this.charLength += oldchar.getTotalLength();
			counter++;
		}
	}

} // End of Class declaration
