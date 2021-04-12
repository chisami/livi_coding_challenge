package com.livi.coding.challenge.demo.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.AntPathMatcher;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;


/**
 * The Class EndpointAuthority.
 */
public class EndpointAuthority implements GrantedAuthority {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	/** The endpoint url pattern. */
	private String endpointUrlPattern;

	/** The user authority, this means the user has read authority. */
	public static final String AUTHORITY_READ = "R";

	/** The user authority, this means the user has update authority. */
	public static final String AUTHORITY_UPDATE = "U";

	/** The user authority, this means the user has delete authority. */
	public static final String AUTHORITY_DELETE = "D";

	/** The user authority, this means the user has create authority. */
	public static final String AUTHORITY_NEW = "N";

	/** The Constant PERMISSION_DELIMITER. */
	public final static String PERMISSION_DELIMITER = ";";

	/**
	 * Instantiates a new endpoint authority.
	 *
	 * @param endpointUri the endpoint uri
	 */
	public EndpointAuthority(String endpointUri) {
		super();
		this.endpointUrlPattern = endpointUri;
	}

	/** The permissions set. */
	//
	private SortedSet<String> permissionsSet = new TreeSet<String>();

	/**
	 * Gets the permissions set.
	 *
	 * @return the permissions set
	 */
	public SortedSet<String> getPermissionsSet() {
		return permissionsSet;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof EndpointAuthority) {
			return endpointUrlPattern.equals(((EndpointAuthority) obj).getEndpointUrlPattern())
					&& permissionsSet.equals(((EndpointAuthority) obj).getPermissionsSet())

					;
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.endpointUrlPattern.hashCode();
	}

	/**
	 * Gets the endpoint url pattern.
	 *
	 * @return the endpoint url pattern
	 */
	public String getEndpointUrlPattern() {
		return endpointUrlPattern;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.endpointUrlPattern + permissionsSet;
	}

	/**
	 * Grant permission.
	 *
	 * @param newPermission the new permission
	 */
	public void grantPermission(String newPermission) {
		// check the permission
		permissionsSet.add(newPermission);

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
		return endpointUrlPattern + permissionsSet.toString();
	}

	/**
	 * Checks for permission on url.
	 *
	 * @param targetUrlEndpoint the target url endpoint
	 * @param requiredPermission the required permission
	 * @return true, if successful
	 */
	public boolean hasPermissionOnUrl(String targetUrlEndpoint, String requiredPermission) {
		boolean hasPermissionOnUrl = false;
		if (endpointUrlPattern != null && permissionsSet != null && requiredPermission != null) {
			AntPathMatcher antPathMatcher = new AntPathMatcher();
			HashSet<String> requiredPermissionsSet = new HashSet<String>();
			requiredPermissionsSet.addAll(Arrays.asList(((String) requiredPermission).split(PERMISSION_DELIMITER)));
			SetView<String> hasAnyRequiredPermission = Sets.intersection(permissionsSet, requiredPermissionsSet);
			hasPermissionOnUrl = !hasAnyRequiredPermission.isEmpty()
					&& antPathMatcher.match(this.endpointUrlPattern, targetUrlEndpoint);
		}

		return hasPermissionOnUrl;
	}

}