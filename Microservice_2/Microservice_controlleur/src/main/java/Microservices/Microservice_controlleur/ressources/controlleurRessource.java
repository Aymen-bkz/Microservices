package Microservices.Microservice_controlleur.ressources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import Microservices.Microservice_controlleur.model.*;


@RestController
@RequestMapping("/auto")
public class controlleurRessource {
	private final String PresenceUrl = "http://localhost:8086/presence";

	@GetMapping("/run")
	public String run() {
		RestTemplate restTemplate = new RestTemplate();
		String msg = restTemplate.getForObject(PresenceUrl+"/all",String.class);
		return msg;
	}
	 
}
