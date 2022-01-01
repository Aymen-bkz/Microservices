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
    private gas[] gas_tab = { new gas(0, 1), new gas(0, 2), new gas(1, 1),
            new gas(1, 2) };

    @GetMapping("/{id}")
    public gas getState_id(@PathVariable int id) {
        gas buff = new gas();
        for (gas gas : gas_tab) {
            gas.update();
            if (gas.getId() == id)
                buff = gas;
        }
        return buff;
    }

    @GetMapping("/status")
    public String getState_all() {
        String msg = "";
        for (gas gas : gas_tab) {
            msg += "le capteur de gas " + gas.getId() + " de l'étage " + gas.getEtage()
                    + ", salle "
                    + gas.getSalle() + " : " + gas.getData() + "<br>";
        }
        return msg;
    }

    @GetMapping("/all")
    public List<gas> getAllgas() {
        List<gas> gas_list = new ArrayList<gas>();
        for (gas gas : gas_tab) {
            gas.update();
            gas_list.add(gas);
        }
        return gas_list;
    }

    @GetMapping("/etage/{etage}")
    public List<gas> getState_Etage(@PathVariable int etage) {
        List<gas> gas_list = new ArrayList<gas>();
        for (gas gas : gas_tab) {
            gas.update();
            if (gas.getEtage() == etage)
                gas_list.add(gas);
        }
        System.out.println(gas_list.toString());
        return gas_list;
    }

    @GetMapping("/salle/{salle}")
    public List<gas> getState_Salle(@PathVariable int salle) {
        List<gas> gas_list = new ArrayList<gas>();
        for (gas gas : gas_tab) {
            gas.update();
            if (gas.getEtage() == salle)
                gas_list.add(gas);
        }
        System.out.println(gas_list.toString());
        return gas_list;
    }
}
