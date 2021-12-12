package Microservices.Microservice_controlleur.ressources;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import Microservices.Microservice_controlleur.model.*;


@RestController
public class controlleurRessource {
	
	@PostMapping("/volets/create/{id}/{etage}/{salle}")
	public volet create (@PathVariable int id, @PathVariable int etage, @PathVariable int salle) {
		
		volet volet = new volet(id, etage, salle);
		return volet;
	}
	@PutMapping("/volets/open/{id}/{etage}/{salle}")
	public void open (@PathVariable int id, @PathVariable int etage, @PathVariable int salle) {
		
		
	}
	 
}
