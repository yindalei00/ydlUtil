package cn.sh.ideal.util;

import android.support.annotation.Nullable;



/**
 * @author yindalei
 */
public class StringUtil {


	/**
	 * 判断是否为空
	 * @param str	String
	 * @return		判断是否为空
	 */
	public static boolean isEmpty(@Nullable String str) {
		return str == null || str.length() == 0;
	}




}
