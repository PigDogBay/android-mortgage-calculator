package com.mpdbailey.utils;

import java.util.ArrayList;
import java.util.List;

public final class StringUtils
{
	public static List<String> GetSubWords(String word)
	{
		char[] letters = word.toCharArray();
		int len = letters.length;
		ArrayList<String> subwords = new ArrayList<String>();
		if (len<2){return subwords;}
		
		for (int i=0; i<len;i++)
		{
			subwords.add(String.copyValueOf(letters, 0, len-1));
			char swapTmp = letters[i];
			letters[i] = letters[len-1];
			letters[len-1] = swapTmp;
		}
		return subwords;
		
	}
}
