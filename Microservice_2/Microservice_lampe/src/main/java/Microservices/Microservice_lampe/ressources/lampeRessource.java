package Microservices.Microservice_lampe.ressources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Microservices.Microservice_lampe.model.Lampe;

@RestController
@RequestMapping("/lampes")
public class lampeRessource {
    private Lampe[] Lampes_tab = { new Lampe(1, 1), new Lampe(1, 2), new Lampe(2, 1),
            new Lampe(2, 2) };

    @GetMapping("/status")
    public String Lampe_all() {
        String msg = "";
        for (Lampe Lampe : Lampes_tab) {
            msg += "les Lampes " + Lampe.getId() + " de l'étage " + Lampe.getEtage() + ", salle " + Lampe.getSalle()
                    + " sont ";
            if (Lampe.isEtat())
                msg += "allumé <br>";
            else
                msg += "éteinte <br>";
        }
        return msg;
    }

    @GetMapping("/all")
	public List<Lampe> getAllLampe() {
		List<Lampe> Lampe_list = new ArrayList<Lampe>();
		for (Lampe Lampe : Lampes_tab) {
			Lampe_list.add(Lampe);
		}
		return Lampe_list;
	}

    @GetMapping("/{id}")
    public Lampe getLampeByID(@PathVariable int id) {
        Lampe buff = new Lampe();
        for (Lampe Lampe : Lampes_tab) {
            if (Lampe.getId() == id) {
                buff = Lampe;
            }
        }
        return buff;
    }

    @GetMapping("/etages/{etage}")
    public List<Lampe> getLampeEtage(@PathVariable int etage) {
        List<Lampe> Lampe_list = new ArrayList<Lampe>();
        for (Lampe Lampe : Lampes_tab) {
            if (Lampe.getEtage() == etage)
                Lampe_list.add(Lampe);
        }
        System.out.println(Lampe_list.toString());
        return Lampe_list;
    }

    @GetMapping("/salles/{salle}")
    public List<Lampe> getLampeSalle(@PathVariable int salle) {
        List<Lampe> Lampe_list = new ArrayList<Lampe>();
        for (Lampe Lampe : Lampes_tab) {
            if (Lampe.getEtage() == salle)
                Lampe_list.add(Lampe);
        }
        System.out.println(Lampe_list.toString());
        return Lampe_list;
    }

    @PostMapping("/order/{id}/{etat}")
    public Lampe setLampeOrder(@PathVariable int id, @PathVariable Boolean etat) {
        Lampe buff = new Lampe();
        for (Lampe Lampe : Lampes_tab) {
            if (Lampe.getId() == id) {
                Lampe.setEtat(etat);
                buff = Lampe;
            }
        }
        return buff;
    }
}
