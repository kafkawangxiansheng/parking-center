package com.spm.common;

public class URLConstants {
	private static final String  MAIN_DOMAIN = "http://127.0.0.1:1235";
	public static final String URL_GET_ALL_IN_OUT = MAIN_DOMAIN + "/orders/?page=::page&cardCode=::cardCode&cardStt=::cardStt&carNumber=::carNumber&dateFrom=::dateFrom&dateTo=::dateTo";
	public static final String URL_EXPORT_ALL_IN_OUT = MAIN_DOMAIN + "/orders/export/?page=::page&cardCode=::cardCode&cardStt=::cardStt&carNumber=::carNumber&dateFrom=::dateFrom&dateTo=::dateTo";
	public static final String URL_GET_ALL_REVENUE = MAIN_DOMAIN + "/revenue/?projectId=::projectId&employeeId=::employeeId&dateFrom=::dateFrom&dateTo=::dateTo";
	public static final String URL_GET_ALL_CARD = MAIN_DOMAIN + "/cards/?page=::page&code=::code&stt=::stt&vehicleId=::vehicleId";
	
	public static final String URL_GET_ACCESS_TOKEN = MAIN_DOMAIN + "/oauth/token";
	public static final String URL_USER_BY_USERNAME = MAIN_DOMAIN + "/users/{username}/";
	public static final String URL_ATTRIBUTES_BY_USER_ID = MAIN_DOMAIN + "/users/get-attributes/{userId}/";
	public static final String URL_GET_PROJECT_IDS_BY_USER_ID = MAIN_DOMAIN + "/project/get-project-ids/{userId}/";
	
	public static final String URL_GET_ALL_PROJECT = MAIN_DOMAIN + "/project";
	public static final String URL_GET_PROJECT_BY_ID = MAIN_DOMAIN + "/project/getProject?id=::id";
	public static final String URL_POST_ADD_PROJECT = MAIN_DOMAIN + "/project/addProject";
	public static final String URL_DELETE_PROJECT = MAIN_DOMAIN + "/project/delete/::id";
	
	public static final String URL_ADD_CARD = MAIN_DOMAIN + "/cards/add";
	public static final String URL_GET_CARD_BY_ID = MAIN_DOMAIN + "/cards/getById/?cardId=::cardId";
	
	public static final String URL_GET_ALL_COMPANIES = MAIN_DOMAIN + "/company";
	public static final String URL_GET_LIST_COMPANIES = MAIN_DOMAIN + "/company/listAllCompanies";
	public static final String URL_GET_COMPANY_BY_ID = MAIN_DOMAIN + "/company/getCompany?id=::id";
	public static final String URL_POST_ADD_COMPANY = MAIN_DOMAIN + "/company/addCompany";
	public static final String URL_DELETE_COMPANY = MAIN_DOMAIN + "/company/delete/::id";
	
	public static final String URL_GET_ALL_EMPLOYEE = MAIN_DOMAIN + "/employee/?page=::page&name=::name&userName=::userName&pass=::pass&position=::position&sex=::sex";
	
}
