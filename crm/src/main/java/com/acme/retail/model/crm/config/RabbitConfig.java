package com.acme.retail.model.crm.config;

import com.acme.retail.model.crm.rabbit.ClienteRegistradoConsumer;
import com.acme.retail.model.crm.rabbit.VentaRealizadaConsumer;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RabbitProperties.class)
public class RabbitConfig {

    private final RabbitProperties properties;

    @Value("${rabbit.queue.clienteRegistrado}")
    private final String queueClienteCreado = "";
    @Value("${rabbit.queue.ventaRealizada}")
    private final String queueVentaRealizada = "";


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

    @Bean(name = "ventaRealizada")
    public SimpleMessageListenerContainer ventaRealizadaQueue(ConnectionFactory connectionFactory, VentaRealizadaConsumer rabbitConsumer){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(rabbitConsumer);
        container.setQueueNames("acme.retail.crm.ventarealizada");
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }

   @Bean(name = "clienteCreado")
    public SimpleMessageListenerContainer nuevoClienteQueue(ConnectionFactory connectionFactory, ClienteRegistradoConsumer rabbitConsumer){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(rabbitConsumer);
        container.setQueueNames("acme.retail.crm.clienteregistrado");
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }

}
