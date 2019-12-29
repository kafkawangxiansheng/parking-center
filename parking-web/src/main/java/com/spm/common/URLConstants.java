package com.spm.common;

public class URLConstants {
	private static final String  MAIN_DOMAIN = "http://127.0.0.1:1235";
	public static final String URL_GET_ALL_IN_OUT = MAIN_DOMAIN + "/orders/?page=::page&cardCode=::cardCode&cardStt=::cardStt&carNumber=::carNumber&dateFrom=::dateFrom&dateTo=::dateTo";
	public static final String URL_GET_ALL_REVENUE = MAIN_DOMAIN + "/revenue/?projectId=:projectId";
	
	public static final String URL_GET_ACCESS_TOKEN = MAIN_DOMAIN + "/oauth/token";
	public static final String URL_USER_BY_USERNAME = MAIN_DOMAIN + "/users/{username}/";
	public static final String URL_ROLES_BY_USER_ID = MAIN_DOMAIN + "/roles/get-roles/{userId}/";
	
}
