package Microservices.Microservice_presence.ressources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Microservices.Microservice_presence.model.*;

@RestController
@RequestMapping("/presence")
public class presenceRessource {

	private static Presence[] tabPresence = { new Presence(0, 1), new Presence(0, 2),
			new Presence(1, 1), new Presence(1, 2) };

	@GetMapping("/{id}")
	public Presence getState_id(@PathVariable int id) {
		Presence buff = new Presence();
		for (Presence presence : tabPresence) {
			presence.update();
			if (presence.getId() == id)
				buff = presence;
		}
		return buff;
	}

	@GetMapping("/status")
	public String getState_all() {
		String msg = "";
		for (Presence presence : tabPresence) {
			msg += "le capteur de présence " + presence.getId() + " de l'étage " + presence.getEtage() + ", salle "
					+ presence.getSalle() + " est " + presence.isEtat() + "<br>";
		}
		return msg;
	}

	@GetMapping("/all")
	public List<Presence> getAllPresence() {
		List<Presence> presence_list = new ArrayList<Presence>();
		for (Presence presence : tabPresence) {
			presence.update();
			presence_list.add(presence);
		}
		return presence_list;
	}

	@GetMapping("/etage/{etage}")
	public List<Presence> getState_Etage(@PathVariable int etage) {
		List<Presence> presence_list = new ArrayList<Presence>();
        for (Presence presence : tabPresence) {
			presence.update();
            if(presence.getEtage() == etage)
                presence_list.add(presence);
        }
        System.out.println(presence_list.toString());
        return presence_list;
	}

	@GetMapping("/salle/{salle}")
	public List<Presence> getState_Salle(@PathVariable int salle) {
		List<Presence> Presence_list = new ArrayList<Presence>();
        for (Presence presence : tabPresence) {
			presence.update();
            if(presence.getEtage() == salle)
                Presence_list.add(presence);
        }
        System.out.println(Presence_list.toString());
        return Presence_list;
	}

}
