package Microservices.Microservice_fenetre.ressources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Microservices.Microservice_fenetre.model.fenetre;

@RestController
@RequestMapping("/fenetres")
public class fenetreRessource {
    private fenetre[] fenetres_tab = { new fenetre(1, 0, 1), new fenetre(2, 0, 2), new fenetre(3, 1, 1),
            new fenetre(4, 1, 2) };

    @GetMapping("all")
    public String fenetre_all() {
        String msg = "";
        for (fenetre fenetre : fenetres_tab) {
            msg += "les fenetres " + fenetre.getId() + " de l'étage " + fenetre.getEtage() + ", salle " + fenetre.getSalle()
                    + " sont ";
            if (fenetre.isEtat())
                msg += "ouvert <br>";
            else
                msg += "fermé <br>";
        }
        return msg;
    }

    @GetMapping("{id}")
    public fenetre getfenetreByID(@PathVariable int id) {
        fenetre buff = new fenetre();
        for (fenetre fenetre : fenetres_tab) {
            if (fenetre.getId() == id) {
                buff = fenetre;
            }
        }
        return buff;
    }

    @GetMapping("etages/{etage}")
    public List<fenetre> getfenetreEtage(@PathVariable int etage) {
        List<fenetre> fenetre_list = new ArrayList<fenetre>();
        for (fenetre fenetre : fenetres_tab) {
            if (fenetre.getEtage() == etage)
                fenetre_list.add(fenetre);
        }
        System.out.println(fenetre_list.toString());
        return fenetre_list;
    }

    @GetMapping("salles/{salle}")
    public List<fenetre> getfenetreSalle(@PathVariable int salle) {
        List<fenetre> fenetre_list = new ArrayList<fenetre>();
        for (fenetre fenetre : fenetres_tab) {
            if (fenetre.getEtage() == salle)
                fenetre_list.add(fenetre);
        }
        System.out.println(fenetre_list.toString());
        return fenetre_list;
    }

    @PostMapping("order/{id}/{etage}/{salle}/{etat}")
    public fenetre setfenetreOrder(@PathVariable int id, @PathVariable int etage, @PathVariable int salle,
            @PathVariable Boolean etat) {
        fenetre buff = new fenetre();
        System.out.println("id " + id + " etage " + etage + " salle " + salle + " order " + etat);
        for (fenetre fenetre : fenetres_tab) {
            if (fenetre.getId() == id && fenetre.getEtage() == etage && fenetre.getSalle() == salle) {
                fenetre.setEtat(etat);
                buff = fenetre;
            }
        }
        return buff;
    }

}
