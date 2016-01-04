package org.paces.data.Stata.Readers;

import org.paces.data.Stata.Readers.FileElements.DtaStrLs;
import org.paces.data.Stata.Readers.FileElements.DtaValueLabel;

import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;

/**
 * Created by billy on 12/25/15.
 */
public abstract interface DtaFileParser {

	void setFileMap(RandomAccessFile datafile, Long fileMapOffset);
	void setDataTypes(RandomAccessFile datafile, Long dataTypeOffset, ByteOrder sbo);
	void setVariableNames(RandomAccessFile datafile, Long varNameOffset, ByteOrder sbo);
	void setSortOrder(RandomAccessFile datafile, Long sortOrderOffset, ByteOrder sbo);
	void setDisplayFmts(RandomAccessFile datafile, Long fmtsOffset, ByteOrder sbo);
	void setValLabNames(RandomAccessFile datafile, Long valLabNameOffset, ByteOrder sbo);
	void setVarLabs(RandomAccessFile datafile, Long varLabsOffset, ByteOrder sbo);
	void setCharacteristics(RandomAccessFile datafile, Long varLabsOffset, ByteOrder sbo);
	void setStrLs(RandomAccessFile datafile, Long varLabsOffset, ByteOrder sbo);
	void setValueLabels(RandomAccessFile datafile, Long varLabsOffset, ByteOrder sbo);
	void setData(RandomAccessFile datafile, Long varLabsOffset, ByteOrder sbo);

	List<Long> getFileMap();
	List<Object> getDataTypes();
	List<String> getVariableNames();
	List<Integer> getSortOrder();
	List<String> getDisplayFmts();
	List<String> getValLabNames();
	List<String> getVarLabs();
	List<List<String>> getCharacteristics();
	List<DtaStrLs> getStrLs();
	List<DtaValueLabel> getValueLabels();
	Map<Long, Map<Integer, Object>> getData();

}
