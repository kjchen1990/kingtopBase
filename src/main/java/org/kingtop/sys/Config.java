package org.kingtop.sys;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	protected static final Logger logger = LoggerFactory.getLogger(Config.class);
	public static String systemExceptionFile;
	public static String appExceptionFile;
	public static String uploadFilesPath = null;
	//public static String upgradeFilesPath = null;

	public static String systemName = "";
	public static String webPath = "";

	static {
		try {
			logger.info("Start loading config ...");
			SAXReader saxReader = new SAXReader();
			InputStream is = Config.class.getClassLoader().getResourceAsStream("resources/systemConfig.xml");
			Document document = saxReader.read(is);

			systemExceptionFile = document.selectSingleNode("/config/startup/exception/systemException").getText();
			appExceptionFile = document.selectSingleNode("/config/startup/exception/appException").getText();

			systemName = document.selectSingleNode("/config/system/systemName").getText();
			webPath = document.selectSingleNode("/config/system/webPath").getText();
			uploadFilesPath = document.selectSingleNode("/config/system/uploadFilesPath").getText();

			logger.info("loading config done...");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}
