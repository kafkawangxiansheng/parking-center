package net.spm.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.spm.common.util.constant.BkavConfigurationConstant;
import net.spm.common.util.constant.InvoiceStatusConstant;

/**
 * Created by NhanNguyen
 */
public class BkavInvoiceUtil {

	public static String buildItemName(String cppCode, String hours) {
		return String.format(BkavConfigurationConstant.INVOICE_ITEM_NAME, cppCode, hours);		
	}
	
	public static int calculatePriceBeforeTax(int price) {

		double temp = (price / 1.1);
		double result = Math.round(temp);
		return (int) result;
	}
	
	public static int calculateAmount(int price, int qty) {
		return price * qty;
	}
	
	public static int calculareTaxAmount(double taxRate, double amount) {
		double temp = taxRate * amount;
		double result = Math.round(temp);
		
		return (int) result;
	}
	
	public static String parseInvoiceStatus(int invoiceStatus) {
		String invoiceStatusStr = "";
		switch (invoiceStatus) {
			case InvoiceStatusConstant.CREATED:
				invoiceStatusStr = InvoiceStatusConstant.CREATED_STATUS_STR;
				break;
			case InvoiceStatusConstant.PUBLISED:
				invoiceStatusStr = InvoiceStatusConstant.PUBLISED_STATUS_STR;
				break;
			case InvoiceStatusConstant.DESTROYED:
				invoiceStatusStr = InvoiceStatusConstant.DESTROYED_STATUS_STR;
				break;
			case InvoiceStatusConstant.WAITING_FOR_REPLACED:
				invoiceStatusStr = InvoiceStatusConstant.WAITING_FOR_REPLACED_STATUS_STR;
				break;
			case InvoiceStatusConstant.REPLACE:
				invoiceStatusStr = InvoiceStatusConstant.REPLACE_STATUS_STR;
				break;
			case InvoiceStatusConstant.WAITING_FOR_UPDATED:
				invoiceStatusStr = InvoiceStatusConstant.WAITING_FOR_UPDATED_STATUS_STR;
				break;
			case InvoiceStatusConstant.UPDATE:
				invoiceStatusStr = InvoiceStatusConstant.UPDATE_STATUS_STR;
				break;
			case InvoiceStatusConstant.WAS_REPLACED:
				invoiceStatusStr = InvoiceStatusConstant.WAS_REPLACED_STATUS_STR;
				break;
			case InvoiceStatusConstant.WAS_UPDATED:
				invoiceStatusStr = InvoiceStatusConstant.WAS_UPDATED_STATUS_STR;
				break;
			case InvoiceStatusConstant.NULL:
				invoiceStatusStr = InvoiceStatusConstant.NULL_STATUS_STR;
				break;
			case InvoiceStatusConstant.NOT_USED:
				invoiceStatusStr = InvoiceStatusConstant.NOT_USED_STATUS_STR;
				break;
			default:
				invoiceStatusStr = "";
				break;
		}
		
		return invoiceStatusStr;
	}
	
	public static Map<String, String> parseTokenToKeyAndIvy(String partnerToken) {
		Map<String, String> keyMap = new HashMap<String, String>();
		
		String []arr =  partnerToken.split(":");
		if (arr.length == 2) {
			
			keyMap.put("KEY", arr[0]);
			keyMap.put("IVY", arr[1]);
			
			return keyMap;
		} else {
			return null;
		}	
	}
	
	public static String generateGuidString() {
		return UUID.randomUUID().toString();
	}
	
	public static String convertPhoneNumber(String phoneNumber) {
		StringBuffer buffer = new StringBuffer(phoneNumber);
		buffer.replace(0, 2, "0");
		
		return buffer.toString();
	}
}
