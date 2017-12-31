package pmag.experiment.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DebugService {

Logger logger = LoggerFactory.getLogger(getClass());

	public String run(String message) {
		logger.debug(message);
		return message;
	}
}
