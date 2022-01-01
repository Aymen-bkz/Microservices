package Microservices.Microservice_lampe.model;

import java.util.Random;

public class Lampe {
	private int id, etage, salle;
	private boolean etat;

	public Lampe(int etage, int salle) {
		super();
		Random rand = new Random();
		this.id = rand.nextInt(1000);
		this.etage = etage;
		this.salle = salle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public int getEtage() {
		return etage;
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	public int getSalle() {
		return salle;
	}

	public void setSalle(int salle) {
		this.salle = salle;
	}

	public Lampe() {
		super();
	}

}
