package Microservices.Microservice_controlleur.model;

import java.util.Random;

public class gas {
	private int id;
	private float data;
	private int etage, salle;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getData() {
		return data;
	}

	public void setData(float data) {
		this.data = data;
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

	public gas(int etage, int salle) {
		super();
		Random rand = new Random();
		this.id = rand.nextInt(1000);
		this.etage = etage;
		this.salle = salle;
		rand = new Random();
		this.data = rand.nextInt(4095);
	}

	public gas() {
		super();
	}

	public void update() {
		Random rand = new Random();
		this.data = rand.nextInt(4095);
	}

}
