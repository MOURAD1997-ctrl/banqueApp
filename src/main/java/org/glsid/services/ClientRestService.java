package org.glsid.services;

import java.util.List;

import org.glsid.entities.Client;
import org.glsid.metier.ClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRestService {

	@Autowired
	ClientMetier clientMetier;
	@RequestMapping(value = "/clients", method = RequestMethod.POST)
	public Client saveClient(@RequestBody Client c) {
		return clientMetier.saveClient(c);
	}
	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public List<Client> listClient(){
		return clientMetier.listClient();
	}
}