package com.spm.common;

public class URLConstants {
	private static final String  MAIN_DOMAIN = "http://127.0.0.1:1235";
	public static final String URL_GET_ALL_IN_OUT = MAIN_DOMAIN + "/orders/?page=::page&employeeId=::employeeId&employeeIdOut=::employeeIdOut&cardChip=::cardChip&isMonthlyCard=::isMonthlyCard&projectId=::projectId&cardCode=::cardCode&cardStt=::cardStt&carNumber=::carNumber&dateFrom=::dateFrom&dateTo=::dateTo";
	public static final String URL_EXPORT_ALL_IN_OUT = MAIN_DOMAIN + "/orders/export/?page=::page&employeeId=::employeeId&employeeIdOut=::employeeIdOut&cardChip=::cardChip&isMonthlyCard=::isMonthlyCard&projectId=::projectId&cardCode=::cardCode&cardStt=::cardStt&carNumber=::carNumber&dateFrom=::dateFrom&dateTo=::dateTo";
	public static final String URL_GET_EXISTING_IN_PART = MAIN_DOMAIN + "/orders/existing-in-part/?page=::page&projectId=::projectId";
	public static final String URL_SAVE_LOST_CARD = MAIN_DOMAIN + "/orders/save-lost-card/?ids=::ids";
	public static final String URL_GET_ALL_REVENUE = MAIN_DOMAIN + "/revenue/?projectId=::projectId&employeeId=::employeeId&dateFrom=::dateFrom&dateTo=::dateTo";
	
	public static final String URL_GET_ACCESS_TOKEN = MAIN_DOMAIN + "/oauth/token";
	public static final String URL_USER_BY_USERNAME = MAIN_DOMAIN + "/users/{username}/";
	public static final String URL_ATTRIBUTES_BY_USER_ID = MAIN_DOMAIN + "/users/get-attributes/{userId}/";
	
	//project
	public static final String URL_GET_ALL_PROJECT = MAIN_DOMAIN + "/project";
	public static final String URL_GET_PROJECT_BY_ID = MAIN_DOMAIN + "/project/getProjectById?id=::id";
	public static final String URL_POST_ADD_PROJECT = MAIN_DOMAIN + "/project/addProject";
	public static final String URL_DELETE_PROJECT = MAIN_DOMAIN + "/project/delete/::id";
	public static final String URL_GET_PROJECT_IDS_BY_USER_ID = MAIN_DOMAIN + "/project/get-project-ids/{userId}/";
	
	//card
	public static final String URL_ADD_CARD = MAIN_DOMAIN + "/cards/add";
	public static final String URL_GET_CARD_BY_ID = MAIN_DOMAIN + "/cards/getById/?cardId=::cardId";
	public static final String URL_GET_LIST_BY_CODE_AND_VEHICLE_CARD_TYPE = MAIN_DOMAIN + "/card/getCodeAndVehicleCardType?code=::code&cardType=::cardType";
	public static final String URL_GET_ALL_CARD_BY_DISABLE = MAIN_DOMAIN + "/cards/disabledCard?code=::code";
	public static final String URL_ACTIVE_CARD = MAIN_DOMAIN + "/cards/activeCard/?cardId=::cardId";
	public static final String URL_GET_ALL_CARD = MAIN_DOMAIN + "/cards/?page=::page&code=::code&stt=::stt&vehicleId=::vehicleId&projectId=::projectId";
	public static final String URL_DELETE_CARD = MAIN_DOMAIN + "/cards/delete/::id";
	public static final String URL_ADD_UPDATE = MAIN_DOMAIN + "/cards/update";
	
	
	//company
	public static final String URL_GET_ALL_COMPANIES = MAIN_DOMAIN + "/company";
	public static final String URL_GET_LIST_COMPANIES = MAIN_DOMAIN + "/company/listAllCompanies";
	public static final String URL_GET_COMPANY_BY_ID = MAIN_DOMAIN + "/company/getCompanyById?id=::id";
	public static final String URL_POST_ADD_COMPANY = MAIN_DOMAIN + "/company/addCompany";
	public static final String URL_DELETE_COMPANY = MAIN_DOMAIN + "/company/delete/::id";
	
