package org.com.cim.service.dto;

public class JsonError {
	private String field;
	private String error;

	public JsonError() {
	}

	public JsonError(String field, String error) {
		super();
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public void setField(final String field) {
		this.field = field;
	}

	public String getError() {
		return error;
	}

	public void setError(final String error) {
		this.error = error;
	}

}
