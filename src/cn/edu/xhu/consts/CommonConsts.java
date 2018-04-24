package cn.edu.xhu.consts;

import java.text.DateFormat;

public class CommonConsts {
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String ENCODED_FORMAT = "utf-8";
	public static final ThreadLocal<DateFormat> THREADLOCAL_DATE = new ThreadLocal<>();
}
