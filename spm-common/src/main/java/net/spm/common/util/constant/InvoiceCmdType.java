package net.spm.common.util.constant;

/**
 * @author NhanNguyen
 */
public interface InvoiceCmdType {

	public static final int CREATE_INVOICE = 100;
	
	public static final int CREATE_INVOICE_WITH_FORM = 110;
	
	public static final int GET_INVOICE_DETAIL_INFOR = 800;
	
	public static final int GET_INVOICE_STATUS = 801;
	
	public static final int GET_INVOICE_HISTORY = 802;
	
	public static final int GET_COMPANY_INFORMATION_BY_TAX_CODE = 904;
	
	public static final int CANCEL_INVOICE_BY_INVOICE_GUID = 201;
	
	public static final int REPLACE_INVOICE = 120;
	
	public static final int EDIT_INVOICE = 121;
	
}
