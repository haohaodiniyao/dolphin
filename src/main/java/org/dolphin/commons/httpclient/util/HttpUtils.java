package org.dolphin.commons.httpclient.util;

import com.tuniu.intlsdl.utils.ContextUtil;
import com.tuniu.intlsdl.utils.SystemConfigs;
import com.tuniu.intlsdl.utils.logging.RequestLogger;
import com.tuniu.intlsdl.utils.tool.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 基于HTTP协议的网络请求接口，对HttpClient进行封装，使用HttpClient版本为4.3
 * 
 * @author
 */
public class HttpUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * Cookie 容器
	 */
	private BasicCookieStore cookieStore;

	/**
	 * SSL 设置
	 */
	private LayeredConnectionSocketFactory sslSocketFactory = null;
	
	/**
	 * 连接超时时间
	 */
	private int connectionTimeout = 30 * 1000;

	/**
	 * 等待数据超时时间
	 */
	private int soTimeout = 30 * 1000;

	/**
	 * 代理
	 */
	private HttpProxy proxy = null;
	
	/**
	 * 代理认证
	 */
	private CredentialsProvider proxyCredentialsProvider = null;
	
	/**
	 * HTTP认证
	 */
	private CredentialsProvider wwwCredentialsProvider = null;

	/**
	 * 请求重试处理，默认请求失败后会重试 2 次
	 */
	private HttpRequestRetryHandler retryHandler = null;

	/**
	 * 使用的 Cookie 规范
	 */
	private String cookieSpec = CookieSpecs.BEST_MATCH;
	
	/**
	 * 请求
	 */
	private HttpRequest request = null;

	/**
	 * 压缩
	 */
	private boolean isZip = false;

	/**
	 * 设置timeout标识
	 */
	private boolean isTimeOut = true;

	public void setZip(boolean isZip){
		this.isZip = isZip;
	}

	public void setTimeOut(int timeOut) {
		isTimeOut = true;
		this.setSoTimeout(timeOut * 1000);
		this.setConnectionTimeout(timeOut * 1000);
	}

	/**
	 * default constructor
	 */
	public HttpUtils() {
		
		// 使用基本的 cookie 容器
		this.cookieStore = new BasicCookieStore();
		
		// 设置默认的重试机制：每个请求如果出现异常，再重试2次
		this.retryHandler = new HttpRequestRetryHandler() {

		    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {

		    	// 未开启自动重试
		    	if (!SystemConfigs.enableHttpRequestRetry()) {
		    		return false;
				}

		    	if (executionCount > SystemConfigs.getHttpRequestRetryCount()) {
		        	LOGGER.info("执行次数超过上限，请求失败！");
		            return false;
		        }

		    	LOGGER.info("请求出现异常：" + exception.toString());
		    	LOGGER.info("执行次数：" + executionCount + "，正在重试...");
		        return true;
		    }
		};
	}

	/**
	 * 使用get方式获取一个页面，带参数
	 * 
	 * @param url
	 *            待获取页面URL
	 * @return 返回获取到的结果
	 * @throws BaseHttpException
	 *             获取页面失败，抛出异常
	 */
	private HttpGet getMethod(String url, Map<String, String> input) throws BaseHttpException {
		if (input != null) {
			for (Entry<String, String> entry : input.entrySet()) {
				if (-1 == url.indexOf("?")) {
					url += "?" + entry.getKey() + "=" + entry.getValue();
				} else {
					url += "&" + entry.getKey() + "=" + entry.getValue();
				}
			}
		}

		HttpGet getMethod = new HttpGet(url);
		return getMethod;
	}

	/**
	 * 使用post方式获取一个页面，带参数
	 * 
	 * @param url
	 *            待获取页面URL
	 * @return 返回获取到的结果
	 * @throws BaseHttpException
	 *             获取页面失败，抛出异常
	 */
	private HttpPost postMethod(String url, Map<String, String> input) {
		HttpPost postMethod = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (input != null) {
			// 遍历map组装参数数组
			for (Entry<String, String> entry : input.entrySet()) {
				// 针对亚航参数名称相同并且参数有序的情况特殊处理下参数
				if (entry.getKey().contains("[]")) {
					params.add(new BasicNameValuePair(entry.getKey().substring(0, entry.getKey().indexOf("[]") + 2), entry.getValue()));
				} else {
					params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}

			postMethod.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
		}
		return postMethod;

	}

	protected HttpPost soapPostMethod(String url, Map<String, String> params) throws BaseHttpException {
		HttpPost postMethod = new HttpPost(url);
		postMethod.setHeader("Content-Type", "text/xml; charset=UTF-8");
		HttpEntity re;
		try {
			if (isZip) {
				byte[] zipInputString = gzip(params.get("params").getBytes("UTF-8"));
				re = new ByteArrayEntity(zipInputString);
			} else {
				re = new StringEntity(params.get("params"), "UTF-8");
			}
		} catch (Exception e) {
			throw new BaseHttpException(url, e);
		}
		postMethod.setEntity(re);
		return postMethod;

	}
	
	private HttpPost postMethod(String url, String params) throws BaseHttpException {
		HttpPost postMethod = new HttpPost(url);
		postMethod.setHeader("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity re;
		try {
			re = new StringEntity(params, "UTF-8");
		} catch (Exception e) {
			throw new BaseHttpException(url, e);
		}
		postMethod.setEntity(re);
		return postMethod;

	}

	/**
	 * 忽略所有的证书校验
     */
	public void ignoreAllCerts() {

		TrustManager[] trustAllCerts = new TrustManager[] {
				new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					public void checkClientTrusted(X509Certificate[] certs, String authType) {
						// don't check
					}
					public void checkServerTrusted(X509Certificate[] certs, String authType) {
						// don't check
					}
				}
		};

		X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
			public void verify(String arg0, SSLSocket arg1) throws IOException {}
			public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
			public void verify(String arg0, X509Certificate arg1) throws SSLException {}
		};

		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, trustAllCerts, null);
			this.sslSocketFactory = new SSLConnectionSocketFactory(ctx, hostnameVerifier);
		} catch (Exception e) {
			LOGGER.error("忽略证书出错：" + e.getMessage(), e);
		}
	}

	/**
	 * 根据 keystore 文件，设置 SSL 信息
	 */
	public void setKeyStore(InputStream keyStoreFile, String password) {
		
		try {
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(keyStoreFile, password.toCharArray());
			
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(ks);
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, tmf.getTrustManagers(), null);
			
			this.sslSocketFactory = new SSLConnectionSocketFactory(ctx);	
		} catch (Exception e) {
			LOGGER.error("设置 keystore 文件出错：" + e.getMessage(), e);
		}
	}
	
	/**
	 * 获取 keystore
	 */
	public LayeredConnectionSocketFactory getKeyStore() {
		return this.sslSocketFactory;
	}
	
	/**
	 * 手工设置 SSL 信息，对于一些对 HTTPS 安全等级有特殊要求的渠道很有用，如 ABACUS 需要 TLSv1.2 以上的加密等级。
	 * @param sslSocketFactory
	 */
	public void setSslSocketFactory(LayeredConnectionSocketFactory sslSocketFactory) {
		this.sslSocketFactory = sslSocketFactory;
	}
	
	/**
	 * @param url
	 *            请求地址(String)
	 * @param method
	 *            请求方式(String,输入post才执行post方法,输入soap执行webservice调用，
	 *            不输入或者其他都视为get方式)
	 * @param input
	 *            输入参数(Map<String, String>)
	 * @param headers
	 *            请求头(HttpHeaders)
	 * @return
	 * @throws Exception
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private HttpResponse send(String url, String method, Map<String, String> input, Map<String, String> headers, Map<String, String> cookiesInput)
			throws BaseHttpException {
		
		CloseableHttpClient httpclient = null;
		try {
			
			// 初始化 HTTP 请求
			HttpRequestBase request = this.prepareHttpRequest(url, method, input, headers);
			
			// 更新 cookie
			this.updateCookieStore(url, cookiesInput);
			
			// SOCKS 代理
			HttpClientContext context = HttpClientContext.create();
			if (this.proxy != null && "SOCKS".equals(this.proxy.getProtocol())) {
				InetSocketAddress socksaddr = new InetSocketAddress(this.proxy.getIp(), Integer.parseInt(this.proxy.getPort()));
				context.setAttribute("socks.address", socksaddr);
			}
			
			// 开始请求
			httpclient = this.initHttpClient();
			CloseableHttpResponse response = httpclient.execute(request, context);
			
			// 转换结果
			return this.convertHttpResponse(response);
			
		} catch (Exception e) {
			throw new BaseHttpException(url, e);
		} finally {
			// 关闭连接
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					// 忽略
				}
			}
		}
	}

	/**
	 * 初始化 http client
	 * @return 返回 httpclient 实例
	 */
	private CloseableHttpClient initHttpClient() {

		Builder configBuilder = RequestConfig.custom();
		configBuilder.setConnectTimeout(this.connectionTimeout);
		configBuilder.setSocketTimeout(this.soTimeout);
		configBuilder.setConnectionRequestTimeout(this.connectionTimeout);
		RequestConfig globalConfig = configBuilder.build();

		HttpClientBuilder clientBuilder = HttpClients.custom();
		clientBuilder.setDefaultRequestConfig(globalConfig);
		clientBuilder.setRetryHandler(retryHandler);
		clientBuilder.setDefaultCookieStore(cookieStore);
		clientBuilder.disableRedirectHandling();
		clientBuilder.setSSLSocketFactory(sslSocketFactory);

		// 支持 SOCKS 代理，当使用 SOCKS 代理时，需要使用自定义的连接管理
		if (this.proxy != null && "SOCKS".equals(this.proxy.getProtocol())) {
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
			        .register("http", new MyPlainConnectionSocketFactory())
			        .register("https", new MySSLConnectionSocketFactory(SSLContexts.createSystemDefault()))
			        .build();
			PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
			clientBuilder.setConnectionManager(connectionManager);
		}

		// 注意：代理认证和HTTP认证只能二选一，如果使用了HTTP认证，则不使用代理
		if (wwwCredentialsProvider != null) {
			clientBuilder.setDefaultCredentialsProvider(wwwCredentialsProvider);
		} else {
			clientBuilder.setDefaultCredentialsProvider(proxyCredentialsProvider);
		}

		return clientBuilder.build();
	}
	
	/**
	 * 更新 cookie 容器
	 * @param url
	 * @param cookiesInput
	 */
	public void updateCookieStore(String url, Map<String, String> cookiesInput) {
		
		// 设置 cookie
		if (cookiesInput != null) {
			// 根据url获取主机
			String domain = url.split("/")[2];
			String path = "/";
			// 获取到现有的cookieid，然后在加工处理
			for (Entry<String, String> entry : cookiesInput.entrySet()) {
				// 新建一个Cookie
				BasicClientCookie cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
				cookie.setDomain(domain);
				cookie.setPath(path);
				cookieStore.addCookie(cookie);

			}
		}
	}

	/**
	 * 初始化 HTTP 请求
	 * @param url 请求 URL
	 * @param method 请求方法
	 * @param input 请求参数
	 * @param headers 请求头部
	 * @return
	 * @throws BaseHttpException
	 */
	private HttpRequestBase prepareHttpRequest(String url, String method, Map<String, String> input, Map<String, String> headers) throws BaseHttpException {
		
		HttpRequestBase httpMethod = null;
		
		// 根据输入method为post调用post，其他情况都是get
		if (method != null && "post".equals(method.toLowerCase())) {
			if (input != null && input.containsKey("params") && input.size() == 1) {
				// POST 参数是字符串格式
				httpMethod = postMethod(url, input.get("params"));
			} else {
				// POST 参数是字典格式
				httpMethod = postMethod(url, input);
			}
		} else if (method != null && "soap".equals(method.toLowerCase())) {
			httpMethod = soapPostMethod(url, input);
		} else {
			httpMethod = getMethod(url, input);
		}

		// 设置请求头部
		httpMethod.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
		if (headers != null) {
			for (Entry<String, String> entry : headers.entrySet()) {
				if ("Content-Type".equals(entry.getKey()) || "User-Agent".equals(entry.getKey())) {
					httpMethod.setHeader(entry.getKey(), entry.getValue());
				} else {
					httpMethod.addHeader(entry.getKey(), entry.getValue());
				}
			}
		}
		
		// 设置代理
		HttpHost proxyHost = null;
		if (this.proxy != null && !"SOCKS".equals(this.proxy.getProtocol())) {
			proxyHost = new HttpHost(proxy.getIp(), Integer.parseInt(proxy.getPort()), proxy.getProtocol());
		}
		
		// 设置 cookie 规范
		String cookieSpec = CookieSpecs.BEST_MATCH;
		if (this.cookieSpec != null) {
			cookieSpec = this.cookieSpec;
		}

		Builder builder = RequestConfig.custom()
				.setProxy(proxyHost)
				.setCookieSpec(cookieSpec);
		if (isTimeOut) {
			builder.setConnectTimeout(this.connectionTimeout);
			builder.setSocketTimeout(this.soTimeout);
		}
		httpMethod.setConfig(builder.build());
		
		return httpMethod;
	}
	
	/**
	 * 将 httpclient 返回结果转换为 HttpResponse 对象
	 * @param response httpclient 的返回结果
	 * @return
	 * @throws HttpStatusNotOkException 
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	private HttpResponse convertHttpResponse(CloseableHttpResponse response) throws HttpStatusNotOkException, IOException {

		// 程序只处理200和302两个状态码，其他都报错
		int statusCode = response.getStatusLine().getStatusCode();
        if (!this.isIgnoreHttpStatusNotOk()) {
            String reasonPhrase = response.getStatusLine().getReasonPhrase();
            if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_MOVED_TEMPORARILY) {
                throw new HttpStatusNotOkException("HTTP 请求响应：" + statusCode + " " + reasonPhrase);
            }
        }

		// 读取响应内容
		// 封装返回值
		HttpResponse result = new HttpResponse();
		this.readResponse(response,result);
		// 获取响应中的 cookie
		Map<String, String> cookiesMap = new HashMap<String, String>();
		List<Cookie> cookies = cookieStore.getCookies();
		if (cookies != null && cookies.size() > 0) {
			for (Cookie cookie : cookies) {
				cookiesMap.put(cookie.getName(), cookie.getValue());
			}
		}
		
		// 获取响应中的 Location 头部
		String location = null;
		if (response.getFirstHeader("Location") != null) {
			location = URLEncoder.encode(response.getFirstHeader("Location").getValue(), "utf-8");	
		}
		
		// 获取返回的 Content-Type: 
		//    - application/json;charset=UTF-8
		//    - image/gif
		String contentType = "";
		if (response.getFirstHeader("Content-Type") != null) {
			contentType = response.getFirstHeader("Content-Type").getValue();
		}
		
		result.setStatusCode(statusCode + "");
		result.setCookies(cookiesMap);
		result.setLocation(location);
		result.setContentType(contentType);
		return result;
	}
	
	/**
	 * 读取 http 请求相应，返回字符串
	 * @param response 请求相应对象
	 * @throws IOException
	 */
	private void readResponse(CloseableHttpResponse response, HttpResponse result) throws IOException {

		// 读取请求的响应，如果请求头部包含 gzip ，则使用 GZIPInputStream 进行 gzip 解压。
		InputStream inputStream = null;
		HttpEntity entity = response.getEntity();
		Header contentEncoding = response.getFirstHeader("Content-Encoding");
		String currentChannel = ContextUtil.getCurrentChannel() != null ? ContextUtil.getCurrentChannel().toLowerCase() : "";
		String serviceName = ContextUtil.getCurrentServiceName() != null ? ContextUtil.getCurrentServiceName().toLowerCase() : "";
		if (currentChannel.startsWith("ibe") && !"queryflight".equals(serviceName)) {
			try{
				if (response.getHeaders("CorrelationId").length > 0 && response.getHeaders("EchoToken").length > 0) {
					LOGGER.info("记录返回标识符：channel: " + ContextUtil.getCurrentChannel() + ", serviceName:" + ContextUtil.getCurrentServiceName() +
							", CorrelationId:" + response.getHeaders("CorrelationId") != null ? response.getHeaders("CorrelationId")[0].getValue() : "" +
							", EchoToken: " + response.getHeaders("EchoToken") != null ? response.getHeaders("EchoToken")[0].getValue() : "");
				}
			} catch (Exception e) {
				LOGGER.error("ibe serviceName:" + ContextUtil.getCurrentServiceName() + "，记录返回标识符异常：" + e.toString(), e);
			}
		}

		if (contentEncoding != null && contentEncoding.getValue().toLowerCase().contains("gzip")) {
			inputStream = entity == null ? null : new GZIPInputStream(entity.getContent());
		} else {
			inputStream = entity == null ? null : entity.getContent();
		}
		// 获取字节流内容
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int rc = 0;
		while ((rc = inputStream.read(buff)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		swapStream.close();
		if(inputStream == null){
			inputStream.close();
		}
		byte[] byteArray;
		if (isZip) {
			byteArray = unzip(swapStream.toByteArray());
		} else {
			byteArray = swapStream.toByteArray();
		}
		result.setByteContent(byteArray);
		result.setContent(new String(result.getByteContent(),this.getEncoding()));
	}

	public static byte[] unzip(byte[] zipBytes) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(zipBytes);
		ZipInputStream zis = new ZipInputStream(bais);
		zis.getNextEntry();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final int BUFSIZ = 4096;
		byte inbuf[] = new byte[BUFSIZ];
		int n;
		try{
			while ((n = zis.read(inbuf, 0, BUFSIZ)) != -1) {
				baos.write(inbuf, 0, n);
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}

		byte[] data = baos.toByteArray();
		zis.close();
		return data;
	}

	public static byte[] gzip(byte[] data) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipEntry ze = new ZipEntry("servletservice");
		ZipOutputStream zos = new ZipOutputStream(baos);
		zos.putNextEntry(ze);
		zos.write(data, 0, data.length);
		zos.close();
		byte[] zipBytes = baos.toByteArray();
		return zipBytes;
	}


	/**
	 * 对 send 函数封装一层，用于记录请求的日志
	 */
	private HttpResponse timingSend(String url, String method, Map<String, String> input, Map<String, String> headers, Map<String, String> cookiesInput, boolean enableLogging)
			throws BaseHttpException {
		
		// url可能是带特殊标志的，形如 http://www.baidu.com#TEST#
		// 请求时需要将##删除，使用真实的url
		String[] arrs = url.split("#");
		String trueUrl = arrs[0];
		
		// 计时
		Date start = new Date();
		HttpResponse response = null;
		BaseHttpException exception = null;
		try {
			if (SystemConfigs.enableHttpRequestAsync()) {
				// 是否开启异步请求模式
				response = asyncSend(trueUrl, method, input, headers, cookiesInput);
			} else {
				// 同步请求模式，可能会出现网络卡死
				response = send(trueUrl, method, input, headers, cookiesInput);
			}
			return response;
		} catch (BaseHttpException e) {
			exception = e;
			throw e;
		} finally {
			// 记录请求的日志
			int timeSpan = TimeUtils.getTimeSpan(start);
			RequestLogger.log(url, method, input, response, exception, timeSpan, this.proxy, enableLogging);
			RequestLogger.requestLogAfterTicket(url, method, input, response, exception, timeSpan, this.proxy);
		}
	}
	
	//
	// 由于底层处理网络请求为同步 IO，可能会出现卡死现象，设置的请求超时参数不起作用。
	// 这里新建一个线程来处理网络请求，并使用线程的 join 方法来等待，避免请求卡死。
	//
	private HttpResponse asyncSend(String url, String method, Map<String, String> input, Map<String, String> headers, Map<String, String> cookiesInput)
			throws BaseHttpException {
		
		// 将异步请求的返回值放在这个数组中，第一个元素为请求返回值，第二个元素为可能出现的异常
		final Object[] returnValues = new Object[2];
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					returnValues[0] = send(url, method, input, headers, cookiesInput);
				} catch (BaseHttpException e) {
					returnValues[1] = e;
				}
			}
		});
		thread.setName("requests");
		thread.start();
		
		// 使用网络超时时间来等待请求线程结束，如果超时，线程会忽略该请求，继续往下执行，注意这个时候 returnValues 数组中的两个值都是 null
		try {
			thread.join(this.getConnectionTimeout());
		} catch (InterruptedException e) {
			throw new BaseHttpException("网络请求线程被中断！", e);
		}
		
		if (returnValues[1] != null) {
			throw (BaseHttpException)returnValues[1];
		}
		if (returnValues[0] == null) {
			thread.interrupt();
			throw new BaseHttpException(url, "网络请求超时！");
		}
		return (HttpResponse)returnValues[0];
	}
	
	/**
	 * 发送 HTTP GET 请求
	 * @param url 请求 URL
	 * @return 返回请求结果
	 * @throws BaseHttpException
	 */
	public HttpResponse get(String url) throws BaseHttpException {
		return timingSend(url, "get", null, null, null, false);
	}

	/**
	 * 发送 HTTP GET 请求
	 * @param url 请求 URL
	 * @param parameters 请求参数
	 * @return 返回请求结果
	 * @throws BaseHttpException
	 */
	public HttpResponse get(String url, Map<String, String> parameters) throws BaseHttpException {
		return timingSend(url, "get", parameters, null, null, false);
	}

	/**
	 * 发送 HTTP GET 请求
	 * @param request 请求参数
	 * @return 返回请求结果
	 * @throws BaseHttpException
	 */
	public HttpResponse get(HttpRequest request) throws BaseHttpException {
		this.request = request;
		return timingSend(request.getUrl(), "get", request.getParameters(), request.getHeaders(), request.getCookies(), request.isEnableLogging());
	}

	/**
	 * 发送 HTTP POST 请求
	 * @param url 请求 URL
	 * @return 返回请求结果
	 * @throws BaseHttpException
	 */
	public HttpResponse post(String url) throws BaseHttpException {
		return timingSend(url, "post", null, null, null, false);
	}

	/**
	 * 发送 HTTP POST 请求
	 * @param url 请求 URL
	 * @param parameters 请求参数
	 * @return 返回请求结果
	 * @throws BaseHttpException
	 */
	public HttpResponse post(String url, Map<String, String> parameters) throws BaseHttpException {
		return timingSend(url, "post", parameters, null, null, false);
	}

	/**
	 * 发送 HTTP POST 请求
	 * @param request 请求参数
	 * @return 返回请求结果
	 * @throws BaseHttpException
	 */
	public HttpResponse post(HttpRequest request) throws BaseHttpException {
		this.request = request;
		if (request.getPostData() != null) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("params", request.getPostData());
			request.setParameters(params);
		}
		return timingSend(request.getUrl(), "post", request.getParameters(), request.getHeaders(), request.getCookies(), request.isEnableLogging());
	}

	/**
	 * 发送 HTTP POST 请求（但是 ContentType 是 text/xml，一般用于 SOAP 请求）
	 * @param url 请求 URL
	 * @param input 请求参数
	 * @return 返回请求结果
	 * @throws BaseHttpException
	 */
	public HttpResponse soap(String url, String input) throws BaseHttpException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("params", input);
		return timingSend(url, "soap", params, null, null, false);
	}
	
	/**
	 * 发送 HTTP POST 请求（但是 ContentType 是 text/xml，一般用于 SOAP 请求）
	 * @param request 请求
	 * @return 返回请求结果
	 * @throws BaseHttpException
	 */
	public HttpResponse soap(HttpRequest request) throws BaseHttpException {
		this.request = request;
		Map<String, String> params = new HashMap<String, String>();
		params.put("params", request.getPostData());
		return timingSend(request.getUrl(), "soap", params, request.getHeaders(), request.getCookies(), request.isEnableLogging());
	}

	/**
	 * 获取当前所使用的代理
	 */
	public HttpProxy getProxy() {
		return proxy;
	}

	/**
	 * 设置要使用的代理
	 */
	public void setProxy(HttpProxy proxy) {
		this.proxy = proxy;
		
		// 代理可能需要使用用户名和密码进行认证
		if (proxy != null && 
			StringUtils.isNotEmpty(proxy.getUsername()) && 
			StringUtils.isNotEmpty(proxy.getPassword())) {
			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(proxy.getUsername(), proxy.getPassword()));
			this.proxyCredentialsProvider = credentialsProvider;
		}
	}
	
	/**
	 * 设置 HTTP 认证，只支持基本的用户名密码认证
	 */
	public void setHttpAuth(String username, String password) {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
		this.wwwCredentialsProvider = credentialsProvider;
	}

	/**
	 * 清空所有 Cookie
	 */
	public void clearCookies() {
		if (this.cookieStore == null) {
			this.cookieStore = new BasicCookieStore();
		} else {
			this.cookieStore.clear();
		}
	}

	/**
	 * 获取连接超时时间
	 */
	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * 设置连接超时时间
	 */
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * 获取等待数据超时时间
	 */
	public int getSoTimeout() {
		return soTimeout;
	}

	/**
	 * 设置等待数据超时时间
	 */
	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}
	
	/**
	 * 设置重试机制处理，如果无需重试，可以设置为 null
	 */
	public void setRetryHandler(HttpRequestRetryHandler retryHandler) {
		this.retryHandler = retryHandler;
	}

	/**
	 * 获取当前使用的 Cookie 规范
	 * @return
	 */
	public String getCookieSpec() {
		return cookieSpec;
	}

	/**
	 * 设置 Cookie 规范
	 * @param cookieSpec
	 */
	public void setCookieSpec(String cookieSpec) {
		this.cookieSpec = cookieSpec;
	}
	
	/**
	 * 获取编码格式，默认 utf-8
	 * @return
	 */
	public String getEncoding() {
		
		if (this.request == null || StringUtils.isBlank(this.request.getEncoding())) {
			return "utf-8";
		}
		return this.request.getEncoding();
	}

    /**
     * 是否忽略 HttpStatusNotOkException 异常
     * @return
     */
	public boolean isIgnoreHttpStatusNotOk() {

	    // 默认不忽略
	    if (this.request == null) {
	        return false;
        }
        return this.request.isIgnoreHttpStatusNotOk();
    }

	public BasicCookieStore getCookieStore() {
		return cookieStore;
	}
}

class MyPlainConnectionSocketFactory extends PlainConnectionSocketFactory {
	
    @Override
    public Socket createSocket(final HttpContext context) throws IOException {
        InetSocketAddress socksaddr = (InetSocketAddress) context.getAttribute("socks.address");
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksaddr);
        return new Socket(proxy);
    }
}

class MySSLConnectionSocketFactory extends SSLConnectionSocketFactory {

    public MySSLConnectionSocketFactory(final SSLContext sslContext) {
        super(sslContext);
    }

    @Override
    public Socket createSocket(final HttpContext context) throws IOException {
        InetSocketAddress socksaddr = (InetSocketAddress) context.getAttribute("socks.address");
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksaddr);
        return new Socket(proxy);
    }


	public static void main(String[] args) throws BaseHttpException {
//		HttpUtils http = new HttpUtils();
////		http.setTimeOut(3);
//		http.setConnectionTimeout(3000);
//		http.setSoTimeout(3000);

		System.out.println("IBE_CAN".toLowerCase().startsWith("ibe"));
		System.out.println("IBE".toLowerCase().startsWith("ibe"));


//		http.get("http://google.com");
	}

}