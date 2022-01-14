package Microservices.Microservice_luminosity.ressources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Microservices.Microservice_luminosity.model.Luminosity;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/luminosité")
public class luminosityRessource {
    private Luminosity[] luminosities_tab = { new Luminosity(1, 1), new Luminosity(1, 2), new Luminosity(2, 1),
            new Luminosity(2, 2) };

    @GetMapping("/{id}")
    public Luminosity getState_id(@PathVariable int id) {
        Luminosity buff = new Luminosity();
        for (Luminosity Luminosity : luminosities_tab) {
            Luminosity.update();
            if (Luminosity.getId() == id)
                buff = Luminosity;
        }
        return buff;
    }

    @GetMapping("/status")
    public String getState_all() {
        String msg = "";
        for (Luminosity Luminosity : luminosities_tab) {
            msg += "le capteur de Luminosité " + Luminosity.getId() + " de l'étage " + Luminosity.getEtage() + ", salle "
                    + Luminosity.getSalle() + " est " + Luminosity.getData() + "<br>";
        }
        return msg;
    }

    @GetMapping("/all")
	public List<Luminosity> getAllLuminosity() {
		List<Luminosity> Luminosity_list = new ArrayList<Luminosity>();
		for (Luminosity Luminosity : luminosities_tab) {
            Luminosity.update();
			Luminosity_list.add(Luminosity);
		}
        // System.out.println(Luminosity_list);
		return Luminosity_list;
	}

    @GetMapping("/etage/{etage}")
    public List<Luminosity> getState_Etage(@PathVariable int etage) {
        List<Luminosity> Luminosity_list = new ArrayList<Luminosity>();
        for (Luminosity Luminosity : luminosities_tab) {
            Luminosity.update();
            if (Luminosity.getEtage() == etage)
                Luminosity_list.add(Luminosity);
        }
        System.out.println(Luminosity_list.toString());
        return Luminosity_list;
    }

    @GetMapping("/salle/{salle}")
    public List<Luminosity> getState_Salle(@PathVariable int salle) {
        List<Luminosity> Luminosity_list = new ArrayList<Luminosity>();
        for (Luminosity Luminosity : luminosities_tab) {
            Luminosity.update();
            if (Luminosity.getEtage() == salle)
                Luminosity_list.add(Luminosity);
        }
        System.out.println(Luminosity_list.toString());
        return Luminosity_list;
    }
}
