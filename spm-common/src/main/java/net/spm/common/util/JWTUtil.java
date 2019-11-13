package net.spm.common.util;

import java.text.ParseException;
import java.util.Calendar;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * Created by liemnh
 */
public class JWTUtil {

	static final String KEY_1 = "Pgkw660bPasdgk#5Bpaj$sdfp^bms@!jwgGpwm$b6709zPw4jabLq5D;3agP";
	static final String KEY_2 = "[wk6#0vs]^0gaWPb3j3halwjelz@!vPwPvmm501zFeWp#@wf";

	public static String checkKeyEncrypt(String jwtToken) {
		String key = "";
		try {
			String[] split_string = jwtToken.split("\\.");
			String base64EncodedBody = split_string[1];
			Base64 base64Url = new Base64(true);
			String body = new String(base64Url.decode(base64EncodedBody));
			JSONObject containerObject = new JSONObject(body);
			if (containerObject.has("Phone"))
				return KEY_2;
			else
				return KEY_1;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return key;
	}

	public static JwtObjectData getUserInfo(String jwtToken) {
		try {
			String[] split_string = jwtToken.split("\\.");
			String base64EncodedBody = split_string[1];
			Base64 base64Url = new Base64(true);
			String body = new String(base64Url.decode(base64EncodedBody));
			JSONObject containerObject = new JSONObject(body);
			JwtObjectData objJwt = new JwtObjectData();
			if (containerObject.has("UserID"))
				objJwt.setUserId(containerObject.getString("UserID"));
			if (containerObject.has("Group"))
				objJwt.setGroup(containerObject.getString("Group"));
			if (containerObject.has("Role"))
				objJwt.setRole(containerObject.getString("Role"));
			if (containerObject.has("Phone"))
				objJwt.setPhone(containerObject.getString("Phone"));
			return objJwt;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String createTokenCustomer(String cusId, String phone) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(KEY_2);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 6);
			String token = JWT.create().withClaim("UserID", Long.parseLong(cusId))
					.withClaim("Phone", Long.parseLong(phone)).withExpiresAt(cal.getTime()).sign(algorithm);
			return token;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String createTokenUser(String userId, Integer group, Integer role, boolean delegatePayment) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(KEY_1);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 6);
			String token = JWT.create().withClaim("UserID", Long.parseLong(userId)).withClaim("Group", group)
					.withClaim("Role", role).withClaim("DelegatePayment", delegatePayment).withExpiresAt(cal.getTime())
					.sign(algorithm);
			return token;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws ParseException {
		String token = createTokenCustomer("562954248391885", "84976068999");
		System.out.println(token);
	}

}
