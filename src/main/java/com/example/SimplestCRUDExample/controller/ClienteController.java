package com.example.SimplestCRUDExample.controller;

import com.example.SimplestCRUDExample.model.Book;
import com.example.SimplestCRUDExample.model.Cliente;
import com.example.SimplestCRUDExample.repo.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/getClientes")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        try {
            List<Cliente> clienteList = new ArrayList<>();
            clienteRepository.findAll().forEach(clienteList::add);

            if (clienteList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(clienteList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        Optional<Cliente> clienteObj = clienteRepository.findById(id);
        if (clienteObj.isPresent()) {
            return new ResponseEntity<>(clienteObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addCliente")
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente) {
        try {
            Cliente clienteObj = clienteRepository.save(cliente);
            return new ResponseEntity<>(clienteObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/updateCliente/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Optional<Cliente> clienteData = clienteRepository.findById(id);
            if (clienteData.isPresent()) {
                Cliente updatedClienteData = clienteData.get();
                updatedClienteData.setNombres(cliente.getNombres());
                updatedClienteData.setPrimerApellido(cliente.getPrimerApellido());
                updatedClienteData.setSegundoApellido(cliente.getSegundoApellido());
                updatedClienteData.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
                updatedClienteData.setDireccion(cliente.getDireccion());
                updatedClienteData.setCelular(cliente.getCelular());
                updatedClienteData.setPassword(cliente.getPassword());
                updatedClienteData.setEmail(cliente.getEmail());

                Cliente clienteObj = clienteRepository.save(updatedClienteData);
                return new ResponseEntity<>(clienteObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteClienteById/{id}")
    public ResponseEntity<HttpStatus> deleteCliente(@PathVariable Long id) {
        try {
            clienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
