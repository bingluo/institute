package cn.edu.seu.institute.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.edu.seu.institute.view.velocity.ViewUtil;

public class InitSystemListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// ApplicationContext applicationContext = new
		// ClassPathXmlApplicationContext("/config.xml");
		ViewUtil.CONTEXT_PATH = event.getServletContext().getContextPath();
		// SystemContainer.setApplicationContext(applicationContext);
	}
}
