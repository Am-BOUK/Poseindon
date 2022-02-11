package com.nnk.springboot.services;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * implementation of GetUserInfo service
 * 
 * The purposes of this service is to Receive the authentication request
 */
@Service
public class GetUserInfoService implements IGetUserInfoService {

	/**
	 * getUserInfo ** this method allows to return the information of the logged-in
	 * user
	 * 
	 * @param user : Principal contains user information
	 * @return userInfo
	 */
	@Override
	public String getUserInfo(Principal user) {
		StringBuffer userInfo = new StringBuffer();
		if (user instanceof UsernamePasswordAuthenticationToken) {
			userInfo.append(getUsernamePasswordLoginInfo(user));
		} else if (user instanceof OAuth2AuthenticationToken) {
			userInfo.append(getOauth2LoginInfo(user));
		}
		return userInfo.toString();
	}

	/**
	 * The getOauth2LoginInfo method takes the 'principal' from token authentication
	 * as an argument ** this method allows to return the user's login if he is
	 * logged in by Github or the user's name if he is logged in by Google
	 * 
	 * @param user : Principal contains user information
	 * @return protectedInfo : the protected Info contains the user's name or login
	 */
	public StringBuffer getOauth2LoginInfo(Principal user) {
		StringBuffer protectedInfo = new StringBuffer();
		OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) user;
		OAuth2User principal = ((OAuth2AuthenticationToken) user).getPrincipal();
		OidcIdToken idToken = getIdToken(principal);
		if (authToken.isAuthenticated() && idToken == null) {
			Map<String, Object> userDetails = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();
			protectedInfo.append("Github login : " + userDetails.get("login"));
			return protectedInfo;
		}

		if (authToken.isAuthenticated() && idToken != null) {
			Map<String, Object> userDetails = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();
			protectedInfo.append("Google name : " + userDetails.get("name"));
			return protectedInfo;

		} else {
			protectedInfo.append("NA");
		}
		return protectedInfo;

	}

	/**
	 * The getUsernamePasswordLoginInfo method takes the 'principal' from session
	 * authentication as an argument
	 * 
	 * @param user : Principal contains user information
	 * @return usernameInfo : the user name info contains the username of user
	 *         authenticated
	 */
	public StringBuffer getUsernamePasswordLoginInfo(Principal user) {
		StringBuffer usernameInfo = new StringBuffer();
		UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);

		if (token.isAuthenticated()) {
			User u = (User) token.getPrincipal();
			usernameInfo.append("Username : " + u.getUsername());
		} else {
			usernameInfo.append("NA");
		}
		return usernameInfo;
	}

	/**
	 * getIdToken ** this method allows to get the id token of the user
	 * 
	 * @param principal : A representation of a user that is registered with an
	 *                  OAuth 2.0 Provider.
	 * @return IdToken : containing claims about the user
	 */
	public OidcIdToken getIdToken(OAuth2User principal) {
		if (principal instanceof DefaultOidcUser) {
			DefaultOidcUser oidcUser = (DefaultOidcUser) principal;
			return oidcUser.getIdToken();
		}
		return null;
	}
}
