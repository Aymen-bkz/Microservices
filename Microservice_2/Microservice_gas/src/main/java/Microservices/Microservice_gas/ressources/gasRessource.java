package Microservices.Microservice_gas.ressources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Microservices.Microservice_gas.model.gas;

@RestController
@RequestMapping("gas")
public class gasRessource {
    private gas[] gas_tab = { new gas(1, 0, 1), new gas(2, 0, 2), new gas(3, 1, 1),
            new gas(4, 1, 2) };

    @GetMapping("/{id}")
    public gas getState_id(@PathVariable int id) {
        gas buff = new gas();
        for (gas gas : gas_tab) {
            if (gas.getId() == id)
                buff = gas;
        }
        return buff;
    }

    @GetMapping("/all")
    public String getState_all() {
        String msg = "";
        for (gas gas : gas_tab) {
            msg += "le capteur de gas " + gas.getId() + " de l'étage " + gas.getEtage()
                    + ", salle "
                    + gas.getSalle() + " : " + gas.getData();
        }
        return msg;
    }

    @GetMapping("/Etage/{etage}")
    public List<gas> getState_Etage(@PathVariable int etage) {
        List<gas> gas_list = new ArrayList<gas>();
        for (gas gas : gas_tab) {
            if (gas.getEtage() == etage)
                gas_list.add(gas);
        }
        System.out.println(gas_list.toString());
        return gas_list;
    }

    @GetMapping("/Salle/{salle}")
    public List<gas> getState_Salle(@PathVariable int salle) {
        List<gas> gas_list = new ArrayList<gas>();
        for (gas gas : gas_tab) {
            if (gas.getEtage() == salle)
                gas_list.add(gas);
        }
        System.out.println(gas_list.toString());
        return gas_list;
    }
}
