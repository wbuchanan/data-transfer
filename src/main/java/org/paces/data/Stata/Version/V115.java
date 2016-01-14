package org.paces.data.Stata.Version;

import org.paces.data.Stata.Readers.DtaFileParser;
import org.paces.data.Stata.Readers.FileElements.DtaStrLs;
import org.paces.data.Stata.Readers.FileElements.DtaValueLabel;

import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;

/**
 * Created by billy on 12/27/15.
 */
public class V115 extends OldFormats implements FileVersion, DtaFileParser {

	private Integer versionNumber;

	public V115(String versionID, RandomAccessFile stdata) {
		this.versionNumber = Integer.valueOf(versionID);
	}

	@Override
	public Integer getVersionNumber() {
		return this.versionNumber;
	}

	@Override
	public Map<Integer, Integer> getDataTypes() {
		return super.getDataTypes();
	}

	@Override
	public void setFileMap(RandomAccessFile datafile, Long fileMapOffset) {

	}

	@Override
	public void setDataTypes(RandomAccessFile datafile, Long dataTypeOffset, ByteOrder sbo) {

	}

	@Override
	public void setVariableNames(RandomAccessFile datafile, Long varNameOffset, ByteOrder sbo) {

	}

	@Override
	public void setSortOrder(RandomAccessFile datafile, Long sortOrderOffset, ByteOrder sbo) {

	}

	@Override
	public void setDisplayFmts(RandomAccessFile datafile, Long fmtsOffset, ByteOrder sbo) {

	}

	@Override
	public void setValLabNames(RandomAccessFile datafile, Long valLabNameOffset, ByteOrder sbo) {

	}

	@Override
	public void setVarLabs(RandomAccessFile datafile, Long varLabsOffset, ByteOrder sbo) {

	}

	@Override
	public void setCharacteristics(RandomAccessFile datafile, Long varLabsOffset, ByteOrder sbo) {

	}

	@Override
	public void setStrLs(RandomAccessFile datafile, Long varLabsOffset, ByteOrder sbo) {

	}

	@Override
	public void setValueLabels(RandomAccessFile datafile, Long varLabsOffset, ByteOrder sbo) {

	}

	@Override
	public void setData(RandomAccessFile datafile, Long varLabsOffset, ByteOrder sbo) {

	}

	@Override
	public List<Long> getFileMap() {
		return null;
	}

	@Override
	public List<Object> getDtaTypes() {
		return null;
	}

	@Override
	public List<String> getVariableNames() {
		return null;
	}

	@Override
	public List<Integer> getSortOrder() {
		return null;
	}

	@Override
	public List<String> getDisplayFmts() {
		return null;
	}

	@Override
	public List<String> getValLabNames() {
		return null;
	}

	@Override
	public List<String> getVarLabs() {
		return null;
	}

	@Override
	public List<List<String>> getCharacteristics() {
		return null;
	}

	@Override
	public List<DtaStrLs> getStrLs() {
		return null;
	}

	@Override
	public List<DtaValueLabel> getValueLabels() {
		return null;
	}

	@Override
	public Map<Long, Map<Integer, Object>> getData() {
		return null;
	}
}


