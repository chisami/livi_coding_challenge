package com.livi.coding.challenge.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


/** The Constant log. */
@Slf4j
@Component
public class LoggerInterceptor implements HandlerInterceptor {

	/** The Constant LOGIN_USER. */
	public static final String LOGIN_USER = "login_user";

	/**
	 * Executed before actual handler is executed.
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);

		log.info("[preHandle][" + request.getMethod() + "]" + request.getRequestURI());

		// TODO check if attribute 'user' exist, if no, redirect to login page
		// exclude credit service restful call in AppInterceptorConfig
		// String user = (String) request.getSession().getAttribute("user");
		// if (user == null) {
		// response.sendRedirect("/login");
		// }

		return true;
	}

	/**
	 * Executed before after handler is executed.
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @param modelAndView the model and view
	 * @throws Exception the exception
	 */
	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {

	}

	/**
	 * Executed after complete request is finished.
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @param ex the ex
	 * @throws Exception the exception
	 */
	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) throws Exception {

		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;

		StringBuffer result = new StringBuffer(100);
		result.append("[postHandle][" + request.getMethod() + "]" + request.getRequestURI() + " [executeTime : "
				+ executeTime + "ms]");

		if (ex != null) {
			result.append(" [exception: " + ex.getMessage() + "]");
		}
		log.info(result.toString());
	}

}
