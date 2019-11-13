package net.spm.common.util;


public class Constants {
	
	private static final String NAPAS_DOMAIN = "https://dps-staging.napas.com.vn";
	
	public static final String NAPAS_URL_DATAKEY = NAPAS_DOMAIN + "/api/rest/version/1/merchant/{merchantId}/datakey?check3DS=false";
	public static final String NAPAS_URL_TOKEN_INTERNATIONAL_PAY_NON_3DSECURE = NAPAS_DOMAIN + "/api/rest/version/1/merchant/{merchantId}/order/{orderId}/transaction/{transactionId}";
	public static final String NAPAS_URL_TOKEN_INTERNATIONAL_PAY_3DSECURE = NAPAS_DOMAIN + "/api/rest/version/1/merchant/{merchantId}/order/{orderId}/transaction/{transactionId}/3DSecureId/{3DSecureId}";
	public static final String NAPAS_URL_TOKEN_DOMESTIC_PAY_OTP = NAPAS_DOMAIN + "/api/rest/version/1/merchant/{merchantId}/order/{orderId}/transaction/{transactionId}";
	public static final String NAPAS_URL_TOKEN_DOMESTIC_VOID = NAPAS_DOMAIN + "/api/rest/version/1/merchant/{merchantId}/order/{orderId}/transaction/{transactionId}";
	public static final String NAPAS_URL_TOKEN_DOMESTIC_REFUND = NAPAS_DOMAIN + "/api/rest/version/1/merchant/{merchantId}/order/{orderId}/transaction/{transactionId}";
	public static final String NAPAS_URL_TOKEN_INTERNATIONAL_VOID = NAPAS_DOMAIN + "/api/rest/version/1/merchant/{merchantId}/order/{orderId}/transaction/{transactionId}";
	public static final String NAPAS_URL_TOKEN_INTERNATIONAL_REFUND = NAPAS_DOMAIN + "/api/rest/version/1/merchant/{merchantId}/order/{orderId}/transaction/{transactionId}";
	public static final String NAPAS_URL_RETRIEVE_ORDER_TRANSACTION_INTERNATIONAL = NAPAS_DOMAIN + "/api/rest/version/1/merchant/{merchantId}/order/{orderId}/international";
	public static final String NAPAS_URL_RETRIEVE_ORDER_TRANSACTION_DOMESTIC = NAPAS_DOMAIN + "/api/rest/version/1/merchant/{merchantId}/order/{orderId}/domestic";
	public static final String NAPAS_URL_TOKEN_DELETE = NAPAS_DOMAIN + "/api/rest/version/1/merchant/{merchantId}/token/{token}";
	public static final String NAPAS_URL_AUTHENTICATION = NAPAS_DOMAIN + "/api/oauth/token?grant_type=password&client_id={clientID}&client_secret={clientSerect}&username={username}&password={password}";
	
	public static final String NAPAS_CLIENT_ID = "IPARKING";
	public static final String NAPAS_CLIENT_SECRET = "851AF43D12ABD2353BDBDB5E21753D53";
	public static final String NAPAS_USERNAME = "IPARKING";
	public static final String NAPAS_PASSWORD = "I1P2A3R4K5I6N7G8";
	
			
	public static final String NAPAS_CLIENT_IP = "192.168.1.1";
	public static final String NAPAS_DEVICE_ID = "ID_12345678";
	public static final String NAPAS_ENVIRONMENT = "MobileApp";
	public static final String NAPAS_CREDITCARD_SCHEME = "CreditCard";
	public static final String NAPAS_ATMCARD_SCHEME = "AtmCard";
	public static final String NAPAS_ENABLE_3D_SECURE = "true";
	
	public static final String NAPAS_DOMESTIC_TOKEN_CHANNEL = "6014";
	public static final String NAPAS_CHANNEL = "4211";
	public static final String NAPAS_PAY_RETURN_TOKEN_CHANNEL = "6014";
	
	public static final String NAPAS_REFUND_DOMESTIC = "REFUND_DOMESTIC";
	public static final String NAPAS_REFUND = "REFUND";
	
	
	public static final String ERROR_STATUS = "ERROR";
	
	public static final String SUCCESS_STATUS = "SUCCESS";
	
	
	private static final String PAYMENT_CENTER_URL = "https://payment.iparkingstg.com/payment-apis";
	
