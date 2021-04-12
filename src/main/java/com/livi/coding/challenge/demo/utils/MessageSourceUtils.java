package com.livi.coding.challenge.demo.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


/**
 * The Class MessageSourceUtils.
 */
@Component
public class MessageSourceUtils {

	/** The message source. */
	private static MessageSource messageSource;

	/**
	 * Inits the.
	 *
	 * @param messageSource the message source
	 */
	@Autowired
	public void init(MessageSource messageSource) {
		MessageSourceUtils.messageSource = messageSource;
	}

	/**
	 * Gets the message.
	 *
	 * @param code the code
	 * @param args the args
	 * @param defaultMessage the default message
	 * @param locale the locale
	 * @return the message
	 */
	@Nullable
	public static String getMessage(String code, @Nullable Object[] args, @Nullable String defaultMessage,
			Locale locale) {
		return messageSource.getMessage(code, args, defaultMessage, locale);
	}

	/**
	 * Gets the message.
	 *
	 * @param code the code
	 * @param args the args
	 * @param locale the locale
	 * @return the message
	 */
	public static String getMessage(String code, @Nullable Object[] args, Locale locale) {
		return messageSource.getMessage(code, args, locale);
	}
	
	/**
	 * Gets the message.
	 *
	 * @param code the code
	 * @param args the args
	 * @return the message
	 */
	public static String getMessage(String code, @Nullable Object[] args) {
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}

}
