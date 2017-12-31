package pmag.experiment.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import pmag.experiment.services.FileContentService;
import pmag.experiment.services.FileContentServiceImpl;

/**
 * Spring configuration
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pmag.experiment")
public class ApplicationConfiguration {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Bean
	/* multipart resolver for MVC from Commons FileUpload*/
	public CommonsMultipartResolver multipartResolver() {
		logger.debug("multipartResolver instantiation");
		return new CommonsMultipartResolver();
	}
	@Bean
	public FileContentService fileContentService() {
		return new FileContentServiceImpl();
	}
	
}