	//employee
	public static final String URL_GET_ALL_EMPLOYEE = MAIN_DOMAIN + "/employee/?page=::page&name=::name&userName=::userName&pass=::pass&position=::position&sex=::sex";
	public static final String URL_GET_LIST_EMPLOYEE = MAIN_DOMAIN + "/employee/listAllEmployee";
	public static final String URL_GET_LIST_EMPLOYEE_BY_PROJECT_ID = MAIN_DOMAIN + "/employee/getEmployeeByProjectId?projectId=::projectId";
	public static final String URL_GET_EMPLOYEE_BY_ID = MAIN_DOMAIN + "/employee/getEmployeeById?id=::id";
	public static final String URL_POST_ADD_EMPLOYEE = MAIN_DOMAIN + "/employee/addEmployee";
	public static final String URL_DELETE_EMPLOYEE = MAIN_DOMAIN + "/employee/delete/::id";
	public static final String URL_GET_ALL_BY_PROJECT_ID = MAIN_DOMAIN + "/employee/getAllByProjectId?projectId=::projectId";
	public static final String URL_POST_UPDATE_EMPLOYEE = MAIN_DOMAIN + "/employee/update";
	
	//vehicle
	public static final String URL_GET_LIST_ALL_VEHICLE = MAIN_DOMAIN + "/vehicle/getListAll";
	public static final String URL_GET_ALL_VEHICLE = MAIN_DOMAIN + "/vehicle?projectId=::projectId";
	public static final String URL_ADD_VEHICLE = MAIN_DOMAIN + "/vehicle/add";
	public static final String URL_GET_VEHICLE_BY_ID = MAIN_DOMAIN + "/vehicle/getById/?vehicleId=::vehicleId";
	public static final String URL_DELETE_VEHICLE = MAIN_DOMAIN + "/vehicle/delete/::id";
	public static final String URL_UPDATE_VEHICLE = MAIN_DOMAIN + "/vehicle/update";
	
	//monthly card
	public static final String URL_GET_ALL_MONTHLY_CARD = MAIN_DOMAIN + "/monthlyCard";
	public static final String URL_GET_ALL_MONTHLY_CARD_SEARCH_BY_PROJECT_ID = MAIN_DOMAIN + "/monthlyCard/search?page=::page&cardCode=::cardCode&statusDate=::statusDate&vehicleId=::vehicleId&numberEndDate=::numberEndDate&customerName=::customerName&projectId=::projectId";
	public static final String URL_GET_MONTHLY_CARD_RENEWAL_SEARCH = MAIN_DOMAIN + "/monthlyCard/renewal/search?page=::page&cardCode=::cardCode&statusDate=::statusDate&vehicleId=::vehicleId&numberEndDate=::numberEndDate&customerName=::customerName&projectId=::projectId";
	public static final String URL_GET_MONTHLY_CARD_BY_ID = MAIN_DOMAIN + "/monthlyCard/getMonthlyCardById?id=::id";
	public static final String URL_POST_ADD_MONTHLY_CARD = MAIN_DOMAIN + "/monthlyCard/add";
	public static final String URL_POST_RENEWAL_MONTHLY_CARD_UPDATE = MAIN_DOMAIN + "/monthlyCard/revewal/update";
	public static final String URL_DELETE_MONTHLY_CARD = MAIN_DOMAIN + "/monthlyCard/delete/::id/::username/";
	public static final String URL_POST_UPDATE_MONTHLY_CARD = MAIN_DOMAIN + "/monthlyCard/update";
	
	//monthly card logs
	public static final String URL_GET_ALL_MONTHLY_CARD_LOG = MAIN_DOMAIN + "/monthlyCardLog?page=::page&projectId=::projectId";
	
}
