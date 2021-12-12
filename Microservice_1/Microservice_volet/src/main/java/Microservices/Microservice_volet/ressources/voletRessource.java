package Microservices.Microservice_volet.ressources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import Microservices.Microservice_volet.model.*;


@RestController
public class voletRessource  {
	
	@PostMapping("/volets/create/{id}/{etage}/{salle}")
	public volet create (@PathVariable int id, @PathVariable int etage, @PathVariable int salle) {
		
		volet volet = new volet(id, etage, salle);
		return volet;
	}
	
	@PutMapping("/volets/activate/id")
	public void activate(volet volet,@PathVariable int id) {
		volet.setEtat(true);
	}
	
}
