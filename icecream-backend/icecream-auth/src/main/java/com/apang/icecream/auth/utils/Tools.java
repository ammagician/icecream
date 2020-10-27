package com.apang.icecream.auth.utils;

import java.util.UUID;

/**
 * 总的工具类
 * 
 * @author Administrator
 * 
 */
public final class Tools {
	/** 图片工具类 */
	public final static ImageUtil image = new ImageUtil();


	/**
	 * 生成新的UUID(32位)
	 * 
	 * @return
	 */
	public static String uuid() {
		String rs = UUID.randomUUID().toString();
		return rs.replace("-", "");
	}

}
