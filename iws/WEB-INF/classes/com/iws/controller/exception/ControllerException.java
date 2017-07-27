package com.iws.controller.exception;

public class ControllerException extends Exception {

	private static final long serialVersionUID = 1L;

	String controllerErrorCode = "";

	public ControllerException(String controllerErrorCode, String errorMessage) {
		// TODO Auto-generated constructor stub
		super(errorMessage);

		this.controllerErrorCode = controllerErrorCode;
	}
	
	public String getControllerErrorCode(){
		return controllerErrorCode;
	}

}
