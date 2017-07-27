package com.iws.pojo.ws;

public class ResultPojo<T> {
	protected String r_code;
	protected T r_result;

	public String getR_code() {
		return r_code;
	}

	public void setR_code(String r_code) {
		this.r_code = r_code;
	}

	public T getR_result() {
		return r_result;
	}

	public void setR_result(T r_result) {
		this.r_result = r_result;
	}

}
