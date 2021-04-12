package com.livi.coding.challenge.demo.exeception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livi.coding.challenge.demo.payload.CreditPayload;
import com.livi.coding.challenge.demo.payload.GeneralApiResponse;


/**
 * The Class AuthExceptionEntryPoint.
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    
    /* (non-Javadoc)
     * @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @Override
 	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws ServletException {
 
    	GeneralApiResponse<CreditPayload> resp = new GeneralApiResponse<>();
		resp.setSuccess(false);
		resp.setCode("1");
		resp.setMessage(authException.getMessage());
    	
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getOutputStream(), resp);
		} catch (Exception e) {
			throw new ServletException();
		}
	}
	
}
