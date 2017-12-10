package com.acme.retail.model.crm.repository.impl;

import com.acme.retail.model.Cliente;
import com.acme.retail.model.crm.repository.ClienteCachedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
@Repository
public class ClienteCachedRepositoryImpl implements ClienteCachedRepository {
    private static final String KEY = "Cliente";

    private RedisTemplate redisTemplate;
    private HashOperations hashOps;

    private final Logger logger = LoggerFactory.getLogger(ClienteCachedRepositoryImpl.class);

    @Autowired
    public ClienteCachedRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }


    @Override
    public void saveCliente(Cliente cliente) {
        logger.info("Saved cliente in cache {}",cliente.toString());
        hashOps.put(KEY, cliente.getId(), cliente);
    }

    @Override
    public Cliente getClienteById(String clienteId) {
        Cliente cliente = (Cliente) hashOps.get(KEY, clienteId);
        logger.info("Extracted cached cliente {}", cliente);
        return cliente;
    }
}
