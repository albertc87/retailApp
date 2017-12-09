package com.acme.retail.ventas.config;

import com.acme.retail.ventas.rabbit.RabbitConsumer;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RabbitProperties.class)
public class RabbitConfig {

    private final RabbitProperties properties;

    @Autowired
    public RabbitConfig(RabbitProperties properties) {
        this.properties = properties;
    }


    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(properties.getServer());
        connectionFactory.setUsername(properties.getUser());
        connectionFactory.setVirtualHost(properties.getVirtualhost());
        connectionFactory.setPassword(properties.getPassword());
        connectionFactory.setChannelCheckoutTimeout(properties.getChannelCheckoutTimeout());
        connectionFactory.setRequestedHeartBeat(properties.getRequestedHeartBeat());
        return connectionFactory;
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, RabbitConsumer rabbitConsumer){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(rabbitConsumer);
        container.setQueueNames(properties.getQueueName());
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return container;
    }

}

@ConfigurationProperties("rabbit")
class RabbitProperties{
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
