package org.dolphin.commons.util.host;

import java.util.List;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.Http11AprProtocol;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.coyote.http2.Http2Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PortUtil {
	private static final Logger log = LoggerFactory.getLogger(PortUtil.class);
	public static int getTomcatPort(){
		int port = 8080;
		List<MBeanServer> mBeanServerList = MBeanServerFactory.findMBeanServer(null);
		try {
			if(mBeanServerList!=null && !mBeanServerList.isEmpty()){
				MBeanServer mBeanServer = mBeanServerList.get(0);
				ObjectName objectName = new ObjectName("Catalina","type","Server");
				Server server = (Server)mBeanServer.getAttribute(objectName, "managedResource");
				Service[] services = server.findServices();
				for(Service service:services){
					for(Connector connector:service.findConnectors()){
						ProtocolHandler protocolHandler = connector.getProtocolHandler();
						if((protocolHandler instanceof Http2Protocol)||(protocolHandler instanceof Http11AprProtocol) || (protocolHandler instanceof Http11NioProtocol)){
							return connector.getPort();
						}
					}
				}
			}else{
				port = -1;
				log.error("[PortUtil] [getTomcatPort] can not get tomcat port,not use tomcat?");
			}
		} catch (Exception e) {
			e.printStackTrace();
			port = -1;
			log.error("[PortUtil][getTomcatPort]",e);
		}
		return port;
	}
}
