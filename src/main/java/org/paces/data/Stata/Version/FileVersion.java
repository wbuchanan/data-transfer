package org.paces.data.Stata.Version;

import java.util.Map;

/**
 * Created by billy on 12/27/15.
 */
public abstract interface FileVersion {

	public Integer getVersionNumber();

	Map<Integer, Integer> getDataTypes();
}
