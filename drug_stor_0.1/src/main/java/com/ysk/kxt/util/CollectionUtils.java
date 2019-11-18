package com.ysk.kxt.util;

import java.util.Collection;

public class CollectionUtils {
	
	public static boolean isNotEmpty(Collection<?> col) {
		return (col != null && !col.isEmpty());
	}
}
