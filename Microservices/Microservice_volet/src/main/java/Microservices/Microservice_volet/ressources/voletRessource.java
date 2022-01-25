package Microservices.Microservice_volet.ressources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Microservices.Microservice_volet.model.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/volets")
public class voletRessource { 
    private volet[] volets_tab = {new volet(1,1), new volet(1, 2), new volet(2, 1), new volet(2, 2)};

    @GetMapping("/status")
    public String volet_all() {
        String msg = "";
        for (volet volet : volets_tab) {
            msg += "le Voilet " + volet.getId() + " de l'étage " + volet.getEtage() + ", salle " + volet.getSalle() + " est ";
            if(volet.isEtat())
                msg += "ouvert à " + volet.getOrder() +"<br>";
            else
                msg += "fermé <br>";
        }
        return msg;        
    }

    @GetMapping("/all")
	public List<volet> getAllvolet() {
		List<volet> volet_list = new ArrayList<volet>();
		for (volet volet : volets_tab) {
			volet_list.add(volet);
		}
		return volet_list;
	}

    @GetMapping("/{id}")
    public volet getVoletByID(@PathVariable int id) {
        volet buff = new volet();
        for (volet volet : volets_tab) {
            if(volet.getId() == id) {
                buff = volet;
            }
        }
        return buff;
    }

    @GetMapping("/etages/{etage}")
    public List<volet> getvoletEtage(@PathVariable int etage) {
        List<volet> volet_list = new ArrayList<volet>();
        for (volet volet : volets_tab) {
            if(volet.getEtage() == etage)
                volet_list.add(volet);
        }
        System.out.println(volet_list.toString());
        return volet_list;
    }

    @GetMapping("/salles/{salle}")
    public List<volet> getvoletSalle(@PathVariable int salle) {
        List<volet> volet_list = new ArrayList<volet>();
        for (volet volet : volets_tab) {
            if(volet.getEtage() == salle)
                volet_list.add(volet);
        }
        System.out.println(volet_list.toString());
        return volet_list;
    }

    @PostMapping("/order/{id}/{order}")
    public ResponseEntity<String> setVoletOrder(@PathVariable int id, @PathVariable double order) {
        // volet buff = new volet();
        for (volet volet : volets_tab) {
            if(volet.getId() == id)
            {
                volet.setOrder(order);
                // buff = volet;
                return new ResponseEntity<String>("OK !",HttpStatus.OK) ;
            }
        }
        return new ResponseEntity<String>("Wrong id !",HttpStatus.BAD_REQUEST) ;
    }
}
