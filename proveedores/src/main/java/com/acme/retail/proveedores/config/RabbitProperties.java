package com.acme.retail.proveedores.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("rabbit")
public class RabbitProperties{
    private String server;
    private String user;
    private String virtualhost;
    private String password;
    private int channelCheckoutTimeout;
    private int requestedHeartBeat;
    private String queueName;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getVirtualhost() {
        return virtualhost;
    }

    public void setVirtualhost(String virtualhost) {
        this.virtualhost = virtualhost;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getChannelCheckoutTimeout() {
        return channelCheckoutTimeout;
    }

    public void setChannelCheckoutTimeout(int channelCheckoutTimeout) {
        this.channelCheckoutTimeout = channelCheckoutTimeout;
    }

    public int getRequestedHeartBeat() {
        return requestedHeartBeat;
    }

    public void setRequestedHeartBeat(int requestedHeartBeat) {
        this.requestedHeartBeat = requestedHeartBeat;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }


}
