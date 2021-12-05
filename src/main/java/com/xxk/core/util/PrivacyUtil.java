package com.xxk.core.util;

import org.apache.commons.lang.StringUtils;

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

	public static String replaceNameS(String name){
		String newName = "";
		if (StringUtils.isEmpty(name)){
			return newName;
		}
		if (name.length() == 1){
			return name;
		}
		if (name.length() == 2){
			newName = name.replaceFirst(name.substring(0,1), "*");
		}
		if (name.length() > 2){
			StringBuffer sb = new StringBuffer();
			sb.append(name.substring(0,1));
			for (int i = 0;i < name.length() - 2;i++){
				sb.append("*");
			}
			sb.append(name.substring(name.length()-1));
			newName = sb.toString();
		}
		return newName;

	}

	// 手机号码中间四位数字脱敏
	public static String mobileEncrypt(String mobile) {
		return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}
	//身份证号脱敏
	public static String idEncrypt(String id) {
		return id.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
	}


}
