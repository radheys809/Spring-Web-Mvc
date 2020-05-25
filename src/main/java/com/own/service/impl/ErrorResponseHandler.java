package com.own.service.impl;

import com.own.dto.Response;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

@Component
@Lazy
public class ErrorResponseHandler {
	public static final String RES_ERR_MESSAGE="statusMessage";
	public static final String RES_ERR_CODE="statusCode";
	public Response deleteUserResponse(String statusCode) {
		Response response=new Response();
		if ("0".equals(statusCode)) {
			Map<String, Object> successData=new HashMap<>();
			successData.put(RES_ERR_CODE, "0");
			successData.put(RES_ERR_MESSAGE, "User deleted Success");
			response.setData(successData);
			response.setIsSuccess(Boolean.TRUE);
		} else {
			response.setIsSuccess(Boolean.FALSE);
			response.setErrorCode(001);
			response.setErrorMesaage("user Not deleted");
				}
		return response;
	}

	public Response reportNotGenerated(Integer code,String reason) {
		Response response = new Response();
		response.setIsSuccess(Boolean.FALSE);
		response.setErrorCode(code);
		response.setErrorMesaage(reason);
		return response;
	}

	public Response genericError() {
		Response response = new Response();
		response.setIsSuccess(Boolean.FALSE);
		response.setErrorCode(1000);
		response.setErrorMesaage("There is Some issue ! please Try later");
		return response;
	}

	public Response reportGenerated(ByteArrayInputStream report) {
		Response response = new Response();
		response.setData(new InputStreamResource(report));
		response.setIsSuccess(Boolean.TRUE);
		return response;
	}

}
