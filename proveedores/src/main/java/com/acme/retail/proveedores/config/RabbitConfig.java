package com.acme.retail.proveedores.config;

import com.acme.retail.proveedores.rabbit.VentaRealizadaConsumer;
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
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, VentaRealizadaConsumer rabbitConsumer){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(rabbitConsumer);
        container.setQueueNames(properties.getQueueName());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }

}
