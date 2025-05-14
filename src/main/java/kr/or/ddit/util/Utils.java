package kr.or.ddit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
	// 문자열을 숫자로 변경해준다.
	// 문자열에 오류가 있을 경우 default 값을 반환한다.
	public static int parseInt(String str, int defaultValue) {
		int returnValue = defaultValue;
		try {
			if (str == null) {
				return returnValue;
			}

			returnValue = Integer.parseInt(str);
		} catch (Exception e) {
			return returnValue;
		}

		return returnValue;
	}

	// 문자열이 null이거나 ""인 경우 true를 리턴한다.
	public static boolean isNullOrEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
}
