package cz.burios.uniql.metadata.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;


public class NameFormatUtils {

	public NameFormatUtils() {}
	
	/**
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String buildSetMethodName(String fieldName) {
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	
	/**
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String buildGetMethodName(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * 
	 * @param prefix
	 * @param fieldName
	 * @return
	 */
	public static String buildMethodName(String prefix, String fieldName) {
		return prefix.toLowerCase() + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	// -----  -----------------------------------------------------------------
	
	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String toCamelCase(String text) {
		return StringUtils.remove(WordUtils.capitalizeFully(text, '_'), "_");
	}
	
	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String toCamelNameLC(String text) {
		return toLC(toCamelCase(text));
	}
	
	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String toLC(String text) {
		if (text == null || text.isEmpty()) {
            return text;
        }
        return Character.toLowerCase(text.charAt(0)) + text.substring(1); 
	}
}
