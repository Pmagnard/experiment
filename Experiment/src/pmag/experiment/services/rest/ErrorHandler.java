package pmag.experiment.services.rest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pmag.experiment.services.ServiceException;

@RestControllerAdvice("pmag.experiment.services.rest")
public class ErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ServiceException.class)
	public pmag.experiment.beans.Error handleServiceException(ServiceException e) {
		pmag.experiment.beans.Error error = null;
		if (e != null) {
			error = new pmag.experiment.beans.Error();
			error.setMessage(e.getMessage());
			error.setException(e);
		}
		return error;
	}

}
