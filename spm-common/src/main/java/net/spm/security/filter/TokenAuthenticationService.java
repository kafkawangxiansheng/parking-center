package net.spm.security.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import net.spm.common.util.JWTUtil;

/**
 * 
 * @author liemnh
 *
 */
public class TokenAuthenticationService {

	protected static final Logger LOGGER = Logger.getLogger(TokenAuthenticationService.class);
	static final String HEADER_STRING = "Authorization";
	static final String TOKEN_PREFIX = "Bearer ";

	/**
	 * Thuc hien lay thong tin xac thuc
	 *
	 * @param request
	 * @return
	 */
	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token == null || "".equals(token)) {
			return null;
		}
		try {
			String key = JWTUtil.checkKeyEncrypt(token.replace(TOKEN_PREFIX, ""));
			Algorithm algorithm = Algorithm.HMAC256(key);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token.replace(TOKEN_PREFIX, ""));
			String userId = jwt.getClaim("UserID").asInt().toString();
			LOGGER.info("Thong tin username:" + jwt.getClaim("UserID").asLong());
			Date expirationTime = jwt.getExpiresAt();
			if (expirationTime.compareTo(new Date()) < 0) {
				LOGGER.error("------Key expire--------------:" + jwt.getClaim("UserID").asInt());
				return null;
			}
			return userId != null ? new UsernamePasswordAuthenticationToken("admin", null, null) : null;
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return null;
	}

	/**
	 * Thuc hien lay thong tin user
	 *
	 * @param request
	 * @return
	 */
	public static String getAuthenticationInfo(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token == null || "".equals(token)) {
			return null;
		}
		return JWTUtil.getUserInfo(token.replace(TOKEN_PREFIX, "")).getUserId();
	}

	/**
	 * Thực hiện lấy role user
	 * 
	 * @param request
	 * @return
	 */
	public static String getAuthenticationRole(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token == null || "".equals(token)) {
			return null;
		}
		return JWTUtil.getUserInfo(token.replace(TOKEN_PREFIX, "")).getRole();
	}

	/**
	 * Thực hiện lấy group user
	 * 
	 * @param request
	 * @return
	 */
	public static String getAuthenticationGroup(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token == null || "".equals(token)) {
			return null;
		}
		return JWTUtil.getUserInfo(token.replace(TOKEN_PREFIX, "")).getGroup();
	}

	/**
	 * Thực hiện tao token của customer
	 * 
	 * @param request
	 * @return
	 */
	public static String createTokenCustomer(String cusId, String phone) {
		String token = JWTUtil.createTokenCustomer(cusId, phone);
		return token;
	}

	/**
	 * Thực hiện tao token của username
	 * 
	 * @param request
	 * @return
	 */
	public static String createTokenUser(String userId, Integer group, Integer role, boolean delegatePayment) {
		String token = JWTUtil.createTokenUser(userId, group, role, delegatePayment);
		return token;
	}

	public static void main(String[] args) {
		String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySUQiOjc0LCJHcm91cCI6MSwiUm9sZSI6MSwiZXhwIjoxOTI3MzQ2NjExfQ.98rVNBAHIJvFJAlaEexrF2ZdeMtcAvbCu6X6kUEIA6o";
		try {
			String key1 = "Pgkw660bPasdgk#5Bpaj$sdfp^bms@!jwgGpwm$b6709zPw4jabLq5D;3agP";
			DecodedJWT jwt = null;
			Algorithm algorithm = Algorithm.HMAC256(key1);
			JWTVerifier verifier = JWT.require(algorithm).build();
			jwt = verifier.verify(token.replace(TOKEN_PREFIX, ""));
			System.out.println(jwt.getClaim("UserID").asInt());
		} catch (Exception ex) {

		}
	}

}
