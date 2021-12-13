package Microservices.Microservice_controlleur.model;

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

	public gas(int id, int etage, int salle, float data) {
		super();
		this.id = id;
		this.etage = etage;
		this.salle = salle;
		this.data = data;
	}

	public gas() {
		super();
	}
}
