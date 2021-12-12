package Microservices.Microservice_luminosity.model;

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

}
