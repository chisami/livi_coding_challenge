package com.livi.coding.challenge.demo.aspect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;


/**
 * The Class FunctionAccessLogAspect.
 */
@Aspect
@Component

/** The Constant log. */
@Slf4j
public class FunctionAccessLogAspect {

	/** The gson. */
	private Gson gson = new Gson();

	/**
	 * Log around.
	 *
	 * @param pjp the pjp
	 * @return the object
	 * @throws Throwable the throwable
	 */
	@Around("within(com.livi.coding.challenge.*.controller..*)")
	public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
		Object result = null;

		if (log.isInfoEnabled()) {
			Signature sig = pjp.getSignature();
			Object[] args = pjp.getArgs();

			List<Object> argsList = new ArrayList<>();
			for (Object obj : args) {
				if ((obj instanceof Serializable)) {
					argsList.add(obj);
				}
			}

			String msg = "START " + sig.getDeclaringTypeName() + "." + sig.getName() + " param="
					+ gson.toJson(argsList);

			log.info(msg);

			result = pjp.proceed();

			msg = "END " + sig.getDeclaringTypeName() + "." + sig.getName();

			if (result instanceof Serializable) {
				msg += " return=" + gson.toJson(result);
			}

			log.info(msg);

		} else {
			result = pjp.proceed();
		}

		return result;
	}

}
