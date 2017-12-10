package com.acme.retail.model.crm.service.impl;

import com.acme.retail.model.Cliente;
import com.acme.retail.model.crm.exception.ResourceNotFoundException;
import com.acme.retail.model.crm.repository.ClienteCachedRepository;
import com.acme.retail.model.crm.service.ClienteService;
import com.acme.retail.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;
    private final ClienteCachedRepository clienteCachedRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteCachedRepository clienteCachedRepository) {
        this.clienteRepository = clienteRepository;
        this.clienteCachedRepository = clienteCachedRepository;
    }

    @Override
    public Cliente getClienteById(String clienteId) {

        Cliente cliente = clienteCachedRepository.getClienteById(clienteId);

        if(cliente == null) {
            cliente = clienteRepository.findOne(clienteId);
        }

        validateCliente(cliente);
        return cliente;
    }

    private void validateCliente(Cliente cliente) {

        if(cliente != null){
            clienteCachedRepository.saveCliente(cliente);
        }else{
            throw new ResourceNotFoundException("El cliente no existe", null);

        }
    }
}
