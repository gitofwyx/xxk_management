package com.xxk.core.util;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrivacyUtil {
	
	/**
	 * Map按值降序排序
	 * @param
	 * @return
	 */
	public static  String replaceNameX(String str) {

		String reg = ".{1}";
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		int i = 0;
		while(m.find()){
			i++;
			if(i!=2)
				continue;
			m.appendReplacement(sb, "*");
		}
		m.appendTail(sb);
		return sb.toString();

	}

}
