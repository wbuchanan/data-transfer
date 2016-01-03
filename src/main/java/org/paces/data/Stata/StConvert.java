package org.paces.data.Stata;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class StConvert {

	private static String getStataString(byte[] stringType, java.nio.ByteOrder sbo) {
		ByteBuffer.wrap(stringType).order(sbo);
		int endpos = 0;
		while (endpos < stringType.length) {
			if (stringType[endpos] == 0) break;
			else endpos++;
		}
		return new String(stringType, 0, endpos);
	}

	private static Byte getStataByte(byte[] byteType, java.nio.ByteOrder sbo) {
		return ByteBuffer.wrap(byteType).order(sbo).get();
	}

	private static Short getStataShort(byte[] shortType, java.nio.ByteOrder sbo) {
		return ByteBuffer.wrap(shortType).order(sbo).getShort();
	}

	private static Integer getStataInt(byte[] intType, java.nio.ByteOrder sbo) {
		return ByteBuffer.wrap(intType).order(sbo).getInt();
	}

	private static Long getStataLong(byte[] longType, java.nio.ByteOrder sbo) {
		return ByteBuffer.wrap(longType).order(sbo).getLong();
	}

	private static Float getStataFloat(byte[] floatType, java.nio.ByteOrder sbo) {
		return ByteBuffer.wrap(floatType).order(sbo).getFloat();
	}

	private static Double getStataDouble(byte[] doubleType, java.nio.ByteOrder sbo) {
		return ByteBuffer.wrap(doubleType).order(sbo).getDouble();
	}

	public static String toStata(byte[] type, ByteOrder sbo, String sttype) {
		return getStataString(type, sbo);
	}

	public static Byte toStata(byte[] type, ByteOrder sbo, Byte sttype) {
		return getStataByte(type, sbo);
	}

	public static Short toStata(byte[] type, ByteOrder sbo, Short sttype) {
		return getStataShort(type, sbo);
	}

	public static Integer toStata(byte[] type, ByteOrder sbo, Integer sttype) {
		return getStataInt(type, sbo);
	}

	public static Long toStata(byte[] type, ByteOrder sbo, Long sttype) {
		return getStataLong(type, sbo);
	}

	public static Float toStata(byte[] type, ByteOrder sbo, Float sttype) {
		return getStataFloat(type, sbo);
	}

	public static Double toStata(byte[] type, ByteOrder sbo, Double sttype) {
		return getStataDouble(type, sbo);
	}



}
