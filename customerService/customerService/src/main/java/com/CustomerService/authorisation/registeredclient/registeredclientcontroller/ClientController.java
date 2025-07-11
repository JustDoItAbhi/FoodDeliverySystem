package com.CustomerService.authorisation.registeredclient.registeredclientcontroller;

import com.CustomerService.authorisation.registeredclient.registeredclientdto.ClientRequestDto;
import com.CustomerService.authorisation.registeredclient.registeredclientservice.CustomizeRegeredClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {// registrerd OICD client controller
    @Autowired
    public CustomizeRegeredClientService service;// constructor
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/register")// post mapping to register a client only ADMIN can use
    public ResponseEntity<RegisteredClient> createClient(@RequestBody ClientRequestDto dto){
        return ResponseEntity.ok(service.createRegeretedClient(dto));
    }
    @DeleteMapping("/delete/{clientId}")// post mapping to register a client only ADMIN can use
    public ResponseEntity<Boolean> deleteClient(@PathVariable ("clientId")String clientId){
        return ResponseEntity.ok(service.deleteClient(clientId));
    }
}
