package fi.nooks.painonhallinta.dao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND)

public class WeightNotFoundException extends RuntimeException {

	public WeightNotFoundException() {
		super();
	}

	public WeightNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WeightNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public WeightNotFoundException(String message) {
		super(message);
	}

	public WeightNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
