
package org.com.cim.common.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response<T> implements Serializable {
	private static final long serialVersionUID = -1815387657937789885l;
	private List<JsonError> errors = new ArrayList<JsonError>();
	private Object otherInformation;
	private T data;
	private String requestId;

	public Response() {
	}

	public Response(String requestId) {
		this.requestId = requestId;
	}

	public List<JsonError> getErrors() {
		return errors;
	}

	public void setErrors(final List<JsonError> errors) {
		this.errors = errors;
	}

	public Object getOtherInformation() {
		return otherInformation;
	}

	public void setOtherInformation(Object otherInformation) {
		this.otherInformation = otherInformation;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

}
