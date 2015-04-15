package org.kingtop.sys;

import org.kingtop.lang.ExceptionConfig;

public class Startup {
	public static void init() {
		try {
			new Config();
			new ExceptionConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
