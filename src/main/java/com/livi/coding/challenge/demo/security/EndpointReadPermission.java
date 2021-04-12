package com.livi.coding.challenge.demo.security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * The Interface EndpointReadPermission.
 */
@Retention(RetentionPolicy.RUNTIME)
//@PreAuthorize(" hasPermission(#request.requestURI, ENDPOINT,'"
//		+ EndpointAuthority.AUTHORITY_READ + EndpointAuthority.PERMISSION_DELIMITER
//		+ EndpointAuthority.AUTHORITY_SYSTEM_ADM + "') ")
@PreAuthorize("#oauth2.hasScope('"+EndpointAuthority.AUTHORITY_READ+"')")
public @interface EndpointReadPermission {

}