	public static String NAPAS_HOSTED_FORM = "<form id='merchant-form' action='"+PAYMENT_CENTER_URL+"/napas/callback' method='POST'>" + 
			"<div id='napas-widget-container'></div> "
			+ "<script" + 
			"    type='text/javascript' " + 
			"    id='napas-widget-script' src='"+NAPAS_DOMAIN+"/api/restjs/resources/js/napas.hostedform.min.js' " + 
			"    merchantId='{merchantId}' " + 
			"    clientIP='{clientIP}' " + 
			"    deviceId='{deviceId}' " + 
			"    environment='{environment}' " + 
			"    cardScheme='{cardScheme}' " + 
			"    enable3DSecure='{enable3DSecure}' " + 
			"    apiOperation='{apiOperation}' " + 
			"    orderAmount='{orderAmount}' " + 
			"    orderCurrency='{orderCurrency}' " + 
			"    orderReference='{orderReference}' " + 
			"    orderId='{orderId}' " + 
			"    channel='{channel}' " + 
			"    sourceOfFundsType='{sourceOfFundsType}' " + 
			"dataKey='{dataKey}' " + 
			"napasKey='{napasKey}' >" +
			"</script>" + 
			"</form>";
	
	public static String NAPAS_DOMESTIC_TOKEN_HOSTED_FORM = "<form id='merchant-form' action='"+PAYMENT_CENTER_URL+"/napas/callback' method=\"POST\">" + 
			"<div id='napas-widget-container'></div> <script" + 
			"    type='text/javascript'" + 
			"    id='napas-widget-script'" + 
			"src='"+NAPAS_DOMAIN+"/api/restjs/resources/js/napas.hostedform.min.js'" + 
			"    merchantId='{merchantId}' " + 
			"    clientIP='{clientIP}' " + 
			"    deviceId='{deviceId}' " + 
			"    environment='{environment}' " + 
			"    cardScheme='{cardScheme}' " + 
			"    enable3DSecure='{enable3DSecure}' " + 
			"    apiOperation='{apiOperation}' " + 
			"    orderAmount='{orderAmount}' " + 
			"    orderCurrency='{orderCurrency}' " + 
			"    orderReference='{orderReference}' " + 
			"    orderId='{orderId}' " + 
			"    channel='{channel}' " + 
			"    sourceOfFundsType='{sourceOfFundsType}' " + 
			"dataKey='{dataKey}' " + 
			"napasKey='{napasKey}' >" + 
			"</script>" + 
			"</form>";
	
	public static String THANK_YOU_MESSAGE = "<h3> Giao dịch thành công!!!</h3>";
	
	public static String ADD_CARD_SUCCESS_MESSAGE = "<h3> Thêm thẻ thành công!!!</h3>";
	
	public static String CANCEL_TRANSACTION_MESSAGE = "<h3> Giao dịch đã được hủy bởi người dùng!!!</h3>";
	
	public static String TRANSACTION_FROM_INVALID_SOURCE = "<h3> Giao dịch không thành công do không rõ nguồn gốc!!!</h3>";
	
	public static String TRANSACTION_PENDING = "<h3> Giao dịch đang được xử lý!!!</h3>";
	
	public static String TRANSACTION_FAILURE = "<h3> Giao dịch không thành công!!!</h3>";
	
//	--------------------------------VIETTEL_PAY-----------------------------------
	
	
	// thong tin command
	public static final String VIETTEL_PAY_COMMAND_AUTO_PAYMENT_API = "AUTO_PAYMENT_API";
	public static final String VIETTEL_PAY_COMMAND_PAYMENT = "PAYMENT";
	public static final String VIETTEL_PAY_COMMAND_TRANS_INQUIRY = "TRANS_INQUIRY";
	public static final String VIETTEL_PAY_COMMAND_TRANS_INQUIRY_AUTO_PAYMENT = "TRANS_INQUIRY_AUTOPAYMENT";
	// thong tin phien ban
	public static final String VIETTEL_PAY_VERSION = "2.0";
	public static final String VIETTEL_PAY_LOCALE = "Vi";

	

	// kieu lien ket
	public static final int VIETTEL_PAY_TYPE_REGISTER = 0;
	public static final int VIETTEL_PAY_TYPE_CHANGE_LIMIT = 1;
	public static final int VIETTEL_PAY_TYPE_UNREGISTER = 2;
	// mo ta loi
	public static final String VIETTEL_PAY_MESSAGE_USERINFO_NOT_EXITS = "Thông tin User không tồn tại";
	public static final String VIETTEL_PAY_MESSAGE_LIMIT_FAILE = "Hạn mức không phù hợp";
	public static final String VIETTEL_PAY_MESSAGE_USER_EXITS_TOKEN = "User đã liên kết tài khoản Viettel Pay";
	public static final String VIETTEL_PAY_MESSAGE_USER_NOT_EXITS_TOKEN = "User chưa liên kết tài khoản Viettel Pay";
	
	public static final String EMAIL_VIETTEL_PAY_FAILURE_TITLE = "[ViettelPay] Cảnh báo hệ thống";
	public static final String EMAIL_VIETTEL_PAY_CONNECT_FAILURE_TITLE = "[ViettelPay] Cảnh báo hệ thống kết nối tới ViettelPay";
	
}
