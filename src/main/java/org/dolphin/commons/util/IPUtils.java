package org.dolphin.commons.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IPUtils {
	private static final Logger log = LoggerFactory.getLogger(IPUtils.class);
	public static String getIp() {
		Enumeration<NetworkInterface> netInterfaces = null;
		String ip = null;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				if (ni.isLoopback() || ni.isVirtual() || !ni.isUp()) {
					continue;
				}
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					InetAddress address = ips.nextElement();
					if (RegexUtil.checkIp(address.getHostAddress())) {
						ip = address.getHostAddress();
					}
				}
			}
		} catch (Exception e) {
			log.error("鑾峰彇IP鍦板潃鍑洪敊: <{}>.", e.getMessage());
		}
		return ip;
	}
}
