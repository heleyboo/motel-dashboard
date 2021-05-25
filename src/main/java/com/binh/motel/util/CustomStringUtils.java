package com.binh.motel.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomStringUtils {
	public static String BASE64_REGEX = "^(data:(image\\/[^;]+);base64,).*$";

	public static String findBase64ImageType(String input) {
		Pattern pattern = Pattern.compile(BASE64_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return matcher.group(2);
		}

		return "";
	}

	public static String findBase64ImageHeader(String base64) {
		Pattern pattern = Pattern.compile(BASE64_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(base64);
		if (matcher.find()) {
			return matcher.group(1);
		}

		return "";
	}
}
