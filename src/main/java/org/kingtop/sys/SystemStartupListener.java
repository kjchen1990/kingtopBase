package org.kingtop.sys;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SystemStartupListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent event) {
		Startup.init();
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}
