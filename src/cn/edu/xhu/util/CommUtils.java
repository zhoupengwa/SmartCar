package cn.edu.xhu.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

import cn.edu.xhu.consts.CommonConsts;
import cn.edu.xhu.exception.MapToBeanConvertException;

public class CommUtils {
	/**
	 * ��mapת��Ϊbean
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 * @throws MapToBeanConvertException
	 * @throws Exception
	 */
	public static <K, V, T> T convertMapToBean(Map<K, V> map, Class<T> clazz) throws MapToBeanConvertException {
		T object;
		try {
			object = clazz.newInstance();
			for (Entry<K, V> entry : map.entrySet()) {
				K key = entry.getKey();
				V Value = entry.getValue();
				BeanUtils.setProperty(object, String.valueOf(key), Value);
			}
		} catch (Exception e) {
			throw new MapToBeanConvertException();
		}

		return object;
	}

	/**
	 * �õ������ַ���
	 * 
	 * @return
	 */
	public static String formatDate() {
		DateFormat df = CommonConsts.THREADLOCAL_DATE.get();
		if (df == null) {
			df = new SimpleDateFormat(CommonConsts.DATE_FORMAT);
		}
		return df.format(new Date());
	}

	/**
	 * �õ�����
	 * 
	 * @param parseStr
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String parseStr) throws ParseException {
		DateFormat df = CommonConsts.THREADLOCAL_DATE.get();
		if (df == null) {
			df = new SimpleDateFormat(CommonConsts.DATE_FORMAT);
		}
		return df.parse(parseStr);
	}

}
