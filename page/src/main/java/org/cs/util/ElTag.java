package org.cs.util;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
public class ElTag {
	public static String urlDecode(String value){
		try {
			value= URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
}