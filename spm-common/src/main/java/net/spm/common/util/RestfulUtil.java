package net.spm.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class RestfulUtil {
	
	protected final Logger LOGGER = Logger.getLogger(getClass());

	private static String getAccessToken() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		String authenRequestURL = Constants.NAPAS_URL_AUTHENTICATION;

		authenRequestURL = authenRequestURL.replace("{clientID}", Constants.NAPAS_CLIENT_ID);
		authenRequestURL = authenRequestURL.replace("{clientSerect}", Constants.NAPAS_CLIENT_SECRET);
		authenRequestURL = authenRequestURL.replace("{username}", Constants.NAPAS_USERNAME);
		authenRequestURL = authenRequestURL.replace("{password}", Constants.NAPAS_PASSWORD);
		HttpPost postRequest = new HttpPost(authenRequestURL);

		HttpResponse response = httpClient.execute(postRequest);
		if (response.getEntity() != null) {
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		}
		return null;
	}

	public static String put(String putURL, JSONObject requestObject, String mediaType) {

		try {

			String napasToken = getAccessToken();
			JSONObject tokenObject = new JSONObject(napasToken);

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			HttpPut putRequest = new HttpPut(putURL);

			StringEntity input = new StringEntity(requestObject.toString());

			input.setContentType(mediaType);
			putRequest.addHeader("Authorization",
					tokenObject.getString("token_type") + " " + tokenObject.getString("access_token"));
			putRequest.setEntity(input);

			HttpResponse response = httpClient.execute(putRequest);

			return EntityUtils.toString(response.getEntity(), "UTF-8");

		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String post(String postUrl, JSONObject requestObject, String mediaType) {
		String result = "";
		try {
			String napasToken = getAccessToken();
			JSONObject tokenObject = new JSONObject(napasToken);

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			HttpPost postRequest = new HttpPost(postUrl);

			StringEntity input = new StringEntity(requestObject.toString());

			input.setContentType(mediaType);
			postRequest.addHeader("Authorization",
					tokenObject.getString("token_type") + " " + tokenObject.getString("access_token"));
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static String postWithOutAccessToken(String postUrl, JSONObject requestObject, String mediaType) {
		String result = "";
		try {

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost(postUrl);
			StringEntity input = new StringEntity(requestObject.toString());
			input.setContentType(mediaType);
			input.setContentEncoding("UTF-8");
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);

			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static String post(String postUrl) {
		String result = "";
		try {

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			HttpPost postRequest = new HttpPost(postUrl);

			HttpResponse response = httpClient.execute(postRequest);

			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static String get(String getUrl, String... mediaType) {
		try {
			String napasToken = getAccessToken();
			JSONObject tokenObject = new JSONObject(napasToken);

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			HttpGet getRequest = new HttpGet(getUrl);

			getRequest.addHeader("Authorization",
					tokenObject.getString("token_type") + " " + tokenObject.getString("access_token"));

			HttpResponse response = httpClient.execute(getRequest);

			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String get(String getUrl) {
		try {
			
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			HttpGet getRequest = new HttpGet(getUrl);

			HttpResponse response = httpClient.execute(getRequest);

			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String delete(String getUrl, String mediaType) {
		try {
			String napasToken = getAccessToken();
			JSONObject tokenObject = new JSONObject(napasToken);

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			HttpDelete getRequest = new HttpDelete(getUrl);

			getRequest.addHeader("Authorization",
					tokenObject.getString("token_type") + " " + tokenObject.getString("access_token"));

			HttpResponse response = httpClient.execute(getRequest);

			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String getWithOutAccessToke(String getUrl, Map<String, String> formParams, String... mediaType) {
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String finalURL = getUrl;
			if (formParams != null) {
				finalURL = finalURL + "?";
				int index = 0;
				for (String key : formParams.keySet()) {
					finalURL = finalURL + key + "=" + formParams.get(key);
					index++;
					if (index < formParams.keySet().size()) {
						finalURL = finalURL + "&";
					}
				}
			}

			HttpGet getRequest = new HttpGet(finalURL);
			HttpResponse response = httpClient.execute(getRequest);
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static InputStream getWithOutAccessToken(String getUrl, Map<String, String> formParams,
			String... mediaType) {
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String finalURL = getUrl;
			if (formParams != null) {
				finalURL = finalURL + "?";
				int index = 0;
				for (String key : formParams.keySet()) {
					finalURL = finalURL + key + "=" + formParams.get(key);
					index++;
					if (index < formParams.keySet().size()) {
						finalURL = finalURL + "&";
					}
				}
			}

			HttpGet getRequest = new HttpGet(finalURL);
			HttpResponse response = httpClient.execute(getRequest);
			return response.getEntity().getContent();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * thực hiện post data
	 * 
	 * @param postUrl
	 * @param formParams
	 * @param mediaType
	 * @return
	 */

	public static String postFormData(String postUrl, List<NameValuePair> formParams, String mediaType) {
		String result = "";
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost(postUrl);
			postRequest.setEntity(new UrlEncodedFormEntity(formParams));
			if (!StringUtils.isEmpty(mediaType)) {
				postRequest.setHeader("Content-Type", mediaType);
			}
			HttpResponse response = httpClient.execute(postRequest);
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * thực hiện post data
	 * 
	 * @param postUrl
	 * @param formParams
	 * @param mediaType
	 * @return
	 */

	public static String postFormDataWithHeader(String postUrl, List<NameValuePair> formParams, String mediaType,
			String token) {
		String result = "";
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost(postUrl);
			postRequest.setEntity(new UrlEncodedFormEntity(formParams));
			postRequest.setHeader("Content-Type", mediaType);
			postRequest.setHeader(new BasicHeader("Authorization", token));
			HttpResponse response = httpClient.execute(postRequest);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static String postFormData(String postUrl, Map<String, String> paramsMap, String mediaType) {
		String result = "";
		try {
			List<NameValuePair> params = new ArrayList<>();
			for (String key : paramsMap.keySet()) {
				params.add(new BasicNameValuePair(key, paramsMap.get(key)));
			}
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost(postUrl);
			postRequest.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
			postRequest.setHeader("Content-Type", mediaType);
			HttpResponse response = httpClient.execute(postRequest);
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}