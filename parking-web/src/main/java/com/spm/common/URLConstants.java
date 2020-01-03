package com.spm.common;

public class URLConstants {
	private static final String  MAIN_DOMAIN = "http://127.0.0.1:1235";
	public static final String URL_GET_ALL_IN_OUT = MAIN_DOMAIN + "/orders/?page=::page&cardCode=::cardCode&cardStt=::cardStt&carNumber=::carNumber&dateFrom=::dateFrom&dateTo=::dateTo";
	public static final String URL_EXPORT_ALL_IN_OUT = MAIN_DOMAIN + "/orders/export/?page=::page&cardCode=::cardCode&cardStt=::cardStt&carNumber=::carNumber&dateFrom=::dateFrom&dateTo=::dateTo";
	public static final String URL_GET_ALL_REVENUE = MAIN_DOMAIN + "/revenue/?projectId=::projectId&employeeId=::employeeId&dateFrom=::dateFrom&dateTo=::dateTo";
	public static final String URL_GET_ALL_CARD = MAIN_DOMAIN + "/cards/?page=::page&code=::code&stt=::stt&vehicleId=::vehicleId";
	
	public static final String URL_GET_ACCESS_TOKEN = MAIN_DOMAIN + "/oauth/token";
	public static final String URL_USER_BY_USERNAME = MAIN_DOMAIN + "/users/{username}/";
	public static final String URL_ROLES_BY_USER_ID = MAIN_DOMAIN + "/roles/get-roles/{userId}/";
	
	public static final String URL_GET_ALL_PROJECT = MAIN_DOMAIN + "/project";
	public static final String URL_GET_PROJECT_BY_ID = MAIN_DOMAIN + "/project/getProject?id=::id";
	public static final String URL_POST_ADD_PROJECT = MAIN_DOMAIN + "/project/addProject";
	public static final String URL_DELETE_PROJECT = MAIN_DOMAIN + "/project/delete/?id=::id";
	
	public static final String URL_ADD_CARD = MAIN_DOMAIN + "/cards/add";
	public static final String URL_GET_CARD_BY_ID = MAIN_DOMAIN + "/cards/cardId?cardId=::cardId";
	
	public static final String URL_GET_ALL_COMPANIES = MAIN_DOMAIN + "/company";
	public static final String URL_GET_LIST_COMPANIES = MAIN_DOMAIN + "/company/listAllCompanies";
	public static final String URL_GET_COMPANY_BY_ID = MAIN_DOMAIN + "/company/getProject?id=::id";
	public static final String URL_POST_ADD_COMPANY = MAIN_DOMAIN + "/company/addProject";
	public static final String URL_DELETE_COMPANY = MAIN_DOMAIN + "/company/delete/?id=::id";
	
}
