package com.spm.common;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.spm.constants.SecurityConstants;
import com.spm.dto.ResultObject;
import com.spm.dto.TokenDto;
import com.spm.dto.UserDto;
import com.spm.dto.UserRoleDto;

public class RestUtils<T> {

	Class<T> clazz;
	public RestUtils(Class<T> clazz) {
		this.clazz = clazz;
	}
	public TokenDto getAccessToken(String URL, String username, String password)
			throws ClientProtocolException, IOException {

		String apiBasicKey = SecurityConstants.CLIEN_ID + ":" + SecurityConstants.CLIENT_SECRET;

		BasicNameValuePair usernameParam = new BasicNameValuePair("username", username);
		BasicNameValuePair passwordParam = new BasicNameValuePair("password", password);
		BasicNameValuePair grantTypeParam = new BasicNameValuePair("grant_type", "password");
		List<NameValuePair> formParams = new ArrayList<>();
		formParams.add(usernameParam);
		formParams.add(passwordParam);
		formParams.add(grantTypeParam);

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(URL);
		String basicAuthorization = Base64.getEncoder().encodeToString(apiBasicKey.getBytes());
		postRequest.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + basicAuthorization);
		if (formParams != null) {
			postRequest.setEntity(new UrlEncodedFormEntity(formParams));
		}
		HttpResponse response = httpClient.execute(postRequest);
		String entityStringResult = EntityUtils.toString(response.getEntity(), "UTF-8");
		Gson gson = new Gson();
		TokenDto tokenDto = gson.fromJson(entityStringResult, TokenDto.class);
		return tokenDto;
	}

	public void revokeAccessToken(String URL, String username) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		HttpDelete deleteRequest = new HttpDelete(URL);
		BasicNameValuePair usernameParam = new BasicNameValuePair("username", username);
		BasicNameValuePair clientIdParam = new BasicNameValuePair("clientId", SecurityConstants.CLIEN_ID);
		List<NameValuePair> formParams = new ArrayList<>();
		formParams.add(usernameParam);
		formParams.add(clientIdParam);
		httpClient.execute(deleteRequest);
	}

	public UserDto getUserByUsername(String URL, String... token) {
		UserDto userDto = null;
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			HttpGet getRequest = new HttpGet(URL);
			if (token != null && token.length > 0) {
				getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token[0]);
			}

			HttpResponse response = httpClient.execute(getRequest);
			String entityStringResult = EntityUtils.toString(response.getEntity(), "UTF-8");
			Gson gson = new Gson();
			userDto = gson.fromJson(entityStringResult, UserDto.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return userDto;
	}

	public List<UserRoleDto> getUserRolesByUserId(String URL, String... token) {
		List<UserRoleDto> userRolesDtos = new ArrayList<>();

		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			HttpGet getRequest = new HttpGet(URL);
			if (token != null && token.length > 0) {
				getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token[0]);
			}

			HttpResponse response = httpClient.execute(getRequest);
			String entityStringResult = EntityUtils.toString(response.getEntity(), "UTF-8");

			Type listType = new TypeToken<ArrayList<UserRoleDto>>() {}.getType();
			userRolesDtos = new Gson().fromJson(entityStringResult, listType);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return userRolesDtos;
	}

	public ResultObject<List<T>> post(String URL, List<NameValuePair> formParams,  String... token)
			throws ClientProtocolException, IOException {

		ResultObject<List<T>> result = new ResultObject<>();

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(URL);
		if (token != null && token.length > 0) {
			postRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token[0]);
		}
		if (formParams != null) {
			postRequest.setEntity(new UrlEncodedFormEntity(formParams));
		}
		HttpResponse response = httpClient.execute(postRequest);
		String entityStringResult = EntityUtils.toString(response.getEntity(), "UTF-8");
		bindingDataForList(entityStringResult, result);
		return result;
	}
	
	public ResultObject<List<T>> postJSON(String URL, StringEntity stringEntity,  String... token)
			throws ClientProtocolException, IOException {

		ResultObject<List<T>> result = new ResultObject<>();

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(URL);
		if (token != null && token.length > 0) {
			postRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token[0]);
		}
		if (stringEntity != null) {
			postRequest.setEntity(stringEntity);
			postRequest.setHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());
			postRequest.setHeader("Content-type", ContentType.APPLICATION_JSON.getMimeType() +";charset=UTF-8");
		}
		HttpResponse response = httpClient.execute(postRequest);
		String entityStringResult = EntityUtils.toString(response.getEntity(), "UTF-8");
		if(entityStringResult != null && !entityStringResult.isEmpty()) {
			bindingDataForList(entityStringResult,result);
		}
		return result;
	}
	
	public ResultObject<List<T>> put(String URL, List<NameValuePair> formParams,  String... token)
			throws ClientProtocolException, IOException {

		ResultObject<List<T>> result = new ResultObject<>();

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPut postRequest = new HttpPut(URL);
		if (token != null && token.length > 0) {
			postRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token[0]);
		}
		if (formParams != null) {
			postRequest.setEntity(new UrlEncodedFormEntity(formParams));
		}
		HttpResponse response = httpClient.execute(postRequest);
		String entityStringResult = EntityUtils.toString(response.getEntity(), "UTF-8");
		bindingDataForList(entityStringResult,result);
		return result;
	}
	
	
	public ResultObject<List<T>> putJSON(String URL, StringEntity stringEntity,  String... token)
			throws ClientProtocolException, IOException {

		ResultObject<List<T>> result = new ResultObject<>();

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPut postRequest = new HttpPut(URL);
		if (token != null && token.length > 0) {
			postRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token[0]);
		}
		if (stringEntity != null) {
			postRequest.setEntity(stringEntity);
			postRequest.setHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());
			postRequest.setHeader("Content-type", ContentType.APPLICATION_JSON.getMimeType() +";charset=UTF-8");
		}
		HttpResponse response = httpClient.execute(postRequest);
		String entityStringResult = EntityUtils.toString(response.getEntity(), "UTF-8");
		bindingDataForList(entityStringResult,result);
		return result;
	}
	
	public String delete(String URL, String... token)
			throws ClientProtocolException, IOException {

		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpDelete postRequest = new HttpDelete(URL);
		if (token != null && token.length > 0) {
			postRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token[0]);
		}
		
		HttpResponse response = httpClient.execute(postRequest);
		String entityStringResult = EntityUtils.toString(response.getEntity(), "UTF-8");
		return  entityStringResult;
	}
	
	public ResultObject<List<T>> get(String getUrl, String... token) {
		ResultObject<List<T>> result = new ResultObject<>();
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			HttpGet getRequest = new HttpGet(getUrl);
			if (token != null && token.length > 0) {
				getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token[0]);
			}

			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() == HttpServletResponse.SC_UNAUTHORIZED) {
				result.setStatus(response.getStatusLine().getStatusCode());
				result.setDescription(response.getStatusLine().getReasonPhrase());
				return result;
			}
			String entityStringResult = EntityUtils.toString(response.getEntity(), "UTF-8");
			bindingDataForList(entityStringResult, result);

			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public ResultObject<Map<Object, T>> getWithMap(String getUrl, String... token) {
		ResultObject<Map<Object, T>> result = new ResultObject<>();
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			HttpGet getRequest = new HttpGet(getUrl);
			if (token != null && token.length > 0) {
				getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token[0]);
			}

			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() == HttpServletResponse.SC_UNAUTHORIZED) {
				result.setStatus(response.getStatusLine().getStatusCode());
				result.setDescription(response.getStatusLine().getReasonPhrase());
				return result;
			}
			String entityStringResult = EntityUtils.toString(response.getEntity(), "UTF-8");
			bindingDataForMap(entityStringResult, result);

			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	private void bindingDataForList(String dataString, ResultObject<List<T>> result) {
		JsonObject  datafromApi = JsonParser.parseString(dataString).getAsJsonObject();
		JsonArray array = datafromApi.get("data").getAsJsonArray();
		Gson gson = new Gson();
		List<T> data =  new ArrayList<>();
		for(final JsonElement json: array){
            T entity = gson.fromJson(json.toString(), clazz);
            data.add(entity);
        }
		result.setData(data);
		if(datafromApi.has("totalPages")) {
			result.setTotalPages(datafromApi.get("totalPages").getAsInt());
		}
		if(datafromApi.has("totalRows")) {
			result.setTotalRows(datafromApi.get("totalRows").getAsInt());
		}
	}
	
	private void bindingDataForMap(String dataString, ResultObject<Map<Object, T>> result) {
		JsonObject  datafromApi = JsonParser.parseString(dataString).getAsJsonObject();
		
		Gson gson = new Gson();
		Map<Object,T> data =  new HashMap<>();
		for(final Object key: datafromApi.keySet()){
            T entity = gson.fromJson(datafromApi.get(String.valueOf(key)).getAsString(), clazz);
            data.put(key, entity);
        }
		result.setData(data);
		if(datafromApi.has("totalPages")) {
			result.setTotalPages(datafromApi.get("totalPages").getAsInt());
		}
	}
}
