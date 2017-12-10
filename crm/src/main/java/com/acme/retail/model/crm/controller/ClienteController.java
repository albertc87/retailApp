package com.acme.retail.model.crm.controller;

import com.acme.retail.model.Cliente;
import com.acme.retail.model.crm.exception.ResourceNotFoundException;
import com.acme.retail.model.crm.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RequestMapping(method = GET, value = "/cliente/{clienteId}")
    @ExceptionHandler({ResourceNotFoundException.class})
    public Cliente getClienteById(@PathVariable("clienteId") String clienteId){
        return clienteService.getClienteById(clienteId);
    }

}
