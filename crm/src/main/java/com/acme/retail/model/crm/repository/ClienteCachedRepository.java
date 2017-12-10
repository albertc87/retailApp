package com.acme.retail.model.crm.repository;

import com.acme.retail.model.Cliente;


public interface ClienteCachedRepository {
    void saveCliente(Cliente cliente);
    Cliente getClienteById(String clienteId);
}
