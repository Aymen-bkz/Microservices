package Microservices.Microservice_luminosity.model;

import java.util.Random;

public class Luminosity {
	private float data;
	private int id, etage, salle;

	public float getData() {
		return data;
	}

	public void setData(float data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Luminosity() {
		super();
	}

	public Luminosity(int etage, int salle) {
		super();
		Random rand = new Random();
		this.id = etage*10 + salle;
		this.etage = etage;
		this.salle = salle;
		rand = new Random();
		this.data = rand.nextInt(600);
	}

	public void update() {
		Random rand = new Random();
		this.data = rand.nextInt(600);
	}

}
