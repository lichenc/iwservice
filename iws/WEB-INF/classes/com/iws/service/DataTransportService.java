package com.iws.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace="http://transport.service.server.webservice.itsm.dhcc.com/")
public interface DataTransportService {
	public String createRequestForJson(@WebParam(name="arg0")String input);
}
