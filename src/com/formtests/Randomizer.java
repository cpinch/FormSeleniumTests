package com.formtests;

import org.apache.commons.lang3.RandomStringUtils;

public class Randomizer
{
	public static <T> T getRandomElementFromArray(T[] arr)
	{
		return arr[(int) Math.floor(Math.random() * arr.length)];
	}

	public static String getRandomASCIITextOfLength(int len)
	{
		return RandomStringUtils.randomAscii(len);
	}
}
