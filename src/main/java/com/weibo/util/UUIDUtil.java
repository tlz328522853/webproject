package com.weibo.util;

import java.util.UUID;
/**
 * 
 * @author czz
 *
 */
public class UUIDUtil {
	/**
     */
	public static String getUUID() {

		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		return uuid;
	}

	/**
	 * @return 
	 */
	public static long getUUNum() {

		long result = Math.abs(UUID.randomUUID().getMostSignificantBits()) % 9007199254740992l;
		return result == 0L? 1L : result;
	}
}
