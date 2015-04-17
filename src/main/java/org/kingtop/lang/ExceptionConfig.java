package org.kingtop.lang;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.kingtop.sys.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class ExceptionConfig {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public static Map<String, Object> systemMap = new HashMap<String, Object>();

	public static Map<String, Object> applyMap = new HashMap<String, Object>();

	@SuppressWarnings("rawtypes")
	public ExceptionConfig() {
		try {
			this.logger.info("start loading Exception Config...");
			SAXReader saxReader = new SAXReader();
			InputStream sysExceptionIs = getClass().getClassLoader().getResourceAsStream(Config.systemExceptionFile);
			Document document = saxReader.read(sysExceptionIs);

			Iterator it = document.selectNodes("/errorcode/systems/system").iterator();
			while (it.hasNext()) {
				Element element = (Element) it.next();
				ErrorCode errorCode = new ErrorCode();
				errorCode.setCode(element.selectSingleNode("code").getText());
				errorCode.setUserMessage(element.selectSingleNode("userMessage").getText());
				errorCode.setErrorMessage(element.selectSingleNode("errorMessage").getText());
				systemMap.put(element.selectSingleNode("class").getText(), errorCode);
			}

			it = document.selectNodes("/errorcode/applys/apply").iterator();
			while (it.hasNext()) {
				Element element = (Element) it.next();
				ErrorCode errorCode = new ErrorCode();
				errorCode.setUserMessage(element.selectSingleNode("userMessage").getText());
				errorCode.setErrorMessage(element.selectSingleNode("errorMessage").getText());
				applyMap.put(element.selectSingleNode("code").getText(), errorCode);
			}
			this.logger.info("loading system exception done!");

			PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
			Resource[] resources = patternResolver.getResources(Config.appExceptionFile);
			this.logger.info("found " + resources.length + " appException files...");
			InputStream appExceptionIs = null;
			for (int i = 0; i < resources.length; i++) {
				appExceptionIs = resources[i].getInputStream();
				document = saxReader.read(appExceptionIs);
				it = document.selectNodes("/errorcode/applys/apply").iterator();
				while (it.hasNext()) {
					Element element = (Element) it.next();
					ErrorCode errorCode = new ErrorCode();
					errorCode.setUserMessage(element.selectSingleNode("userMessage").getText());
					errorCode.setErrorMessage(element.selectSingleNode("errorMessage").getText());
					applyMap.put(element.selectSingleNode("code").getText(), errorCode);
				}
			}
			this.logger.info("loading app exception done!");
		}
		catch (Exception e) {
			this.logger.error(e.getMessage());
		}
	}
}
