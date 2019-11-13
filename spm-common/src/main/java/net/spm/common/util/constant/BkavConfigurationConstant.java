package net.spm.common.util.constant;

/**
 * @author NhanNguyen
 */
public interface BkavConfigurationConstant {

	public static final String CIS_PARTNER_GUID = "f0808321-79c5-4208-aa58-6d18e718ac70";
	
	public static final String CIS_PARTNER_TOKEN = "y7eGAycvcxanCMEu4rZ1H5UrT7GgJNsmaQCZjgNEX6A=:Fz5frC2x/R1gRB+iy/iAhw==";
	
	public static final String CIS_PARTNER_TOKEN_KEY = "y7eGAycvcxanCMEu4rZ1H5UrT7GgJNsmaQCZjgNEX6A=";
	
	public static final String CIS_PARTNER_TOKEN_IVY = "Fz5frC2x/R1gRB+iy/iAhw==";
	
	public static final String CIS_BKAV_ALGORITHM = "AES/CBC/PKCS5Padding";
	public static final String INVOICE_ITEM_NAME = "Dịch vụ trông giữ ô tô tại điểm đỗ %s giờ thứ %s";
	public static final String INVOICE_DAILY_UNIT_NAME  = "Giờ";
	public static final String INVOICE_MONTHLY_UNIT_NAME = "Tháng";
	public static final String BKAV_DOWNLOAD_INVOICE_URL = "https://tracuu.ehoadon.vn";
	
	public static final double EXCHANGE_RATE_VND = 1;
	
	public static final String CURRENCY_ID_VND = "VND";
	public static final String CURRENCY_ID_USD = "USD";
	
	public static final String INVOICE_CURRENCY_VND = "VND";
	public static final String INVOICE_FORM = "01GTKT0/001";
	public static final String INVOICE_SERIAL = "AA/19E";
	public static final String INVOICE_NOTE = "";
	public static final int    INVOICE_NO = 0;
	public static final String INVOCIE_BILL_CODE = "";
	public static final int	   INVOICE_TYPE_ID = 1;
	
	public static final String INVOICE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	public static final String ORIGINAL_INVOICE_IDENTIFY = "";
	
	public static final int INVOICE_STATUS_SUCCESS = 0;
	public static final int INVOICE_STATUS_FAILED = 1;
	public static final int INVOICE_STATUS_RECREATED_FAILED = 2;
	public static final int INVOICE_STATUS_CANCELLED = 3;
	
	
	public static final String SYSTEM_STATUS_SUCCESS = "SUCCESS";
	public static final String SYSTEM_STATUS_PROCESSING = "PROCESSING";
	public static final String SYSTEM_STATUS_FAILED = "FAILED";
	public static final String SYSTEM_STATUS_RE_CREATE_FAILED = "RE_CREATE_FAILED";
	
}
