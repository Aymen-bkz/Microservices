package Microservices.Microservice_controlleur.ressources;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.catalina.mapper.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import Microservices.Microservice_controlleur.model.*;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/auto")
public class controlleurRessource {
	private final String fenetreUrl = "http://localhost:8082/fenetres";
	private final String gasUrl = "http://localhost:8083/gas";
	private final String lampeUrl = "http://localhost:8084/lampes";
	private final String luminosityUrl = "http://localhost:8085/luminosité";
	private final String PresenceUrl = "http://localhost:8086/presence";
	private final String voletUrl = "http://localhost:8087/volets";

	@GetMapping("/run")
	public String run() {
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();

		// Senario 1
		List<Luminosity> luminosities_List = restTemplate.getForObject(luminosityUrl + "/all", List.class);
		luminosities_List = mapper.convertValue(luminosities_List, new TypeReference<List<Luminosity>>() {
		});
		List<Presence> presences_List = restTemplate.getForObject(PresenceUrl + "/all", List.class);
		presences_List = mapper.convertValue(presences_List, new TypeReference<List<Presence>>() {
		});
		List<volet> volets_List = restTemplate.getForObject(voletUrl + "/all", List.class);
		volets_List = mapper.convertValue(volets_List, new TypeReference<List<volet>>() {
		});

		// System.out.println(luminosities_List.get(0).getData());
		for (int i = 0; i < volets_List.toArray().length; i++) {
			if (luminosities_List.get(i).getData() < 500 &&
					presences_List.get(i).isEtat()) {
				String post_url = voletUrl+"/order/" + volets_List.get(i).getId() + "/"
						+ volets_List.get(i).getEtage() + "/" + volets_List.get(i).getSalle() + "/" + 100;
				System.out.println(post_url);
				restTemplate.postForObject(post_url, "", String.class);
			}
		}

		// Senario 2
		List<gas> gas_List = restTemplate.getForObject(gasUrl + "/all", List.class);
		gas_List = mapper.convertValue(gas_List, new TypeReference<List<gas>>() {
		});
		List<fenetre> fenetres_List = restTemplate.getForObject(fenetreUrl + "/all", List.class);
		fenetres_List = mapper.convertValue(fenetres_List, new TypeReference<List<fenetre>>() {
		});

		for (int i = 0; i < fenetres_List.toArray().length; i++) {
			if (gas_List.get(i).getData() > 100) {
				//fenetres_List.get(i).setEtat(true);
				String post_url = fenetreUrl+"/order/" + volets_List.get(i).getId() + "/"
						+ volets_List.get(i).getEtage() + "/" + volets_List.get(i).getSalle() + "/" + true;
				System.out.println(post_url);
				restTemplate.postForObject(post_url, "", String.class);
			}
		}

		// Senario 3
		List<Lampe> lampes_List = restTemplate.getForObject(lampeUrl + "/all", List.class);
		lampes_List = mapper.convertValue(lampes_List, new TypeReference<List<Lampe>>() {
		});
		// Luminosité deja déclaré
		for (int i = 0; i < lampes_List.toArray().length; i++) {
			if (luminosities_List.get(i).getData() < 200) {
				//lampes_List.get(i).setEtat(true);
				String post_url = lampeUrl+"/order/" + volets_List.get(i).getId() + "/"
						+ volets_List.get(i).getEtage() + "/" + volets_List.get(i).getSalle() + "/" + true;
				System.out.println(post_url);
				restTemplate.postForObject(post_url, "", String.class);
			}
		}

		String msg = restTemplate.getForObject(PresenceUrl + "/status", String.class) + "<br>";
		msg += restTemplate.getForObject(luminosityUrl + "/status", String.class) + "<br>";
		msg += restTemplate.getForObject(voletUrl + "/status", String.class) + "<br>";
		msg += restTemplate.getForObject(gasUrl + "/status", String.class) + "<br>";
		msg += restTemplate.getForObject(fenetreUrl + "/status", String.class) + "<br>";
		msg += restTemplate.getForObject(lampeUrl + "/status", String.class) + "<br>";
		return msg;
	}

}
