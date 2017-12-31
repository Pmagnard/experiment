package pmag.experiment.spring;

import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {
	/* SL4J logger */
	Logger logger = LoggerFactory.getLogger(getClass());

	private static ApplicationContext appContext;

	public static ApplicationContext getContext() {
		return appContext;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Spring context initialization
		logger.debug("Spring web application context is initializing...");
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationConfiguration.class);
		rootContext.setServletContext(servletContext);
		rootContext.refresh();
		appContext = rootContext;
		
		// MVC dispatcher servlet registration
		Dynamic dynamic = servletContext.addServlet("Spring MVC Dispatcher", new DispatcherServlet(rootContext));
		dynamic.addMapping("/api/*");
		dynamic.setLoadOnStartup(1);
		logger.debug("Spring web application context has been initialized.");

	}

}
