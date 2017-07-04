package org.dolphin.commons.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dolphin.commons.util.Base64Utils;
/**
 * http://hc.apache.org/httpcomponents-client-4.5.x/examples.html
 * @author yaokai
 *
 */
public class HttpSender {
	private CloseableHttpClient httpclient;
	private String url;
	private boolean isBase64;
	private String params;

	public HttpSender(String url, String params) {
		this.url = url;
		this.httpclient = HttpClients.createDefault();
		this.isBase64 = true;
		this.params = params;
	}

	public String get() throws IOException {
		String reqData = this.isBase64 ? Base64Utils.encodeString(this.params) : this.params;
		HttpGet httpget = new HttpGet(this.url+"?"+reqData);

		System.out.println("Executing request " + httpget.getRequestLine());

		// Create a custom response handler
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

			@Override
			public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			}

		};
		try {
			return this.httpclient.execute(httpget, responseHandler);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.close();
		}
		return null;
	}

	public String post() throws IOException {
		try {
			HttpPost httppost = new HttpPost(this.url);
			String reqData = this.isBase64 ? Base64Utils.encodeString(this.params) : this.params;
			StringEntity stringEntity = new StringEntity(reqData);

			httppost.setEntity(stringEntity);

			System.out.println("Executing request: " + httppost.getRequestLine());
			CloseableHttpResponse response = this.httpclient.execute(httppost);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}

	public CloseableHttpClient getHttpclient() {
		return httpclient;
	}

	public void setHttpclient(CloseableHttpClient httpclient) {
		this.httpclient = httpclient;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isBase64() {
		return isBase64;
	}

	public void setBase64(boolean isBase64) {
		this.isBase64 = isBase64;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
