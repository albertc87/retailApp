package com.acme.retail.model.crm.rabbit;


import com.acme.retail.model.Cliente;
import com.acme.retail.model.Venta;
import com.acme.retail.repository.ClienteRepository;
import com.acme.retail.repository.VentaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteRegistradoConsumer implements ChannelAwareMessageListener {

    private final ClienteRepository repository;
    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(ClienteRegistradoConsumer.class);

    @Autowired
    public ClienteRegistradoConsumer(ClienteRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message, Channel channel)  {
        String incomingMessage = new String(message.getBody());
        logger.info("Cliente recibido: {}", incomingMessage);
        try {
            Cliente cliente = objectMapper.readValue(incomingMessage,Cliente.class);
            repository.save(cliente);
            logger.info("Clientes registrados");
            repository.findAll().forEach(System.out::println);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            logger.error("Error al registrar cliente", e);
        }


    }
}
