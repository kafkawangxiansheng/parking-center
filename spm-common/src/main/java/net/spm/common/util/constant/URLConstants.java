package net.spm.common.util.constant;

/**
 * @author Vincent
 */
public class URLConstants {
	private static final String MAIN_DOMAIN = "http://payment.iparkingstg.com:8080";
	// private static final String MAIN_DOMAIN = "https://payment.iparking.vn";
	private static final String IPARKING_SERVICE_PATH = "/iparking-apis";

	private static final String VIETTEL_PAY_SERVICE_PATH = "/viettelpay-apis";

	// private static final String API_IPARKING_SERVICE_DOMAIN =
	// "https://api.iparking.vn";
	// private static final String ADMIN_API_IPARKING_SERVICE_DOMAIN =
	// "https://admapi.iparking.vn";
	// private static final String NAPAS_API_IPARKING_SERVICE_DOMAIN =
	// "https://napas.iparking.vn";

	private static final String API_IPARKING_SERVICE_DOMAIN = "https://api.live.iparkingstg.com";
	private static final String ADMIN_API_IPARKING_SERVICE_DOMAIN = "https://admapi.live.iparkingstg.com";
	private static final String NAPAS_API_IPARKING_SERVICE_DOMAIN = "https://napas.live.iparkingstg.com";
	private static final String NEW_PORTAL_DOMAIN = "https://newportal.live.iparkingstg.com/#";

	public static final String TICKET_GET_BY_ID = MAIN_DOMAIN + IPARKING_SERVICE_PATH + "/ticket/{id}/find-by-id/";
	public static final String TICKET_GET_ALL = MAIN_DOMAIN + IPARKING_SERVICE_PATH + "/ticket/";
	public static final String TICKET_CREATED = MAIN_DOMAIN + IPARKING_SERVICE_PATH + "/ticket/create/";
	public static final String TICKET_UPDATED = MAIN_DOMAIN + IPARKING_SERVICE_PATH + "/ticket/update/";

	public static final String PARKING_CONTRACT_GET_BY_ID = MAIN_DOMAIN + IPARKING_SERVICE_PATH
			+ "/parking-contract/{id}/get-by-id/";
	public static final String PARKING_CONTRACT_UPDATE = MAIN_DOMAIN + IPARKING_SERVICE_PATH
			+ "/parking-contract/update/";

	public static final String PARKING_ACTOR_GET = MAIN_DOMAIN + IPARKING_SERVICE_PATH
			+ "/parking-actor/{id}/find-by-actor/";

	public static final String TICKET_TRANS_CREATED = MAIN_DOMAIN + IPARKING_SERVICE_PATH
			+ "/ticket-transaction/create/";
	public static final String TICKET_TRANS_GET_BY_PAYMENT_ORDER_NO = MAIN_DOMAIN + IPARKING_SERVICE_PATH
			+ "/ticket-transaction/{paymentOrderNo}/find-payment-order-no/";
	public static final String TICKET_TRANS_GET_BY_ID = MAIN_DOMAIN + IPARKING_SERVICE_PATH
			+ "/ticket-transaction/{id}/find-by-id/";

	public static final String TICKET_GET_PRICE = API_IPARKING_SERVICE_DOMAIN
			+ "/price/get/ticket_trans?ticket_id={ticketId}&min={min}";
	public static final String SUPP_CAL_TICKET_PRICE = API_IPARKING_SERVICE_DOMAIN
			+ "/price/cal_daily_for_supervisor?cpp_id={cppId}&number_plate={numberPlate}&car_type_id={carTypeId}";

	public static final String TICKET_PAY_BY_TOKEN_VIETTEL_PAY = MAIN_DOMAIN + VIETTEL_PAY_SERVICE_PATH
			+ "/token-payment/PaymentWithToken";

	public static final String TICKET_PAY_BY_WEBPAY_VIETTEL_PAY = MAIN_DOMAIN + VIETTEL_PAY_SERVICE_PATH
			+ "/web-payment/getUrlConnectCTTViettelPay";

	public static final String DELEGATE_RESEND_EMAIL_ACTIVATION = API_IPARKING_SERVICE_DOMAIN
			+ "/p/delegate_resend_email_activation?cus_id={cus_id}&cus_phone={cus_phone}";

	public static final String URL_SEND_OTP = ADMIN_API_IPARKING_SERVICE_DOMAIN + "/p/delegate_send_sms";

	public static final String URL_CREATE_CUSTOMER = API_IPARKING_SERVICE_DOMAIN + "/p/create/customer";

	public static final String URL_UPDATE_CUSTOMER = API_IPARKING_SERVICE_DOMAIN + "/p/update/customer";

	public static final String URL_CREATE_UPDATE_CUSTOMER_INFO = API_IPARKING_SERVICE_DOMAIN + "/p/updateinfo/customer";

	public static final String URL_DELETE_CUSTOMER_CAR = API_IPARKING_SERVICE_DOMAIN + "/p/del/car";

	public static final String URL_CREATE_CUSTOMER_CAR = API_IPARKING_SERVICE_DOMAIN + "/p/add/car";

	public static final String URL_IPARKING_NAPAS_CALLBACK = NAPAS_API_IPARKING_SERVICE_DOMAIN + "/handle_ecom";
	public static final String URL_IPARKING_NAPAS_CALLBACK_ADD_CARD = API_IPARKING_SERVICE_DOMAIN
			+ "/p/payment/ipn/sync";

	public static final String URL_OTP_SIGNUP = API_IPARKING_SERVICE_DOMAIN + "/p/otp_signup";
	public static final String URL_VERIFY_OTP_SIGNUP = API_IPARKING_SERVICE_DOMAIN + "/p/verify/signup/iparking";
	public static final String URL_NAP_SIGNUP = API_IPARKING_SERVICE_DOMAIN + "/p/nap_signup/iparking";

	public static final String JSON_CONTENT_TYPE = "application/json";
	public static final String FORM_DATE_CONTENT_TYPE = "application/x-www-form-urlencoded";

	public static final String URL_CREATE_CUSTOMER_BALACNE = API_IPARKING_SERVICE_DOMAIN + "/p/create/balance";

	public static final String URL_CREATE_CAPCHA = API_IPARKING_SERVICE_DOMAIN + "/p/captcha?s={capchaId}";

	public static final String URL_UPDATE_USER = API_IPARKING_SERVICE_DOMAIN + "/p/update/account";
	public static final String URL_CREATE_USER = API_IPARKING_SERVICE_DOMAIN + "/p/create/account";
	
	
	public static final String URL_RESEND_PASSWORD = NEW_PORTAL_DOMAIN + "/change-password/{id}/{checksum}/{expire}";

}
