package org.paces.data.Stata;

import org.paces.data.Stata.Readers.Load;

/**
 * Created by billy on 1/16/16.
 */
public class DTA {

	public static void main(String[] args) {
		if ("read".equals(args[0])) read(args);
	}

	public static void read(String[] args) {
		Load stataFile = new Load(args);
	}

	public static void write(String[] args) {

	}



}
