package net.spm.common.util;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class TicketUtil {


    public static final String PREFIX_TICKET = "VL";
    public static final String PREFIX_MONTHLY_TICKET = "MT";
    public static final String PREFIX_CREATE_TOKEN = "CT";
    public static final String PREFIX_SUPER_CREATE_TOKEN = "SCT";
    public static final String PREFIX_TOP_UP = "TU";
    
    
    public static Long generateTicketId() {
    	Random random = new Random();
    	long randomId = Math.abs(random.nextLong());
    	String result = String.valueOf(randomId);
    	for(int x = result.length(); x < 16 ; x++) {
    		result = result+"0";
    	}
    	result = result.substring(0, 16);
    	
    	return Long.valueOf(result);
    }

    public static String getMonthlyTicketSessionFrom(String jsonMetadata) {
    	
    	String from = "00:00";	
    	try {
			JSONObject json = new JSONObject(jsonMetadata);
			if(json.has("monthly_session")) {
				JSONObject monthlySessionObject = json.getJSONObject("monthly_session");
				int f = 0;
				if(monthlySessionObject.has("F")) {
					f = monthlySessionObject.getInt("F");
				}
				from = f+":00";
				if(String.valueOf(f).length() == 1) {
					from = "0"+f+":00";
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	
    	return from;
    }
    
    public static String getMonthlyTicketSessionTo(String jsonMetadata) {
    	
    	String to = "23:59";	
    	try {
			JSONObject json = new JSONObject(jsonMetadata);
			if(json.has("monthly_session")) {
				JSONObject monthlySessionObject = json.getJSONObject("monthly_session");
				int t = 0;
				if(monthlySessionObject.has("T")) {
					t = monthlySessionObject.getInt("T");
				}
				if(t == 0) {
					return to;
				}
				
				to = t+":00";
				if(String.valueOf(t).length() == 1) {
					to = "0"+t+":00";
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	
    	return to;
    }
    public static void main(String[] args) {
    	String result = getMonthlyTicketSessionTo("{\"address\":\"12 Ngõ Trạm, Quận Hoàn Kiếm\",\"car_image_url\":\"\",\"cars\":[{\"car_type\":{\"Id\":3,\"P_class\":\"Loai1\",\"Seats\":7,\"Status\":1,\"Ton\":0},\"number_plate\":\"30A83816\",\"vehicle\":\"toyota\",\"vehicle_authorization\":\"\",\"vehicle_version\":\"\"}],\"contract_code\":\"608ip/d\",\"contract_one_image_url\":\"/p/file/11,017894d1e46972/3854526609934634667\",\"contract_two_image_url\":\"/p/file/19,017895009940c2/10566044110673516550\",\"from_date\":1514739600,\"month_number\":1,\"monthly_session\":{\"Desc\":\"Đêm\",\"F\":18,\"M\":12,\"P\":700000,\"T\":6},\"price_per_month\":2000000,\"tax_code\":\"\"}");
    	System.out.println("To:"+result);
    	result = getMonthlyTicketSessionFrom("{\"address\":\"12 Ngõ Trạm, Quận Hoàn Kiếm\",\"car_image_url\":\"\",\"cars\":[{\"car_type\":{\"Id\":3,\"P_class\":\"Loai1\",\"Seats\":7,\"Status\":1,\"Ton\":0},\"number_plate\":\"30A83816\",\"vehicle\":\"toyota\",\"vehicle_authorization\":\"\",\"vehicle_version\":\"\"}],\"contract_code\":\"608ip/d\",\"contract_one_image_url\":\"/p/file/11,017894d1e46972/3854526609934634667\",\"contract_two_image_url\":\"/p/file/19,017895009940c2/10566044110673516550\",\"from_date\":1514739600,\"month_number\":1,\"monthly_session\":{\"Desc\":\"Đêm\",\"F\":18,\"M\":12,\"P\":700000,\"T\":6},\"price_per_month\":2000000,\"tax_code\":\"\"}");
    	System.out.println("From:"+result); 
    }
}
