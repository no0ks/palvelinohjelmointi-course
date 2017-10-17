package fi.nooks.tuntikirjanpito.dao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND)

public class WorkHoursException extends RuntimeException {

	public WorkHoursException() {
		super();
	}

	public WorkHoursException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WorkHoursException(String message, Throwable cause) {
		super(message, cause);
	}

	public WorkHoursException(String message) {
		super(message);
	}

	public WorkHoursException(Throwable cause) {
		super(cause);
	}
	
}
