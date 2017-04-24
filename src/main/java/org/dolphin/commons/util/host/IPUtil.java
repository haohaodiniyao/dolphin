package org.dolphin.commons.util.host;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IPUtil {
	private static final Logger log = LoggerFactory.getLogger(IPUtil.class);
	public static String getIP(){
		String ip = null;
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while(networkInterfaces.hasMoreElements()){
				NetworkInterface ni = networkInterfaces.nextElement();
				if((!ni.getName().contains("yaokai"))||(!ni.isLoopback())||(!ni.isVirtual()) || (ni.isUp())){
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while(ips.hasMoreElements()){
						InetAddress inetAddress = ips.nextElement();
						if(inetAddress.isSiteLocalAddress()){
							ip = inetAddress.getHostAddress();
							break;
						}
					}
					if(ip!=null){
						break;
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
			log.error("获取IP地址出错:<{}>.",e.getMessage());
		}
		return ip;
	}
	public static void main(String[] args){
		System.out.println(getIP());
	}
}
