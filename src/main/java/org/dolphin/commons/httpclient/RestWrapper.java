package org.dolphin.commons.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dolphin.commons.util.Base64Utils;

import com.alibaba.fastjson.JSON;

public class RestWrapper {
	private HttpServletRequest req;
	private HttpServletResponse res;
	private String method;
	private boolean isBase64;
	private String paramSource;
	
	public RestWrapper() {
		super();
	}

	public RestWrapper(HttpServletRequest req, HttpServletResponse res) {
		super();
		this.req = req;
		this.res = res;
		this.method = req.getMethod();
		this.isBase64 = true;
	}
	
	private String getBodyData() throws IOException{
		if(this.req == null)
			return null;
		StringBuffer stringBuffer = new StringBuffer();
		String str;
		BufferedReader br = this.req.getReader();
		while((str = br.readLine())!=null){
			stringBuffer.append(str);
		}
		return stringBuffer.toString();
	}
	
	private void initParamSource() throws IOException{
		if(this.req!=null){
			String method = this.req.getMethod();
			if("get".equalsIgnoreCase(method)){
				if(this.isBase64){
					this.paramSource = Base64Utils.decodeString(this.req.getQueryString());
				}else{
					this.paramSource = this.req.getQueryString();
				}
			}else if("post".equalsIgnoreCase(method)){
				if(this.isBase64){
					this.paramSource = Base64Utils.decodeString(getBodyData());
				}else{
					this.paramSource = getBodyData();
				}
			}
		}
	}
	
	public String getParamString() throws IOException{
		initParamSource();
		return this.paramSource;
	}
	
	public <T> T parseObject(Class<T> clazz) throws IOException{
		return parseObject(getParamString(), clazz);
	}
	
	public <T> T parseObject(String text,Class<T> clazz) throws IOException{
		return JSON.parseObject(text, clazz);
	}	

	public <T> List<T> parseArray(Class<T> arg1) throws IOException{
		return parseArray(getParamString(), arg1);
	}	
	
	public <T> List<T> parseArray(String arg0,Class<T> arg1) throws IOException{
		return JSON.parseArray(arg0, arg1);
	}
	
	public void send(Object obj) throws IOException{
		String resStr;
		if(this.isBase64){
			resStr = Base64Utils.encodeString(JSON.toJSONString(obj));
		}else{
			resStr = JSON.toJSONString(obj);
		}
		this.res.setHeader("Access-Control-Allow-Origin", "*");
		this.res.setHeader("Content-type", "application/json;charset=UTF-8");
		this.res.setCharacterEncoding("UTF-8");
		this.res.getWriter().write(resStr);
	}
	
	public HttpServletRequest getReq() {
		return req;
	}
	public void setReq(HttpServletRequest req) {
		this.req = req;
	}
	public HttpServletResponse getRes() {
		return res;
	}
	public void setRes(HttpServletResponse res) {
		this.res = res;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public boolean isBase64() {
		return isBase64;
	}
	public void setBase64(boolean isBase64) {
		this.isBase64 = isBase64;
	}
	public String getParamSource() {
		return paramSource;
	}
	public void setParamSource(String paramSource) {
		this.paramSource = paramSource;
	}

	@Override
	public String toString() {
		return "RestWrapper [req=" + req + ", res=" + res + ", method=" + method + ", isBase64=" + isBase64
				+ ", paramSource=" + paramSource + "]";
	}
	
}
