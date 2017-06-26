package org.com.cim.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BaseDto implements Serializable {
	private static final long serialVersionUID = -3318018126952419039L;
	private List<JsonError> errors = new ArrayList<>();

	public List<JsonError> getErrors() {
		return errors;
	}

	public void setErrors(List<JsonError> errors) {
		this.errors = errors;
	}

}
