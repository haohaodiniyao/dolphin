package org.dolphin.commons.disruptor;
import java.util.Date;

/**
 * Created by licheng5 on 2016/6/13.
 */
public class LogData {
    /**
     * 接口交互唯一标识
     */
    private String interSerial;

    /**
     * 0被调用 1调用别的
     */
    private int invokeType;

    /**
     * 发起方参数来源即为系统编码
     */
    private String source;

    /**
     * 服务名(url or method)
     */
    private String serviceName;

    /**
     * 参数内容
     */
    private String content;

    /**
     * 添加人
     */
    private int addUid;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 删除标识
     */
    private int delFlag;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 0请求 1响应
     */
    private int type;

    /**
     * 落地方
     */
    private String target;

    /**
     * 当前服务器IP added by GL 2014-5-23
     */
    private String serverIp;

    /**
     * 表中id
     */
    private int id;

    /**
     * 接口调用耗时(单位:ms)
     */
    private long timeCost;

    /**
     * PGA接口调用地址
     */
    private String remoteAddr;


    public String getInterSerial() {
        return interSerial;
    }

    public void setInterSerial(String interSerial) {
        this.interSerial = interSerial;
    }

    public int getInvokeType() {
        return invokeType;
    }

    public void setInvokeType(int invokeType) {
        this.invokeType = invokeType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAddUid() {
        return addUid;
    }

    public void setAddUid(int addUid) {
        this.addUid = addUid;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(long timeCost) {
        this.timeCost = timeCost;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                ", interSerial='" + interSerial + '\'' +
                ", invokeType=" + invokeType +
                ", source='" + source + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", content='" + content + '\'' +
                ", addUid=" + addUid +
                ", addTime=" + addTime +
                ", delFlag=" + delFlag +
                ", orderId=" + orderId +
                ", orderType=" + orderType +
                ", type=" + type +
                ", target='" + target + '\'' +
                ", serverIp='" + serverIp + '\'' +
                ", id=" + id +
                ", timeCost=" + timeCost +
                ", remoteAddr='" + remoteAddr + '\'' +
                '}';
    }
}